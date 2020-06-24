package kh.com.psnd.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;

import core.lib.base.BaseFragment;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentDetailBinding;
import kh.com.psnd.network.model.Search;
import lombok.val;

public class DetailFragment extends BaseFragment<FragmentDetailBinding> {

    public static DetailFragment newInstance(@NonNull Search search) {
        val fragment = new DetailFragment();
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

        binding.adapterView1.setupUI(this, R.string.detail_label_1);
        binding.adapterView2.setupUI(this, R.string.detail_label_2);
        binding.adapterView3.setupUI(this, R.string.detail_label_change_position);
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_detail;
    }

}