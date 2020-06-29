package kh.com.psnd.ui.fragment;

import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.zoomable.DoubleTapGestureListener;

import core.lib.base.BaseFragment;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentImagePreviewBinding;
import kh.com.psnd.ui.activity.ImagePreviewActivity;
import lombok.val;

public class ImagePreviewFragment extends BaseFragment<FragmentImagePreviewBinding> {

    @Override
    public void setupUI() {
        val url = getActivity().getIntent().getStringExtra(ImagePreviewActivity.EXTRA_IMAGE_URI);
        val controller = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse(url))
                .setCallerContext("ZoomableApp")
                .build();
        binding.image.setAllowTouchInterceptionWhileZoomed(true);
        binding.image.setTapListener(new DoubleTapGestureListener(binding.image));
        binding.image.setController(controller);
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_image_preview;
    }
}