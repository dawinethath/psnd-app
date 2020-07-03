package kh.com.psnd.ui.fragment;

import android.text.TextUtils;

import com.google.android.material.snackbar.Snackbar;

import core.lib.base.BaseFragment;
import core.lib.utils.ApplicationUtil;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentSearchBinding;
import kh.com.psnd.ui.view.SearchBarView;

public class SearchFragment extends BaseFragment<FragmentSearchBinding> {

    @Override
    public void setupUI() {
        binding.searchBar.setupUI(this, callback);
        binding.searchResult.setupUI(this, binding.searchBar.getBinding());
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

}