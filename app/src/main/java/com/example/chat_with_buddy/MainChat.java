// MainChat.java
package com.example.chat_with_buddy;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainChat extends AppCompatActivity {
    private EditText editText;
    private final int USER = 0;
    private final int BOT = 1;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState );
        setContentView(R.layout.mainchatting);
        editText = findViewById(R.id.edittext_chatbox);

        findViewById(R.id.send_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {

        String msg = editText.getText().toString();
        OkHttpClient okHttpClient = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("  https://ca12-2409-40c2-1054-8ab3-49db-245e-5cd4-8cc6.ngrok-free.app/webhooks/rest/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserMessage userMessage = new UserMessage();

        if (msg.trim().isEmpty()) {
            Toast.makeText(MainChat.this, "Please enter your query!", Toast.LENGTH_LONG).show();
        } else {
            showTextView(msg, USER);
            editText.setText("");

            userMessage = new UserMessage("User",msg);
        }
        MessageSender messageSender = retrofit.create(MessageSender.class);

        Toast.makeText(MainChat.this, ""+userMessage.getMessage(), Toast.LENGTH_LONG).show();
        messageSender = retrofit.create(MessageSender.class);
        Call<List<BotResponse>> response = messageSender.sendMessage(userMessage);
        response.enqueue(new Callback<List<BotResponse>>() {
            @Override
            public void onResponse(Call<List<BotResponse>> call, Response<List<BotResponse>> response) {
                if(response.body() == null || response.body().size() == 0){
                    showTextView("Sorry didn't understand",BOT);
                }
                else{
                    BotResponse botResponse = response.body().get(0);
                    showTextView(botResponse.getText(),BOT);
                }
            }
            @Override
            public void onFailure(Call<List<BotResponse>> call, Throwable t) {
                showTextView("Waiting for message",BOT);
                Toast.makeText(MainChat.this,""+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showTextView (String message,int type){
        LinearLayout chatLayout = findViewById(R.id.chat_layout);
        LayoutInflater inflater = LayoutInflater.from(MainChat.this);
        FrameLayout layout;
        switch (type) {
            case USER:
                layout = getUserLayout();
                break;
            case BOT:
                layout = getBotLayout();
                break;
            default:
                layout = getBotLayout();
                break;
        }
        layout.setFocusableInTouchMode(true);
        chatLayout.addView(layout);
        TextView tv = layout.findViewById(R.id.chat_msg);
        tv.setText(message);
        layout.requestFocus();
        editText.requestFocus();
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm aa",
                Locale.ENGLISH);
        String time = dateFormat.format(date);
        TextView timeTextView = layout.findViewById(R.id.message_time);
        timeTextView.setText(time.toString());
    }

    FrameLayout getUserLayout () {
        LayoutInflater inflater = LayoutInflater.from(MainChat.this);
        return (FrameLayout) inflater.inflate(R.layout.user_message_box, null);
    }

    FrameLayout getBotLayout () {
        LayoutInflater inflater = LayoutInflater.from(MainChat.this);
        return (FrameLayout) inflater.inflate(R.layout.bot_message_box, null);
    }

}