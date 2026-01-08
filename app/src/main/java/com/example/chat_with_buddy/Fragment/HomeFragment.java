package com.example.chat_with_buddy.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.chat_with_buddy.MainActivity;
import com.example.chat_with_buddy.R;

public class HomeFragment extends Fragment {

    Button buttonOpenMainActivity;
    ImageView gifImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize the views
        buttonOpenMainActivity = view.findViewById(R.id.button_open_main_activity);
        gifImageView = view.findViewById(R.id.gifImageView);

        // Load the GIF using Glide
        Glide.with(this)
                .asGif()
                .load(R.drawable.friendlylivebot) // Assuming the GIF is placed in the drawable directory
                .into(gifImageView);

        // Set an OnClickListener on the button
        buttonOpenMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the MainActivity
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        // Apply a Matrix to focus on a specific part of the GIF
        // Apply a Matrix to focus on a specific part of the GIF


        return view;
    }
}
