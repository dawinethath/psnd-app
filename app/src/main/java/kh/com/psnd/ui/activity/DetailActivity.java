package kh.com.psnd.ui.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import core.lib.base.BaseFragmentActivity;
import core.lib.base.PagerAdapter;
import kh.com.psnd.R;
import kh.com.psnd.databinding.ActivityDetailBinding;
import kh.com.psnd.network.model.Search;
import kh.com.psnd.ui.fragment.DetailFragment;
import lombok.val;

public class DetailActivity extends BaseFragmentActivity<ActivityDetailBinding> {

    private PagerAdapter   adapter   = null;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected int layoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        val position = getIntent().getIntExtra(Search.EXTRA_POSITION, 0);
        val items    = (List<Search>) getIntent().getSerializableExtra(Search.EXTRA_LIST);
        for (val item : items) {
            fragments.add(DetailFragment.newInstance(item));
        }

        adapter = new PagerAdapter(context, getSupportFragmentManager(), fragments, null);
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.setCurrentItem(position);
    }

}