package kh.com.psnd.ui.fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.zoomable.DoubleTapGestureListener;

import core.lib.base.BaseFragment;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentImagePreviewBinding;
import lombok.val;

public class ImagePreviewFragment extends BaseFragment<FragmentImagePreviewBinding> {

    private static final String IMAGE_URL = "imageUrl";

    public static ImagePreviewFragment newInstance(@NonNull String imageUrl) {
        val fragment = new ImagePreviewFragment();
        val bundle   = new Bundle();
        bundle.putSerializable(IMAGE_URL, imageUrl);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setupUI() {
        val url = getArguments().getString(IMAGE_URL, "");
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