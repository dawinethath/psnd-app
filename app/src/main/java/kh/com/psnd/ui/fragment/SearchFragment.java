package kh.com.psnd.ui.fragment;

import core.lib.base.BaseFragment;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentSearchBinding;
import kh.com.psnd.ui.view.SearchBarView;

public class SearchFragment extends BaseFragment<FragmentSearchBinding> {

    @Override
    public void setupUI() {
        binding.searchBar.setupUI(this, new SearchBarView.Callback() {
            @Override
            public void onResult() {
                super.onResult();
            }
        });
        binding.searchResult.setupUI(this);
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_search;
    }
}