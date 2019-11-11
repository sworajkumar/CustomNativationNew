package com.example.customnativation.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.customnativation.R;
import com.example.customnativation.fragment.AccountFragment;
import com.example.customnativation.fragment.ActivityFragment;
import com.example.customnativation.fragment.ArticleFragment;
import com.example.customnativation.fragment.DashBoardFragment;
import com.example.customnativation.fragment.EventFragment;
import com.example.customnativation.fragment.SearchFragment;
import com.example.customnativation.fragment.SettingFragment;
import com.example.customnativation.fragment.forumFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mtoggle;
    private BottomNavigationView bottomNavigationView;
    private NavigationView navigationview;
    private ImageView headerimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);//Botom Navigation View
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mtoggle = new ActionBarDrawerToggle(this, mdrawerLayout, R.string.open, R.string.close);
        mdrawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationview=(NavigationView)findViewById(R.id.navigationview);
        navigationview.setNavigationItemSelectedListener(this);

        View headerview=navigationview.getHeaderView(0);
        headerimage=headerview.findViewById(R.id.headerimage);
        // LoadImage();
        TextView name = (TextView) headerview.findViewById(R.id.name);
        TextView mail = (TextView) headerview.findViewById(R.id.mailid);


        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_content_Frame, new DashBoardFragment()).commit();
            navigationview.setCheckedItem(R.id.dashboard);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.bottom_nav_home:
                                getSupportFragmentManager().beginTransaction().replace(R.id.nav_content_Frame, new DashBoardFragment()).commit();
                                return true;
                        case R.id.bottom_nav_actical:
                            getSupportFragmentManager().beginTransaction().replace(R.id.nav_content_Frame, new ArticleFragment()).commit();
                            return true;
                        case R.id.bottom_nav_forum:
                            getSupportFragmentManager().beginTransaction().replace(R.id.nav_content_Frame, new forumFragment()).commit();
                            return true;
                        case R.id.bottom_nav_account://Bottom Navigation bottom right icon clicked
                            getSupportFragmentManager().beginTransaction().replace(R.id.nav_content_Frame, new AccountFragment()).commit();
                            return true;
                    }
                    return false;
                }
            };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mtoggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
}

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.dashboard) {
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_content_Frame, new DashBoardFragment()).commit();

        } else if (id == R.id.event) {
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_content_Frame, new EventFragment()).commit();

        } else if (id == R.id.search) {
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_content_Frame, new SearchFragment()).commit();

        } else if (id == R.id.setting) {
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_content_Frame, new SettingFragment()).commit();

        } else if (id == R.id.activities) {
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_content_Frame, new ActivityFragment()).commit();

        } else if (id == R.id.logout) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Are you sure you want to Logout?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }

        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mdrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
