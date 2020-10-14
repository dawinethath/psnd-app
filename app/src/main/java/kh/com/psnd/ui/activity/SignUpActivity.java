package kh.com.psnd.ui.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import core.lib.base.BaseFragmentActivity;
import core.lib.base.PagerAdapter;
import core.lib.listener.MyOnPageChangeListener;
import core.lib.utils.ApplicationUtil;
import kh.com.psnd.R;
import kh.com.psnd.databinding.ActivitySignupBinding;
import kh.com.psnd.network.model.Staff;
import kh.com.psnd.ui.fragment.user.SignUpStep1Fragment;
import kh.com.psnd.ui.fragment.user.SignUpStep2Fragment;
import kh.com.psnd.ui.fragment.user.SignUpStep3Fragment;
import lombok.val;

public class SignUpActivity extends BaseFragmentActivity<ActivitySignupBinding> {

    private PagerAdapter   adapter   = null;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected int layoutId() {
        return R.layout.activity_signup;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragments.add(SignUpStep1Fragment.newInstance());
        fragments.add(SignUpStep2Fragment.newInstance());
        fragments.add(SignUpStep3Fragment.newInstance());

        adapter = new PagerAdapter(context, getSupportFragmentManager(), fragments, null);
        binding.viewPager.setOffscreenPageLimit(fragments.size());
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.addOnPageChangeListener(new MyOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                    case 1: {
                        val fragment = ((SignUpStep3Fragment) adapter.getItem(2));
                        fragment.stopCircleProgress();
                    }
                    break;
                    case 2:
                        break;
                }
            }
        });
    }

    public void goToFirstScreen() {
        binding.viewPager.setCurrentItem(0);
        ApplicationUtil.dismissKeyboard(this);
    }

    public void goToSecondScreen(@NonNull Staff staff, @NonNull String username, @NonNull String password) {
        val fragment = ((SignUpStep2Fragment) adapter.getItem(1));
        fragment.updateUI(staff, username, password);
        binding.viewPager.setCurrentItem(1);
        ApplicationUtil.dismissKeyboard(this);
    }

    public void goToSecondScreen() {
        binding.viewPager.setCurrentItem(1);
        ApplicationUtil.dismissKeyboard(this);
    }

    public void goToThirdScreen(@NonNull Staff staff, @NonNull String username, @NonNull String password, @NonNull String phoneNumber) {
        binding.viewPager.setCurrentItem(2);
        val fragment = ((SignUpStep3Fragment) adapter.getItem(2));
        fragment.updateUI(staff, username, password, phoneNumber);
        fragment.runCircleProgress();
        ApplicationUtil.dismissKeyboard(this);
    }
}