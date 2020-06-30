package kh.com.psnd.ui.fragment;

import core.lib.base.BaseFragment;
import core.lib.dialog.DialogProgress;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentProfileBinding;
import kh.com.psnd.helper.LoginManager;
import kh.com.psnd.mock.MockData;
import kh.com.psnd.network.model.UserProfile;
import lombok.val;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding> {
    private DialogProgress progress = null;

    public static ProfileFragment newInstance() {
        val fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void setupUI() {
        UserProfile profile = LoginManager.getUserProfile();
        Log.i(profile);

        binding.imageProfile.setImageURI(MockData.TEST_IMAGE);
        binding.language.setOnClickListener(__ -> Log.i(""));
        binding.autoLogout.setOnClickListener(__ -> Log.i(""));
        binding.fingerprint.setOnCheckedChangeListener((compoundButton, isChecked) -> Log.i("isChecked : " + isChecked));
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_profile;
    }
}