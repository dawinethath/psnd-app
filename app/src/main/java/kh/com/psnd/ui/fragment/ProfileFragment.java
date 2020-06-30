package kh.com.psnd.ui.fragment;

import android.view.View;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import co.infinum.goldfinger.Goldfinger;
import core.lib.base.BaseFragment;
import core.lib.utils.Log;
import kh.com.psnd.BuildConfig;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentProfileBinding;
import kh.com.psnd.helper.FingerPrintManager;
import kh.com.psnd.helper.LoginManager;
import kh.com.psnd.mock.MockData;
import kh.com.psnd.network.model.UserProfile;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding> implements Goldfinger.Callback {

    private UserProfile userProfile = LoginManager.getUserProfile();
    private Goldfinger  goldfinger  = null;

    @Override
    public void setupUI() {
        Log.i(userProfile);

        goldfinger = new Goldfinger.Builder(getContext()).logEnabled(BuildConfig.DEBUG).build();

        binding.username.setText(userProfile.getUsername());
        binding.userId.setText(userProfile.getId());
        binding.imageProfile.setImageURI(MockData.TEST_IMAGE);
        binding.language.setOnClickListener(__ -> doChangeLanguage());
        binding.autoLogout.setOnClickListener(__ -> doAutoLogout());

        binding.fingerprint.setChecked(userProfile.isEnabledFingerprint());
        binding.fingerprint.setOnCheckedChangeListener(onCheckedChangeListener);
        binding.fingerprint.setVisibility(goldfinger.canAuthenticate() ? View.VISIBLE : View.GONE);
    }

    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            binding.fingerprint.setOnCheckedChangeListener(null);
            if (goldfinger.canAuthenticate() && isChecked) {
                binding.fingerprint.setChecked(false);
                goldfinger.authenticate(FingerPrintManager.getPromptParams(ProfileFragment.this), ProfileFragment.this);
            }
            else {
                userProfile.setEnabledFingerprint(false);
                LoginManager.updateUserProfile(userProfile);
                binding.fingerprint.setOnCheckedChangeListener(onCheckedChangeListener);
            }
        }
    };

    private void doAutoLogout() {
        Log.i("");
    }

    private void doChangeLanguage() {
        Log.i("");
    }

    @Override
    public void onResult(@NonNull Goldfinger.Result result) {
        Log.i(new Gson().toJson(result));
        binding.fingerprint.setOnCheckedChangeListener(null);
        switch (result.reason()) {
            case AUTHENTICATION_SUCCESS:
                userProfile.setEnabledFingerprint(true);
                LoginManager.updateUserProfile(userProfile);
                binding.fingerprint.setChecked(true);
                break;
        }
        binding.fingerprint.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    @Override
    public void onError(@NonNull Exception e) {
        Log.e(e);
        binding.fingerprint.setChecked(false);
        binding.fingerprint.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_profile;
    }

}