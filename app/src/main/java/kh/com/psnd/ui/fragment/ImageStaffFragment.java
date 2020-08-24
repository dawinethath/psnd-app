package kh.com.psnd.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;

import core.lib.base.BaseFragment;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentImageStaffBinding;
import kh.com.psnd.helper.ActivityHelper;
import kh.com.psnd.network.model.Staff;
import lombok.val;

public class ImageStaffFragment extends BaseFragment<FragmentImageStaffBinding> {

    public static final String POSITION = "position";

    public static ImageStaffFragment newInstance(@NonNull Staff staff, int position) {
        val fragment = new ImageStaffFragment();
        val bundle   = new Bundle();
        bundle.putSerializable(Staff.EXTRA, staff);
        bundle.putSerializable(POSITION, position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setupUI() {
        val staff    = (Staff) getArguments().getSerializable(Staff.EXTRA);
        val position = getArguments().getInt(POSITION, 0);
        binding.imageProfile.setImageURI(staff.getAlbum().get(position));
        binding.imageProfile.setOnClickListener(__ -> ActivityHelper.openImagePreviewActivity(getContext(), staff, position));
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_image_staff;
    }

}