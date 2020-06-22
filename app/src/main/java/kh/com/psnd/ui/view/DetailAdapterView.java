package kh.com.psnd.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import core.lib.base.BaseFragment;
import kh.com.psnd.R;
import kh.com.psnd.databinding.LayoutDetailAdapterBinding;
import kh.com.psnd.network.model.ItemDetail;
import lombok.val;

public class DetailAdapterView extends FrameLayout {

    private LayoutDetailAdapterBinding binding = null;

    public DetailAdapterView(@NonNull Context context) {
        super(context);
        init();
    }

    public DetailAdapterView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DetailAdapterView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        binding = LayoutDetailAdapterBinding.inflate(LayoutInflater.from(getContext()), this, true);
    }

    public void setupUI(@NonNull BaseFragment fragment, @IntRange(from = 1) @StringRes int labelHeader_1) {
        {
            val header_1   = getContext().getString(labelHeader_1);
            val header_2   = getContext().getString(R.string.detail_header_2);
            val header_3   = getContext().getString(R.string.detail_header_3);
            val itemDetail = new ItemDetail(header_1, header_2, header_3);

            val view = new ItemDetailView(getContext());
            view.bind(itemDetail);
            binding.recyclerView.addView(view);
        }

        for (int i = 0; i < 3; i++) {
            val view = new ItemDetailView(getContext());
            view.bind(null);
            binding.recyclerView.addView(view);
        }
    }

}