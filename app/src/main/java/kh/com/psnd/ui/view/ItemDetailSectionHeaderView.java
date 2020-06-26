package kh.com.psnd.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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

    public void bind(@NonNull String header) {
        binding.headerPosition.setText(header);
    }

}