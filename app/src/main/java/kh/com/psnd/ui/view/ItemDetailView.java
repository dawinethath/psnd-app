package kh.com.psnd.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import kh.com.psnd.databinding.ItemDetailBinding;
import kh.com.psnd.network.model.ItemDetail;

public class ItemDetailView extends FrameLayout {

    private ItemDetailBinding binding = null;

    public ItemDetailView(@NonNull Context context) {
        super(context);
        init();
    }

    public ItemDetailView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ItemDetailView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        binding = ItemDetailBinding.inflate(LayoutInflater.from(getContext()), this, true);
    }

    public void bind(@NonNull ItemDetail itemDetail) {
        if (itemDetail != null) {
            binding.position.setText(itemDetail.getLabel());
            binding.docNumber.setText(itemDetail.getDocNumber());
            binding.date.setText(itemDetail.getDate());
        }
    }

}