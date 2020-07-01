package kh.com.psnd.ui.fragment;

import android.view.View;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
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
import lombok.val;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding> {

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

        updateUI();

        binding.groupSecurity.setVisibility(goldfinger.canAuthenticate() ? View.VISIBLE : View.GONE);
        binding.fingerprint.setChecked(userProfile.isEnabledFingerprint());
        binding.fingerprint.setOnCheckedChangeListener(onCheckedChangeListener);

        if (userProfile.isEnabledFingerprint() && goldfinger.canAuthenticate()) {
            binding.lockBackground.setVisibility(View.VISIBLE);
            goldfinger.authenticate(FingerPrintManager.getPromptParams(getBaseFragmentActivity()), new Goldfinger.Callback() {
                @Override
                public void onResult(@NonNull Goldfinger.Result result) {
                    Log.i(new Gson().toJson(result));
                    switch (result.reason()) {
                        case CANCELED:
                        case USER_CANCELED:
                        case TIMEOUT:
                        case NEGATIVE_BUTTON:
                        case LOCKOUT:
                            goldfinger.cancel();
                            getActivity().finish();
                            break;
                        case AUTHENTICATION_SUCCESS:
                            binding.lockBackground.setVisibility(View.GONE);
                            break;
                    }
                }

                @Override
                public void onError(@NonNull Exception e) {
                    getActivity().finish();
                }
            });
        }
    }

    private void updateUI() {

        val languages = getResources().getStringArray(R.array.languages);
        binding.language.setText(languages[userProfile.getLanguage()]);

        val autoLogout    = getResources().getStringArray(R.array.autoLogout);
        val strAutoLogout = getString(R.string.auto_logout_after) + " " + autoLogout[userProfile.getAutoLogout()];
        binding.autoLogout.setText(strAutoLogout);

    }

    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            binding.fingerprint.setOnCheckedChangeListener(null);
            if (goldfinger.canAuthenticate() && isChecked) {
                binding.fingerprint.setChecked(false);
                goldfinger.authenticate(FingerPrintManager.getPromptParams(getBaseFragmentActivity()), new Goldfinger.Callback() {
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
                });
            }
            else {
                userProfile.setEnabledFingerprint(false);
                LoginManager.updateUserProfile(userProfile);
                binding.fingerprint.setOnCheckedChangeListener(onCheckedChangeListener);
            }
        }
    };

    private void doAutoLogout() {
        new MaterialAlertDialogBuilder(getContext())
                .setTitle(R.string.auto_logout_after)
                .setSingleChoiceItems(R.array.autoLogout, userProfile.getAutoLogout(), (dialogInterface, position) -> {
                    userProfile.setAutoLogout(position);
                    LoginManager.updateUserProfile(userProfile);
                    updateUI();
                    dialogInterface.dismiss();
                })
                .show();
    }

    private void doChangeLanguage() {
        new MaterialAlertDialogBuilder(getContext())
                .setTitle(R.string.language_choice)
                .setSingleChoiceItems(R.array.languages, userProfile.getLanguage(), (dialogInterface, position) -> {
                    userProfile.setLanguage(position);
                    LoginManager.updateUserProfile(userProfile);
                    updateUI();
                    dialogInterface.dismiss();
                })
                .show();
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_profile;
    }

}