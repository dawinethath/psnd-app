package core.lib.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import core.lib.utils.ApplicationUtil;
import core.lib.utils.Log;
import io.reactivex.disposables.CompositeDisposable;
import kmobile.logger.R;

public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {

    public static final String              PAGE_UNKNOWN         = "Unknown";
    private             CompositeDisposable mCompositeDisposable = null;
    public              boolean             isAlive              = true;
    protected           T                   binding              = null;

    public T getBinding() {
        return binding;
    }

    public BaseFragmentActivity getBaseFragmentActivity() {
        if (getContext() instanceof BaseFragmentActivity) {
            return (BaseFragmentActivity) getContext();
        }
        else {
            return null;
        }
    }

    public CompositeDisposable getCompositeDisposable() {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        return mCompositeDisposable;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (layoutId() == 0) {
            throw new RuntimeException("You need to setup layout ID");
        }
        binding = DataBindingUtil.inflate(inflater, layoutId(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupUI();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    @Override
    public void onDestroyView() {
        ApplicationUtil.dismissKeyboard(getActivity());
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        isAlive = true;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupToolbar();
    }

    protected void setCurrentFragment(Fragment fragment) {
        if (getActivity() != null && getActivity() instanceof BaseFragmentActivity) {
            getBaseFragmentActivity().currentFragment = fragment;
        }
    }

    @Override
    public void onDestroy() {
        Log.i("[onDestroy]");
        isAlive = false;
        super.onDestroy();
    }

    public void showContentFragment(Fragment fragment, String whatPage, boolean clearBackStack, boolean showAnimate) {
        ((BaseFragmentActivity) getActivity()).showContentFragment(fragment, whatPage, clearBackStack, showAnimate);
    }

    public void showContentFragment(Fragment fragment, String whatPage, boolean clearBackStack) {
        ((BaseFragmentActivity) getActivity()).showContentFragment(fragment, whatPage, clearBackStack);
    }

    public void hideToolbar() {
        if (getBaseFragmentActivity().getSupportActionBar() != null) {
            getBaseFragmentActivity().getSupportActionBar().hide();
        }
    }

    public void showToolbar() {
        if (getBaseFragmentActivity().getSupportActionBar() != null) {
            getBaseFragmentActivity().getSupportActionBar().show();
        }
    }

    private void setupToolbar() {
        if (binding != null && binding.getRoot().findViewById(R.id.toolbar) != null) {
//            txtTitle = mBinding.getRoot().findViewById(R.id.txtTitle);
//            txtSubTitle = mBinding.getRoot().findViewById(R.id.txtSubTitle);
        }
        initToolbar();
    }

    /**
     * This method will be called from onActivityCreated()
     */
    abstract public void setupUI();

    abstract protected void initToolbar();

    abstract protected int layoutId();
}