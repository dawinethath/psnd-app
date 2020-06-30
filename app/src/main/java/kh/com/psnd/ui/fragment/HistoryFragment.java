package kh.com.psnd.ui.fragment;

import core.lib.base.BaseFragment;
import core.lib.dialog.DialogProgress;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentHistoryBinding;
import lombok.val;

public class HistoryFragment extends BaseFragment<FragmentHistoryBinding> {
    private DialogProgress progress = null;

    public static HistoryFragment newInstance() {
        val fragment = new HistoryFragment();
        return fragment;
    }

    @Override
    public void setupUI() {

    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_history;
    }
}