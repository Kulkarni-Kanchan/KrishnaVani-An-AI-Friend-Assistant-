package com.example.chat_with_buddy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class splashScreen extends AppCompatActivity {
TextView appname;
    LottieAnimationView lotties;  //make lottieAnimationView object

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        appname=findViewById(R.id.appname);
        lotties=findViewById(R.id.lottie);

        appname.animate().translationY(-1400).setDuration(2700).setStartDelay(0);
        appname.animate().translationY(2000).setDuration(2000).setStartDelay(4000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                FirebaseAuth auth=FirebaseAuth.getInstance();
                FirebaseUser user=auth.getCurrentUser();
                if(user!=null){
                    Intent i=new Intent(getApplicationContext(), ChatScreen.class);
                    startActivity(i);
                    splashScreen.this.finish();
                }else{
                    Intent i=new Intent(getApplicationContext(),SlideActivity.class);
                    startActivity(i);
                    splashScreen.this.finish();
                }


            }
        },8000);
    }

    }

