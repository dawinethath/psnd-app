package kh.com.psnd.ui.fragment;

import core.lib.base.BaseFragment;
import core.lib.utils.ApplicationUtil;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentAboutBinding;

public class AboutFragment extends BaseFragment<FragmentAboutBinding> {

    @Override
    public void setupUI() {
        String version = "v" + ApplicationUtil.getVersionApp(getContext());
        binding.version.setText(version);
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_about;
    }
}