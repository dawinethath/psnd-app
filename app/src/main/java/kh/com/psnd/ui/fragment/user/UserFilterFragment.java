package kh.com.psnd.ui.fragment.user;

import android.content.Context;

import androidx.annotation.Nullable;

import core.lib.dialog.BaseDialog;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentUserFilterBinding;
import kh.com.psnd.network.model.UserFilter;
import kh.com.psnd.network.model.UserRolePrivilege;
import lombok.Setter;

public class UserFilterFragment extends BaseDialog<FragmentUserFilterBinding> {

    @Setter
    private Callback callback = null;

    public UserFilterFragment(@androidx.annotation.NonNull Context context, @NonNull Callback callback, @Nullable CompositeDisposable compositeDisposable) {
        super(context, compositeDisposable);
        this.callback = callback;
    }

    public void show(@androidx.annotation.NonNull UserFilter filter, @androidx.annotation.NonNull UserRolePrivilege rolePrivilege) {
//        checkLastFilter(filter);
        super.show();
    }

    @Override
    public void show() {
        checkLastFilter();
        super.show();
    }

    @Override
    public void setupUI() {

    }

    private void checkLastFilter() {
//        val filter = StaffFilter.getLastFilter();
//        if (filter != null) {
//            checkLastFilter(filter);
//        }
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_user_filter;
    }

    public interface Callback {
        void onSearch(UserFilter filter);
    }
}