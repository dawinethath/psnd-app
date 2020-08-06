package kh.com.psnd.ui.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;

import core.lib.listener.MyTextWatcher;
import core.lib.utils.ApplicationUtil;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.databinding.LayoutButtonPasswordBinding;
import kh.com.psnd.utils.PasswordGeneratorUtil;
import lombok.val;

public class ButtonPasswordView extends FrameLayout {

    private LayoutButtonPasswordBinding binding = null;

    public ButtonPasswordView(@NonNull Context context) {
        super(context);
        init();
    }

    public ButtonPasswordView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ButtonPasswordView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        binding = LayoutButtonPasswordBinding.inflate(LayoutInflater.from(getContext()), this, true);
        if (!isInEditMode()) {
            binding.btnGenerate.setOnClickListener(__ -> {
                val pwd = PasswordGeneratorUtil.getPasswordGenerator().generate(PasswordGeneratorUtil.PWD_LENGTH);
                binding.password.setTextWithEndCursor(pwd);
            });
            binding.textInputLayoutPassword.setEndIconOnClickListener(__ -> {
                val pwd = binding.password.getText().toString();
                if (!TextUtils.isEmpty(pwd)) {
                    ApplicationUtil.copyPassword(pwd);
                }
            });
            binding.password.addTextChangedListener(new MyTextWatcher() {
                @Override
                public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                    super.onTextChanged(charSequence, start, before, count);
                    isValidPassword();
                }
            });
        }
    }

    public void addTextChangedListener(MyTextWatcher textWatcher) {
        binding.password.addTextChangedListener(textWatcher);
    }

    public boolean isValidPassword() {
        val pwd    = binding.password.getText().toString();
        val length = pwd.length();
        Log.i("isValidPassword -> length : " + length);
        switch (length) {
            case PasswordGeneratorUtil.PWD_LENGTH:
                binding.textInputLayoutPassword.setError(null);
                return true;
            case 0:
                binding.textInputLayoutPassword.setError(getContext().getString(R.string.password_error));
                return false;
            default:
                binding.textInputLayoutPassword.setError(getContext().getString(R.string.password_error_incorrect));
                return false;
        }
    }

    public void showError(String msg) {

    }

    public String getPassword() {
        return binding.password.getText().toString();
    }

    public interface Callback {

    }

    @BindingAdapter("enabled_generate")
    public static void hint(ButtonPasswordView view, boolean enabledGenerate) {
        view.binding.btnGenerate.setVisibility(enabledGenerate ? VISIBLE : GONE);
    }
}