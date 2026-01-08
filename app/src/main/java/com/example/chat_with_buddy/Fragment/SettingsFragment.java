package com.example.chat_with_buddy.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.chat_with_buddy.R;

public class SettingsFragment extends Fragment {

    private CheckBox notificationCheckbox;
    private Switch darkModeSwitch;
    private Spinner languageSpinner;
    private SeekBar fontSizeSeekBar;
    private CheckBox vibrationCheckbox;
    private Button saveButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        // Initialize views
        notificationCheckbox = view.findViewById(R.id.notificationCheckbox);
        darkModeSwitch = view.findViewById(R.id.darkModeSwitch);
        languageSpinner = view.findViewById(R.id.languageSpinner);
        fontSizeSeekBar = view.findViewById(R.id.fontSizeSeekBar);
        vibrationCheckbox = view.findViewById(R.id.vibrationCheckbox);
        saveButton = view.findViewById(R.id.saveButton);

        // Set listener for the save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save settings or perform action based on user preferences
                saveSettings();
            }
        });

        // Set listener for the dark mode switch
        darkModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Apply dark theme if switch is checked
                if (isChecked) {
                    applyDarkTheme();
                } else {
                    // Apply light theme if switch is unchecked
                    applyLightTheme();
                }
            }
        });

        return view;
    }

    private void saveSettings() {
        // You can implement saving settings logic here
        // For example, you can save the state of checkboxes, selected spinner item, seekbar progress, etc.
        Toast.makeText(getContext(), "Settings saved!", Toast.LENGTH_SHORT).show();
    }

    private void applyDarkTheme() {
        // Implement logic to apply dark theme
        // For example, you can change background color, text color, etc.
        // getActivity().setTheme(R.style.DarkTheme); // If using themes/styles
        // You can customize based on your application's theme implementation
    }

    private void applyLightTheme() {
        // Implement logic to apply light theme
        // For example, you can revert changes made in applyDarkTheme()
        // getActivity().setTheme(R.style.AppTheme); // If using themes/styles
        // You can customize based on your application's theme implementation
    }
}
