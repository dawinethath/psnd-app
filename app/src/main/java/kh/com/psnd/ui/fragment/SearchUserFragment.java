package kh.com.psnd.ui.fragment;

import android.text.TextUtils;

import com.google.android.material.snackbar.Snackbar;

import core.lib.base.BaseFragment;
import core.lib.utils.ApplicationUtil;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentSearchUserBinding;
import kh.com.psnd.network.model.UserFilter;
import kh.com.psnd.ui.view.SearchUserBarView;
import lombok.val;

public class SearchUserFragment extends BaseFragment<FragmentSearchUserBinding> {

    @Override
    public void setupUI() {
        binding.addNewUser.setOnClickListener(__ -> {
            val fragment = new AddUserFragment();
            fragment.show(getParentFragmentManager(), "");
        });
        binding.searchBar.setupUI(this, callback);
        binding.searchResult.setupUI(this, binding.searchBar);
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
            Log.i("Search : " + search);
            if (TextUtils.isEmpty(search)) {
                // todo do something here
                return;
            }
            if (!ApplicationUtil.isOnline()) {
                Snackbar.make(getView(), R.string.noInternetConnection, Snackbar.LENGTH_SHORT).show();
                return;
            }
            binding.searchResult.doSearch(search.toString());
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
        return R.layout.fragment_search_user;
    }


}