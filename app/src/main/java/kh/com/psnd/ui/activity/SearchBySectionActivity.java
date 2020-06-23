package kh.com.psnd.ui.activity;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import core.lib.base.BaseFragmentActivity;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.databinding.ActivitySearchBySectionBinding;
import kh.com.psnd.mock.MockDepartment;
import kh.com.psnd.ui.view.AutoCompleteDropdownView;

public class SearchBySectionActivity extends BaseFragmentActivity<ActivitySearchBySectionBinding> {

    @Override
    protected int layoutId() {
        return R.layout.activity_search_by_section;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.toolbar.setTitle(R.string.search_detail_label_1);
        binding.searchSelect1.setupUI(MockDepartment.getDepartment(), new AutoCompleteDropdownView.Callback() {
            @Override
            public void onSelected(Object object) {
                Log.i(object);
            }
        });
    }

}