package kh.com.psnd.ui.activity;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import core.lib.base.BaseFragmentActivity;
import core.lib.base.PagerAdapter;
import kh.com.psnd.R;
import kh.com.psnd.databinding.ActivitySearchBySectionBinding;
import kh.com.psnd.ui.fragment.SelectSectionFragment;

public class SearchBySectionActivity extends BaseFragmentActivity {

    private ActivitySearchBySectionBinding binding;
    private List<Fragment> fragments = new ArrayList<>();
    private PagerAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_by_section);

        fragments.add(SelectSectionFragment.newInstance());
        fragments.add(SelectSectionFragment.newInstance());
        fragments.add(SelectSectionFragment.newInstance());
        fragments.add(SelectSectionFragment.newInstance());
        fragments.add(SelectSectionFragment.newInstance());
        fragments.add(SelectSectionFragment.newInstance());
        fragments.add(SelectSectionFragment.newInstance());
        fragments.add(SelectSectionFragment.newInstance());

        adapter = new PagerAdapter(context, getSupportFragmentManager(), fragments, null);
        binding.viewPager.setAdapter(adapter);
        binding.toolbar.setTitle(R.string.general_department);
    }

}