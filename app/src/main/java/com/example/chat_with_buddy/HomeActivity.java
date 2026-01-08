package com.example.chat_with_buddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    Button btnlogout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homeactivity);
btnlogout=findViewById(R.id.myButton);

btnlogout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        FirebaseAuth auth=FirebaseAuth.getInstance();
        auth.signOut();
        Intent i=new Intent(getApplicationContext(),splashScreen.class);
        startActivity(i);
        HomeActivity.this.finish();
    }
});



    }
}
