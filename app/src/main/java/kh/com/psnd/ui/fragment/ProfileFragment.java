package kh.com.psnd.ui.fragment;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
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
import kh.com.psnd.network.model.LoginProfile;
import lombok.val;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding> {

    private LoginProfile profile    = LoginManager.getUserProfile();
    private Goldfinger   goldfinger = null;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void setupUI() {
        Log.i(profile);

        goldfinger = new Goldfinger.Builder(getContext()).logEnabled(BuildConfig.DEBUG).build();

        val fullName = (profile != null && profile.getStaff() != null) ? profile.getStaff().getFullName() : "";
        val image    = profile.getStaff() == null ? null : profile.getStaff().getPhoto();

        binding.fullName.setText(fullName);
        binding.username.setText(profile.getUsername());
        binding.password.setOnClickListener(__ -> {
            val fragment = StaffChangePasswordFragment.newInstance();
            fragment.show(getBaseFragmentActivity().getSupportFragmentManager(), "");
        });
        binding.imageProfile.setImageURI(image);
        binding.language.setOnClickListener(__ -> doChangeLanguage());
        binding.autoLogout.setOnClickListener(__ -> doAutoLogout());

        updateUI();

        binding.btnViewPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        binding.password.setText(profile.getPwd());
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_HOVER_EXIT:
                        binding.password.setText("******");
                        break;
                }
                return true;
            }
        });
        binding.groupSecurity.setVisibility(goldfinger.canAuthenticate() ? View.VISIBLE : View.GONE);
        binding.fingerprint.setChecked(profile.isEnabledFingerprint());
        binding.fingerprint.setOnCheckedChangeListener(onCheckedChangeListener);

        if (profile.isEnabledFingerprint() && goldfinger.canAuthenticate()) {
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
        binding.language.setText(languages[profile.getLanguage()]);

        val autoLogout    = getResources().getStringArray(R.array.autoLogout);
        val strAutoLogout = getString(R.string.auto_logout_after) + " " + autoLogout[profile.getAutoLogout()];
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
                                profile.setEnabledFingerprint(true);
                                LoginManager.updateUserProfile(profile);
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
                profile.setEnabledFingerprint(false);
                LoginManager.updateUserProfile(profile);
                binding.fingerprint.setOnCheckedChangeListener(onCheckedChangeListener);
            }
        }
    };

    private void doAutoLogout() {
        new MaterialAlertDialogBuilder(getContext())
                .setTitle(R.string.auto_logout_after)
                .setSingleChoiceItems(R.array.autoLogout, profile.getAutoLogout(), (dialogInterface, position) -> {
                    profile.setAutoLogout(position);
                    LoginManager.updateUserProfile(profile);
                    updateUI();
                    dialogInterface.dismiss();
                })
                .show();
    }

    private void doChangeLanguage() {
        new MaterialAlertDialogBuilder(getContext())
                .setTitle(R.string.language_choice)
                .setSingleChoiceItems(R.array.languages, profile.getLanguage(), (dialogInterface, position) -> {
                    profile.setLanguage(position);
                    LoginManager.updateUserProfile(profile);
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