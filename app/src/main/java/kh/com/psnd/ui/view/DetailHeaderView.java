package kh.com.psnd.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;

import core.lib.base.BaseFragment;
import core.lib.utils.FrescoUtil;
import core.lib.utils.Log;
import kh.com.psnd.databinding.LayoutDetailHeaderBinding;
import kh.com.psnd.helper.ActivityHelper;
import kh.com.psnd.network.model.Staff;
import lombok.val;

public class DetailHeaderView extends FrameLayout {

    private LayoutDetailHeaderBinding binding = null;

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

    public void setupUI(@NonNull BaseFragment fragment, @NonNull Staff staff) {
        val bitmap = FrescoUtil.getBitmapFromCache(staff.getPhoto());
        if (bitmap != null) {
            binding.imageProfile.setImageURI(staff.getPhoto());
            binding.cardImageProfile.setOnClickListener(__ -> ActivityHelper.openImagePreviewActivity(getContext(), staff.getPhoto()));
        }
        else {
            FrescoUtil.loadImage(Uri.parse(staff.getPhoto()), new BaseBitmapDataSubscriber() {
                @Override
                public void onNewResultImpl(@Nullable Bitmap bitmap) {
                    binding.imageProfile.setImageURI(staff.getPhoto());
                    binding.cardImageProfile.setOnClickListener(__ -> ActivityHelper.openImagePreviewActivity(getContext(), staff.getPhoto()));
                }

                @Override
                public void onFailureImpl(DataSource dataSource) {
                    Log.i("dataSource : " + dataSource);
                }

            });
        }

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
        if (staff.getAlbum() == null || staff.getAlbum().size() <= 1) {
            binding.album.setVisibility(GONE);
            binding.album.setText("");
        }
        else {
            binding.album.setVisibility(VISIBLE);
            val album = String.format("%s/%s", 1, staff.getAlbum().size());
            binding.album.setText(album);
        }
    }

    public void showProgress() {
        binding.progressBar.setVisibility(VISIBLE);
    }

    public void hideProgress() {
        binding.progressBar.setVisibility(GONE);
    }

}