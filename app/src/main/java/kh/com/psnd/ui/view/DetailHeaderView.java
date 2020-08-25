package kh.com.psnd.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import core.lib.base.BaseFragment;
import kh.com.psnd.databinding.LayoutDetailHeaderBinding;
import kh.com.psnd.network.model.Staff;

public class DetailHeaderView extends FrameLayout {

    private       LayoutDetailHeaderBinding binding     = null;
    private final int                       BLUR_RADIUS = 25;

    public DetailHeaderView(@NonNull Context context) {
        super(context);
        init();
    }

    public DetailHeaderView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DetailHeaderView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        binding = LayoutDetailHeaderBinding.inflate(LayoutInflater.from(getContext()), this, true);
        if (!isInEditMode()) {
        }
    }

    public void setCurrentImageStaff(int position) {
        binding.imagesStaffViewPager.setCurrentItem(position);
    }

    public void setupUI(@NonNull BaseFragment baseFragment, @NonNull DetailHeaderToolbarView headerToolbarView, @NonNull Staff staff) {
        binding.imagesStaffViewPager.bind(baseFragment, headerToolbarView, staff);
        binding.firstNameKH.setText(staff.getFullName());
        binding.staffId.setText(staff.getId());
        binding.headerTitle.setText(staff.getGeneralCommissariat());
        binding.exportPdf.setOnClickListener(__ -> {
//            View view = fragment.getBinding().getRoot();
////            view.buildLayer();
////            view.buildDrawingCache();
//            Bitmap bitmap   = view.getDrawingCache();
//            String filePath = fragment.getActivity().getCacheDir().getPath() + File.separator + "test.jpg";
//            FileManager.saveImageJPG(bitmap, filePath);
        });

        {
            // Study blur image
            /*
            binding.layoutBlurImage.setVisibility(GONE);
            binding.blurImage.setVisibility(GONE);
            val bitmap = FrescoUtil.getBitmapFromCache(staff.getPhoto());
            if (bitmap != null) {
                val blurBitmap = BlurKit.getInstance().blur(bitmap, BLUR_RADIUS);
                if (blurBitmap != null) {
                    binding.blurImage.setImageBitmap(blurBitmap);
                    binding.blurImage.setVisibility(VISIBLE);
                    binding.layoutBlurImage.setVisibility(VISIBLE);
                }
            }
            */
        }

    }

    public void showProgress() {
        binding.progressBar.setVisibility(VISIBLE);
    }

    public void hideProgress() {
        binding.progressBar.setVisibility(GONE);
    }

}