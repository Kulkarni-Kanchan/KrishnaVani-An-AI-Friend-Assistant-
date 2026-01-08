package com.example.chat_with_buddy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chat_with_buddy.setterGetter.UserStGt;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

RadioGroup radioGroup;
    RadioButton radioButton1 ;
    RadioButton radioButton2 ;
    EditText edtfisrtname, edtlastname, edtage;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    EditText edtusername, edtpassword,edtconfirmpass;
    Button btnsignup, btnlogin;
String radioButtonSlected;

    TextView textViewLogintext;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupactivity);


       radioGroup=findViewById(R.id.radioGroup);
       radioButton1= findViewById(R.id.radioButtonOption1);
       radioButton2= findViewById(R.id.radioButtonOption2);
        radioButton2.setChecked(true);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Handle radio button selection change event here
                // Save the selected option to Firebase Database
                switch (checkedId) {
                    case R.id.radioButtonOption1:
                       radioButtonSlected="MALE";
                        break;
                    case R.id.radioButtonOption2:
                        radioButtonSlected="FEMALE";
                        break;
                }
            }
        });


        textViewLogintext=findViewById(R.id.logintext);
        textViewLogintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });


        edtfisrtname = findViewById(R.id.userFirstName);
        edtlastname = findViewById(R.id.userLastname);
        edtage = findViewById(R.id.edtage);

        edtpassword = findViewById(R.id.edtpassword);
        edtusername = findViewById(R.id.edtusernamEmail);
        btnsignup = findViewById(R.id.bsignup);

edtconfirmpass=findViewById(R.id.edtconfirmpassword);

        edtage.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});


        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usernmae = edtusername.getText().toString();
                String password = edtpassword.getText().toString();
                String confirmpass=edtconfirmpass.getText().toString();
                String firstName = edtfisrtname.getText().toString();
                String lastName = edtlastname.getText().toString();
                String age = edtage.getText().toString();

                if (usernmae.isEmpty() && password.isEmpty() && firstName.isEmpty() && lastName.isEmpty() && age.isEmpty() && confirmpass.isEmpty()) {
                    edtusername.setError("This field is required"); // Set an error message on the EditText field
                    edtusername.requestFocus(); // Request focus on the EditText field to show the error

                    edtpassword.setError("This field is required");
                    edtpassword.requestFocus(); //

                    edtfisrtname.setError("This field is required");
                    edtfisrtname.requestFocus(); //

                    edtlastname.setError("This field is required");
                    edtlastname.requestFocus(); //

                    edtage.setError("This field is required");

                    edtage.requestFocus(); //
                     edtconfirmpass.requestFocus();

                    return; // Return to exit the validation logic
                }

                else if (!password.equals(confirmpass)) {
                    // Handle the case where password and confirmation password don't match
                    Toast.makeText(SignupActivity.this, "Password and confirmation password do not match", Toast.LENGTH_SHORT).show();
                }


                else {

                    signup(usernmae, password);
                }


            }
        });
    }

    public void signup(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful()) {
                    //signin successful,get user id
                    String userId = mAuth.getCurrentUser().getUid();
                    Toast.makeText(SignupActivity.this, "user created" + userId, Toast.LENGTH_SHORT).show();

                    String stringfname = edtfisrtname.getText().toString();
                    String stringlname = edtlastname.getText().toString();
                    String stringage = edtage.getText().toString();
                    String stringEmail = edtusername.getText().toString();

                    UserStGt user = new UserStGt();
                    user.setFirstname(stringfname);
                    user.setLastname(stringlname);
                    user.setAge(Integer.parseInt(stringage));
                    user.setGender(radioButtonSlected);
                    user.setEmail(stringEmail);
                    DatabaseReference mdatabase = FirebaseDatabase.getInstance().getReference();
                    mdatabase.child("user").child(userId).setValue(user);
                    //startActivities(i);

                    Intent i = new Intent(getApplicationContext(), ChatScreen.class);
                    startActivity(i);


                    //do something with the user id
                } else {
                    //signin failed
                    Log.e("TAG", "Signin falied.", task.getException());

                    Toast.makeText(SignupActivity.this, "error-" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


}
