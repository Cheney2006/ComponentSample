package com.lcfarm.android.componentsample;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lcfarm.android.account.NotificationsFragment;
import com.lcfarm.android.base.constants.ARouterConstant;
import com.lcfarm.android.base.utils.ARouterUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);


        //动态添加
//        NavHostFragment navHostFragment= (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
//        navController.getNavInflater().inflate(R.navigation.mobile_navigation);

        if (!BuildConfig.IS_HOME_APPLICATION) {
            Menu menu=navView.getMenu();
            menu.add(0,R.id.navigation_home,0,"首页");
            MenuItem menuItem=menu.findItem(R.id.navigation_home);
            menuItem.setIcon(R.drawable.ic_home_black_24dp);
        }

        if (!BuildConfig.IS_FIND_APPLICATION) {
            Menu menu=navView.getMenu();
            menu.add(0,R.id.navigation_dashboard,0,"发现");
            MenuItem menuItem=menu.findItem(R.id.navigation_dashboard);
            menuItem.setIcon(R.drawable.ic_dashboard_black_24dp);
        }

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.navigation_home:
                        //路由获取 Fragment
                        Fragment fragment = (Fragment)  ARouterUtil.navigation(ARouterConstant.FRAGMENT_HOME);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.content_layout, fragment)
                                .commitNow();
                        return true;
                    case  R.id.navigation_dashboard:
                        //路由获取 Fragment
                         fragment = (Fragment)  ARouterUtil.navigation(ARouterConstant.FRAGMENT_FIND);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.content_layout, fragment)
                                .commitNow();
                        return true;
                    case  R.id.navigation_notifications:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.content_layout, NotificationsFragment.newInstance(null,null))
                                .commitNow();
                        return true;
                }
                return false;
            }
        });


        if (savedInstanceState == null) {
            //路由获取 Fragment
            Fragment fragment = (Fragment)  ARouterUtil.navigation(ARouterConstant.FRAGMENT_HOME);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_layout, fragment)
                    .commitNow();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("main activity code=" + resultCode + ",requestCode=" + requestCode);
    }
}
