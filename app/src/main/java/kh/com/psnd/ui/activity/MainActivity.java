package kh.com.psnd.ui.activity;

import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import core.lib.base.BaseFragmentActivity;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.databinding.ActivityMainBinding;
import kh.com.psnd.helper.ActivityHelper;

public class MainActivity extends BaseFragmentActivity<ActivityMainBinding> {
    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    private AppBarConfiguration mAppBarConfiguration;
    private NavController       navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(binding.appBarMain.toolbar);
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_search, R.id.nav_about)
                .setDrawerLayout(binding.drawerLayout)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        binding.logout.setOnClickListener(__ -> ActivityHelper.logout(this));
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        Log.i("");
        if (navController.getCurrentDestination().getId() != R.id.nav_search) {
            navController.popBackStack(navController.getCurrentDestination().getId(), true);
            navController.navigate(R.id.nav_search);
        }
        else {
            super.onBackPressed();
        }
    }
}