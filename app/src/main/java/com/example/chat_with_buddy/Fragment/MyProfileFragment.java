package com.example.chat_with_buddy.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.chat_with_buddy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyProfileFragment extends Fragment {

    private TextView textViewUsername;
    private TextView textViewFirstName;
    private TextView textViewLastName;
    private TextView textViewAge;
    private TextView textViewGender;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fargmnet_profile, container, false);

        textViewUsername = view.findViewById(R.id.textViewUsername);
        textViewFirstName = view.findViewById(R.id.textViewFirstName);
        textViewLastName = view.findViewById(R.id.textViewLastName);
        textViewAge = view.findViewById(R.id.textViewAge);
        textViewGender = view.findViewById(R.id.textViewGender);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("user").child(mAuth.getCurrentUser().getUid());

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String username = dataSnapshot.child("email").getValue().toString();
                    String firstName = dataSnapshot.child("firstname").getValue().toString();
                    String lastName = dataSnapshot.child("lastname").getValue().toString();
                    String age = dataSnapshot.child("age").getValue().toString();
                    String gender = dataSnapshot.child("gender").getValue().toString();

                    textViewUsername.setText("Username: " + username);
                    textViewFirstName.setText("First Name: " + firstName);
                    textViewLastName.setText("Last Name: " + lastName);
                    textViewAge.setText("Age: " + age);
                    textViewGender.setText("Gender: " + gender);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
            }
        });

        return view;
    }
}
