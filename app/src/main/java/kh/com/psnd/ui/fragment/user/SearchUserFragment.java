package kh.com.psnd.ui.fragment.user;

import com.google.android.material.snackbar.Snackbar;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import core.lib.base.BaseFragment;
import core.lib.dialog.DialogProgress;
import core.lib.utils.ApplicationUtil;
import core.lib.utils.Log;
import io.reactivex.disposables.CompositeDisposable;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentSearchUserBinding;
import kh.com.psnd.eventbus.CreateAccountSuccessEventBus;
import kh.com.psnd.network.model.UserFilter;
import kh.com.psnd.network.response.ResponseUserRolePrivilege;
import kh.com.psnd.network.task.TaskUserRolePrivilege;
import kh.com.psnd.ui.view.SearchUserBarView;
import lombok.val;
import retrofit2.Response;

public class SearchUserFragment extends BaseFragment<FragmentSearchUserBinding> {

    private DialogProgress progress;

    @Override
    public void setupUI() {
        progress = new DialogProgress(getContext(), true, dialogInterface -> getCompositeDisposable().clear());
        binding.addNewUser.setOnClickListener(__ -> loadRolePrivilege());
        binding.searchBar.setupUI(this, callback);
        binding.searchResult.setupUI(this, binding.searchBar);

//        loadRolePrivilege();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCreateAccountSuccessEventBus(CreateAccountSuccessEventBus event) {
        Log.i(event);
        binding.searchResult.addNewUser(event.getUserProfile());
    }

    private void loadRolePrivilege() {
        progress.show();
        val task = new TaskUserRolePrivilege();
        new CompositeDisposable().add(task.start(task.new SimpleObserver() {

            @Override
            public Class<?> clazzResponse() {
                return null;
            }

            @Override
            public void onNext(Response result) {
                Log.i("LOG >> onNext >> result : " + result);
                if (result.isSuccessful()) {
                    val data     = (ResponseUserRolePrivilege) result.body();
                    val fragment = AddUserFragment.newInstance(data.getResult());
                    fragment.show(getParentFragmentManager(), "");
                }
                progress.dismiss();
            }

            @Override
            public void onError(Throwable e) {
                Log.e(e);
                progress.dismiss();
            }
        }));
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.searchBar.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        binding.searchBar.onPause();
    }

    private SearchUserBarView.Callback callback = new SearchUserBarView.Callback() {
        @Override
        public void doSearch(UserFilter filter) {
            binding.searchBar.clearTextBox();
            binding.searchResult.doSearch(filter);
        }

        @Override
        public void doSearch(CharSequence search) {
            if (!ApplicationUtil.isOnline()) {
                Snackbar.make(getView(), R.string.noInternetConnection, Snackbar.LENGTH_SHORT).show();
                return;
            }
            binding.searchResult.doSearch(search.toString());
        }

    };


    @Override
    protected void initToolbar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_search_user;
    }


}