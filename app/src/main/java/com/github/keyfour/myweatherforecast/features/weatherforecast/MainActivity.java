package com.github.keyfour.myweatherforecast.features.weatherforecast;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.keyfour.myweatherforecast.R;
import com.github.keyfour.myweatherforecast.features.login.LoginActivity;
import com.github.keyfour.myweatherforecast.features.settings.SettingFragment;
import com.github.keyfour.myweatherforecast.features.weatherforecast.current.CurrentFragment;
import com.github.keyfour.myweatherforecast.features.weatherforecast.fivedays.FivedaysFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnCompleteListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;


    class NavViewFields {

        @BindView(R.id.iv_user)
        ImageView ivUser;

        @BindView(R.id.tv_username)
        TextView tvUsername;

        @BindView(R.id.tv_email)
        TextView tvEmail;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        NavViewFields navViewFields = new NavViewFields();
        ButterKnife.bind(navViewFields, navigationView.getHeaderView(0));
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            navViewFields.tvUsername.setText(account.getDisplayName());
            navViewFields.tvEmail.setText(account.getEmail());
            Uri user = account.getPhotoUrl();
            Picasso.with(this).load(user).into(navViewFields.ivUser);
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.contentFrame,
                    CurrentFragment.newInstance()).commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contentFrame,
                    SettingFragment.newInstance()).addToBackStack("Settings").commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_today) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contentFrame,
                    CurrentFragment.newInstance()).addToBackStack("Today").commit();
        } else if (id == R.id.nav_fivedays) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contentFrame,
                    FivedaysFragment.newInstance()).addToBackStack("5-days").commit();
        } else if (id == R.id.nav_settings) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contentFrame,
                    SettingFragment.newInstance()).addToBackStack("Settings").commit();
        } else if (id == R.id.nav_signout) {
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();
            GoogleSignInClient signInClient = GoogleSignIn.getClient(this, gso);
            signInClient.signOut().addOnCompleteListener(this);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onComplete(@NonNull Task task) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }
}
