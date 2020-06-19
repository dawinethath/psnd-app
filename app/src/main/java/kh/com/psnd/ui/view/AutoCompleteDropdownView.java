package kh.com.psnd.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;

import java.util.List;

import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.databinding.LayoutAutocompleteDropdownBinding;

public class AutoCompleteDropdownView extends FrameLayout {

    private LayoutAutocompleteDropdownBinding binding  = null;
    private Callback                          callback = null;

    public AutoCompleteDropdownView(@NonNull Context context) {
        super(context);
        init();
    }

    public AutoCompleteDropdownView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AutoCompleteDropdownView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        binding = LayoutAutocompleteDropdownBinding.inflate(LayoutInflater.from(getContext()), this, true);
    }

    public <T> void setupUI(@NonNull List<T> list, @NonNull Callback callback) {
        ArrayAdapter<T> adapter = new ArrayAdapter<T>(getContext(), R.layout.dropdown_menu_popup_item, R.id.item, list);
        binding.dropdown.setAdapter(adapter);
        binding.dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i(adapterView.getSelectedItem());
            }
        });
    }

    @BindingAdapter("hint")
    public static void hint(AutoCompleteDropdownView view, String hint) {
        view.binding.textInputLayout.setHint(hint);
    }


    public interface Callback {
        void onSelected(Object object);
    }

}