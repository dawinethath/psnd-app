package kh.com.psnd.ui.fragment;

import android.os.Bundle;

import core.lib.base.BaseBottomSheetDialogFragment;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentSelectSectionBinding;
import kh.com.psnd.ui.adapter.DepartmentAdapter;
import lombok.val;

public class SelectSectionFragment extends BaseBottomSheetDialogFragment<FragmentSelectSectionBinding> {

    private DepartmentAdapter adapter = null;

    public static SelectSectionFragment newInstance() {
        val fragment = new SelectSectionFragment();
        val bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setupUI() {
        adapter = new DepartmentAdapter(this);
        binding.recyclerView.setupUI(null);
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_select_section;
    }

}