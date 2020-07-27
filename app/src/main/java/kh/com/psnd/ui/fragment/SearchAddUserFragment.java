package kh.com.psnd.ui.fragment;

import android.content.Context;

import androidx.annotation.Nullable;

import core.lib.dialog.BaseDialog;
import core.lib.utils.Log;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentSearchAddUserBinding;
import kh.com.psnd.network.model.Search;
import lombok.Getter;
import lombok.Setter;
import lombok.val;

public class SearchAddUserFragment extends BaseDialog<FragmentSearchAddUserBinding> {

    @Setter
    @Getter
    private Callback callback = null;

    public SearchAddUserFragment(@androidx.annotation.NonNull Context context, @NonNull Callback callback, @Nullable CompositeDisposable compositeDisposable) {
        super(context, compositeDisposable);
        this.callback = callback;
    }

    @Override
    public void setupUI() {

    }

    @Override
    public void show() {
        val fragment = fragmentManager.findFragmentById(R.id.searchAddUserFragment);
        if (fragment != null) {
            ((SearchFragment) fragment).setParentCallBack(this);
        }
        super.show();
    }

    public void onDestroy() {
        val fragment = fragmentManager.findFragmentById(R.id.searchAddUserFragment);
        Log.i("fragment : " + fragment);
        if (fragment != null) {
            fragmentManager.beginTransaction().remove(fragment).commit();
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_search_add_user;
    }

    public interface Callback {
        void onSearch(Search search);
    }
}