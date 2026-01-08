package com.example.chat_with_buddy.Fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.chat_with_buddy.R;

public class ShareFragment extends Fragment {

    public ShareFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_share, container, false);
        return view;
    }

    // Method to share the app link via WhatsApp
    public void shareOnWhatsApp(View view) {
        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        whatsappIntent.setType("text/plain");
        whatsappIntent.setPackage("com.whatsapp");
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, "Check out this amazing app: https://play>google.com?store?apps?details?id=com.whatsapp");
        try {
            startActivity(whatsappIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getContext(), "WhatsApp not installed.", Toast.LENGTH_SHORT).show();
        }
    }


    // Method to share the app link via Twitter
    public void shareOnTwitter(View view) {
        Intent twitterIntent = new Intent(Intent.ACTION_VIEW);
        twitterIntent.setData(Uri.parse("https://twitter.com/intent/tweet?text=Check out this amazing app: https://play>google.com?store?apps?details?id=com.whatsapp"));
        startActivity(twitterIntent);
    }

    // Method to share the app link via Instagram
    public void shareOnInstagram(View view) {
        Intent instagramIntent = new Intent(Intent.ACTION_VIEW);
        instagramIntent.setData(Uri.parse("https://www.instagram.com/"));
        startActivity(instagramIntent);
    }

    // Method to copy the app link to clipboard
    public void copyLink(View view) {
        ClipboardManager clipboard = (ClipboardManager) requireContext().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("App Link", "https://play>google.com?store?apps?details?id=com.whatsapp");
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getContext(), "Link copied to clipboard", Toast.LENGTH_SHORT).show();
    }
}
