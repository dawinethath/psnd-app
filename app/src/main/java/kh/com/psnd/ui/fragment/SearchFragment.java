package kh.com.psnd.ui.fragment;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;

import core.lib.base.BaseFragment;
import core.lib.dialog.DialogProgress;
import core.lib.utils.ApplicationUtil;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.dao.SearchHistory;
import kh.com.psnd.databinding.FragmentSearchBinding;
import kh.com.psnd.helper.ActivityHelper;
import kh.com.psnd.network.model.Search;
import kh.com.psnd.network.request.RequestStaff;
import kh.com.psnd.network.response.ResponseStaff;
import kh.com.psnd.network.task.TaskStaff;
import kh.com.psnd.ui.view.SearchBarView;
import lombok.val;
import retrofit2.Response;

public class SearchFragment extends BaseFragment<FragmentSearchBinding> {
    private DialogProgress progress = null;

    @Override
    public void setupUI() {
        progress = new DialogProgress(getContext(), true, dialogInterface -> getCompositeDisposable().clear());
        binding.searchBar.setupUI(this, callback);
        binding.searchResult.setupUI(this, binding.searchBar.getBinding());
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.searchBar.onResume();
    }

    private SearchBarView.Callback callback = new SearchBarView.Callback() {
        @Override
        public void doSearch(CharSequence search) {
            Log.i("Search : " + search);
            if (TextUtils.isEmpty(search)) {
                // todo do something here
                return;
            }
            if (!ApplicationUtil.isOnline()) {
                Snackbar.make(getView(), R.string.noInternetConnection, Snackbar.LENGTH_SHORT).show();
                return;
            }
            binding.searchResult.loadMore(search.toString(), 1);
        }

        @Override
        public void onClickedClear() {
            Log.i("onClickedClear");
            binding.searchResult.cleanList();
        }
    };

    @Override
    protected void initToolbar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public void onPause() {
        super.onPause();
        binding.searchBar.onPause();
    }

    public void onClickedItem(@NonNull Search search) {
        progress.show();
        val task = new TaskStaff(new RequestStaff(search.getStaffId()));
        getCompositeDisposable().add(task.start(task.new SimpleObserver() {

            @Override
            public Class<?> clazzResponse() {
                return null;
            }

            @Override
            public void onNext(@io.reactivex.annotations.NonNull Response result) {
                Log.i("LOG >> onNext >> result : " + result);
                if (result.isSuccessful()) {
                    val data = (ResponseStaff) result.body();
                    Log.i(data);
                    ActivityHelper.openDetailActivity(getContext(), data.getResult());
                    SearchHistory.addSearch(binding.searchBar.getSearchString());
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
}