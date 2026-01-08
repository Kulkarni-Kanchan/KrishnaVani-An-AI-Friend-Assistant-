package com.example.chat_with_buddy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.chat_with_buddy.Fragment.AboutFragment;
import com.example.chat_with_buddy.Fragment.HomeFragment;
import com.example.chat_with_buddy.Fragment.MyProfileFragment;
import com.example.chat_with_buddy.Fragment.SettingsFragment;
import com.example.chat_with_buddy.Fragment.ShareFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class ChatScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


View item;
    private DrawerLayout drawerLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatscren);

        item=findViewById(R.id.nav_logout);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout=findViewById(R.id.drawer_layout);

        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_nav,R.string.close_nav);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        if (savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);


        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
       switch (item.getItemId())
       {
           case R.id.nav_home:
               getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
               break;


           case R.id.nav_myProfile:
               getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MyProfileFragment()).commit();
               break;

           case R.id.nav_settings:
               getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new SettingsFragment()).commit();
               break;

           case R.id.nav_share:
               getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ShareFragment()).commit();
               break;

           case R.id.nav_about:
               getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AboutFragment()).commit();
               break;

           case R.id.nav_logout:


     item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
    @Override
    public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
        FirebaseAuth auth=FirebaseAuth.getInstance();
        auth.signOut();
        Intent i=new Intent(getApplicationContext(),splashScreen.class);
        startActivity(i);
        ChatScreen.this.finish();

        return false;
    }
});
               Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                break;

       }

       drawerLayout.closeDrawer(GravityCompat.START);
       return true;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }

    }
}
