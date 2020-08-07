package kh.com.psnd.ui.view;

import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import kh.com.psnd.databinding.ItemDetailSectionBinding;
import kh.com.psnd.network.model.StaffRecord;

public class ItemDetailSectionView extends FrameLayout {

    private ItemDetailSectionBinding binding = null;

    public ItemDetailSectionView(@NonNull Context context) {
        super(context);
        init();
    }

    public ItemDetailSectionView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ItemDetailSectionView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        binding = ItemDetailSectionBinding.inflate(LayoutInflater.from(getContext()), this, true);
    }

    public void bind(@NonNull StaffRecord staffRecord, @Nullable Callback callback) {
        binding.position.setText(staffRecord.getName());
        binding.docNumber.setText(staffRecord.getAnnounceNumber());
        binding.date.setText(staffRecord.getDateAnnounce());

        if (!TextUtils.isEmpty(staffRecord.getPdfUrl())) {
            binding.docNumber.setPaintFlags(binding.docNumber.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            binding.docNumber.setOnClickListener(__ -> callback.clickedDownloadPdf(staffRecord));
        }
    }

    public interface Callback {
        void clickedDownloadPdf(StaffRecord staffRecord);
    }
}