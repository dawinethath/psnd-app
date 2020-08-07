package kh.com.psnd.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import kh.com.psnd.databinding.ItemDetailSectionHeaderBinding;

public class ItemDetailSectionHeaderView extends FrameLayout {

    private ItemDetailSectionHeaderBinding binding = null;

    public ItemDetailSectionHeaderView(@NonNull Context context) {
        super(context);
        init();
    }

    public ItemDetailSectionHeaderView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ItemDetailSectionHeaderView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        binding = ItemDetailSectionHeaderBinding.inflate(LayoutInflater.from(getContext()), this, true);
    }

    public void bind(@IntRange(from = 1) @StringRes int... headers) {
        for (int i = 0; i < headers.length; i++) {
            switch (i) {
                case 0:
                    binding.headerTitle1.setText(headers[0]);
                    break;
                case 1:
                    binding.headerTitle2.setText(headers[1]);
                    break;
                case 2:
                    binding.headerTitle3.setText(headers[2]);
                    break;
            }
        }
    }

}