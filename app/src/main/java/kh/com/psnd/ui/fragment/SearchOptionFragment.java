package kh.com.psnd.ui.fragment;

import core.lib.base.BaseFragment;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentSearchOptionBinding;
import kh.com.psnd.mock.MockDepartment;
import kh.com.psnd.ui.view.AutoCompleteDropdownView;

public class SearchOptionFragment extends BaseFragment<FragmentSearchOptionBinding> {

    @Override
    public void setupUI() {
        binding.toolbar.setTitle(R.string.search_detail_label_1);
        binding.searchSelect1.setupUI(MockDepartment.getDepartment(), new AutoCompleteDropdownView.Callback() {
            @Override
            public void onSelected(Object object) {
                Log.i(object);
            }
        });
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_search_option;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}