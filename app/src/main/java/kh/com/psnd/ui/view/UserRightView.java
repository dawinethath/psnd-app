package kh.com.psnd.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import kh.com.psnd.databinding.LayoutUserRightBinding;
import kh.com.psnd.ui.fragment.AddUserFragment;
import kh.com.psnd.ui.fragment.SelectUserRightFragment;
import lombok.val;

public class UserRightView extends FrameLayout {

    private LayoutUserRightBinding binding = null;

    public UserRightView(@NonNull Context context) {
        super(context);
        init();
    }

    public UserRightView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public UserRightView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        binding = LayoutUserRightBinding.inflate(LayoutInflater.from(getContext()), this, true);

        if (isInEditMode()) {

        }
    }

    public void setupUI(@NonNull AddUserFragment fragment) {
        binding.btnEdit.setOnClickListener(__ -> {
            val selectUserRightFragment = SelectUserRightFragment.newInstance();
            selectUserRightFragment.show(fragment.getActivity().getSupportFragmentManager(), "");
        });
    }

}