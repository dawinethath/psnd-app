package kh.com.psnd.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;

import core.lib.base.BaseFragment;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentDetailV2Binding;
import kh.com.psnd.network.model.Search;
import lombok.val;

public class Detail_v2_Fragment extends BaseFragment<FragmentDetailV2Binding> {

    public static Detail_v2_Fragment newInstance(@NonNull Search search) {
        val fragment = new Detail_v2_Fragment();
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

    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_detail_v2;
    }

}