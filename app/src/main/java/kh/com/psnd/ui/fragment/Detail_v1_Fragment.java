package kh.com.psnd.ui.fragment;

import android.os.Bundle;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import core.lib.base.BaseFragment;
import core.lib.base.PagerAdapter;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentDetailV1Binding;
import kh.com.psnd.network.model.Search;
import lombok.val;

public class Detail_v1_Fragment extends BaseFragment<FragmentDetailV1Binding> {

    private PagerAdapter   adapter;
    @ArrayRes
    private List<Integer>  titles    = new ArrayList<>(
            Arrays.asList(
                    R.string.detail_label_tab_1,
                    R.string.detail_label_tab_2,
                    R.string.detail_label_tab_3,
                    R.string.detail_label_tab_4,
                    R.string.detail_label_tab_5,
                    R.string.detail_label_tab_6,
                    R.string.detail_label_tab_7
            ));
    private List<Fragment> fragments = new ArrayList<>();

    public static Detail_v1_Fragment newInstance(@NonNull Search search) {
        val fragment = new Detail_v1_Fragment();
        val bundle   = new Bundle();
        bundle.putSerializable(Search.EXTRA, search);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setupUI() {
        if (getArguments() != null) {
            val search = (Search) getArguments().getSerializable(Search.EXTRA);
            Log.i(search);
        }

        fragments.add(new DetailProfile_1_Fragment());
        fragments.add(new DetailProfile_2_Fragment());
        fragments.add(new DetailProfile_3_Fragment());
        fragments.add(new DetailProfile_4_Fragment());
        fragments.add(new DetailProfile_5_Fragment());
        fragments.add(new DetailProfile_6_Fragment());
        fragments.add(new DetailProfile_7_Fragment());

        adapter = new PagerAdapter(getContext(), getChildFragmentManager(), fragments, titles);
        binding.viewPager.setAdapter(adapter);
        binding.tab.setupWithViewPager(binding.viewPager);
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_detail_v1;
    }

}