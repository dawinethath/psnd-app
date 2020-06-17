package kh.com.psnd.ui.fragment;

import core.lib.base.BaseFragment;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentSearchBinding;
import kh.com.psnd.ui.view.SearchBarView;

public class SearchFragment extends BaseFragment<FragmentSearchBinding> {

    @Override
    public void setupUI() {
        binding.searchBar.setupUI(this, new SearchBarView.Callback() {
            @Override
            public void onResult() {
                // todo upload result list
            }

            @Override
            public void onClickedClear() {
                Log.i("onClickedClear");
                // todo implement here
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

    @Override
    public void onPause() {
        super.onPause();
        binding.searchBar.onPause();
    }
}