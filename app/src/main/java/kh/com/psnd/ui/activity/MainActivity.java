package kh.com.psnd.ui.activity;

import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import core.lib.base.BaseFragmentActivity;
import kh.com.psnd.R;
import kh.com.psnd.databinding.ActivityMainBinding;
import kh.com.psnd.helper.ActivityHelper;
import lombok.val;

public class MainActivity extends BaseFragmentActivity<ActivityMainBinding> {
    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(binding.appBarMain.toolbar);
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_search, R.id.nav_about)
                .setDrawerLayout(binding.drawerLayout)
                .build();
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        binding.logout.setOnClickListener(__ -> ActivityHelper.logout(this));
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }
}