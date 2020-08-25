package kh.com.psnd.ui.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import core.lib.base.BaseFragment;
import core.lib.base.PagerAdapter;
import core.lib.listener.MyOnPageChangeListener;
import core.lib.utils.Log;
import kh.com.psnd.databinding.LayoutImagesStaffViewPagerBinding;
import kh.com.psnd.network.model.Staff;
import kh.com.psnd.ui.fragment.ImageStaffFragment;
import lombok.val;

public class ImagesStaffViewPager extends FrameLayout {

    private PagerAdapter   adapter   = null;
    private List<Fragment> fragments = new ArrayList<>();

    private LayoutImagesStaffViewPagerBinding binding = null;

    public ImagesStaffViewPager(@NonNull Context context) {
        super(context);
        init();
    }

    public ImagesStaffViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ImagesStaffViewPager(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        binding = LayoutImagesStaffViewPagerBinding.inflate(LayoutInflater.from(getContext()), this, true);
    }

    public void setCurrentItem(int position){
        binding.viewPager.setCurrentItem(position);
    }

    public void bind(@NonNull BaseFragment baseFragment, @NonNull DetailHeaderToolbarView headerToolbarView, @NonNull Staff staff) {
        fragments.clear();
        val items = staff.getAlbum();
        Log.i(staff.getFullName() + " : " + items);
        if (items != null) {
            Log.i("Total Images " + staff.getFullName() + " : " + items.size());
            for (val item : items) {
                if (!TextUtils.isEmpty(item)) {
                    fragments.add(ImageStaffFragment.newInstance(staff, items.indexOf(item)));
                }
            }
            binding.layoutIndicator.setVisibility(items.size() <= 1 ? GONE : VISIBLE);
        }
        binding.viewPager.clearOnPageChangeListeners();
        binding.viewPager.addOnPageChangeListener(new MyOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                Log.i("onPageSelected : " + position);
                binding.pageIndicatorView.setSelection(position);
                headerToolbarView.setImageProfile(items.get(position));
            }

        });
        adapter = new PagerAdapter(getContext(), baseFragment.getChildFragmentManager(), fragments, null);
        binding.viewPager.setAdapter(adapter);

//        {
//            val bitmap = FrescoUtil.getBitmapFromCache(staff.getPhoto());
//            if (bitmap != null) {
//                binding.imageProfile.setImageURI(staff.getPhoto());
//                binding.cardImageProfile.setOnClickListener(__ -> ActivityHelper.openImagePreviewActivity(getContext(), staff.getPhoto()));
//            }
//            else {
//                FrescoUtil.loadImage(Uri.parse(staff.getPhoto()), new BaseBitmapDataSubscriber() {
//                    @Override
//                    public void onNewResultImpl(@Nullable Bitmap bitmap) {
//                        binding.imageProfile.setImageURI(staff.getPhoto());
//                        binding.cardImageProfile.setOnClickListener(__ -> ActivityHelper.openImagePreviewActivity(getContext(), staff.getPhoto()));
//                    }
//
//                    @Override
//                    public void onFailureImpl(DataSource dataSource) {
//                        Log.i("dataSource : " + dataSource);
//                    }
//
//                });
//            }
//        }

    }

}