package kh.com.psnd.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;

import core.lib.base.BaseFragment;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentDetailProfile1Binding;
import kh.com.psnd.network.model.Search;
import lombok.val;

public class DetailProfile_1_Fragment extends BaseFragment<FragmentDetailProfile1Binding> {

    public static DetailProfile_1_Fragment newInstance(@NonNull Search search) {
        val fragment = new DetailProfile_1_Fragment();
        val bundle   = new Bundle();
        bundle.putSerializable(Search.EXTRA, search);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setupUI() {

    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_detail_profile_1;
    }

}