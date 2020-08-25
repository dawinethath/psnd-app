package kh.com.psnd.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.fragment.app.Fragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import core.lib.base.BaseFragmentActivity;
import core.lib.base.PagerAdapter;
import core.lib.listener.MyOnPageChangeListener;
import kh.com.psnd.R;
import kh.com.psnd.databinding.ActivityImagePreviewBinding;
import kh.com.psnd.eventbus.ImagePreviewSwipeEventBus;
import kh.com.psnd.network.model.Staff;
import kh.com.psnd.ui.fragment.ImagePreviewFragment;
import kh.com.psnd.ui.fragment.ImageStaffFragment;
import kh.com.psnd.ui.view.ImagesStaffViewPager;
import lombok.val;

public class ImagePreviewActivity extends BaseFragmentActivity<ActivityImagePreviewBinding> {

    private PagerAdapter   adapter   = null;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected int layoutId() {
        return R.layout.activity_image_preview;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        val staff    = (Staff) getIntent().getSerializableExtra(Staff.EXTRA);
        val position = getIntent().getIntExtra(ImageStaffFragment.POSITION, 0);

        val items = staff.getAlbum();
        if (items != null) {
            for (val item : items) {
                if (!TextUtils.isEmpty(item)) {
                    fragments.add(ImagePreviewFragment.newInstance(item));
                }
            }
            adapter = new PagerAdapter(context, getSupportFragmentManager(), fragments, null);
            binding.viewPager.setAdapter(adapter);
            binding.viewPager.addOnPageChangeListener(new MyOnPageChangeListener() {

                @Override
                public void onPageSelected(int position) {
                    binding.pageIndicatorView.setSelection(position);
                    if (!ImagesStaffViewPager.USED_SINGLE_PHOTO) {
                        EventBus.getDefault().postSticky(new ImagePreviewSwipeEventBus(staff, position));
                    }
                }

            });
            binding.viewPager.setCurrentItem(position);

            if (ImagesStaffViewPager.USED_SINGLE_PHOTO && items.size() > 1) {
                binding.changeStaffPhoto.setVisibility(View.VISIBLE);
                binding.changeStaffPhoto.setOnClickListener(__ -> {
                    EventBus.getDefault().postSticky(new ImagePreviewSwipeEventBus(staff, binding.viewPager.getCurrentItem()));
                    finish();
                });
            }
        }
    }

}