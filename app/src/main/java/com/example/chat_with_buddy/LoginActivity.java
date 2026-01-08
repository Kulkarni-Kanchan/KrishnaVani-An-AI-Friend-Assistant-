package com.example.chat_with_buddy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth mAuth=FirebaseAuth.getInstance();
    EditText edtusername,edtpassword;

    TextView txtsignup,textforget;
    Button btnlogin;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);


         textforget=findViewById(R.id.forgotPasswordTextView);
        txtsignup = findViewById(R.id.sinuptext);

        edtusername = findViewById(R.id.loginedtusername);
        edtusername.requestFocus();

        edtpassword = findViewById(R.id.loginedtpassword);
        btnlogin = findViewById(R.id.btnlogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtusername.getText().toString().trim();
                String password = edtpassword.getText().toString().trim();

                if (email.isEmpty() && password.isEmpty()){

                    edtusername.setError("This field is required");
                    edtusername.requestFocus();

                    edtpassword.setError("This field is required");
                    edtpassword.requestFocus();
                    return;
                }
                else {
                    signInWithEmail(email, password);
                    //LoginActivity.this.finish();
                }
            }
        });
textforget.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i=new Intent(getApplicationContext(), ForgetPassworld.class);
        startActivity(i);
    }
});


        txtsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(i);

            }
        });


    }

    public void signInWithEmail(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    //sigin is succefully, update UI with the sigin users
                    String uid=mAuth.getCurrentUser().getUid();
                    Intent i=new Intent(getApplicationContext(), ChatScreen.class);
                    startActivity(i);
                    Toast.makeText(LoginActivity.this, "Login succesfully-", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent i=new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                    //if signin fails display msg to be user
                    Toast.makeText(LoginActivity.this, "Authentication fail", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}
