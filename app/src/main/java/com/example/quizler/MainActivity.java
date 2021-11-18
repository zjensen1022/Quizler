package com.example.quizler;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.view.View;


import com.example.quizler.databinding.ActivityMainBinding;
import com.google.android.flexbox.FlexboxLayout;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up bottom bar and put buttons in there.
        FlexboxLayout bottomBar = findViewById(R.id.bottomBarRef);
        CustomButton addDeckButton = new CustomButton(bottomBar.getContext());
        addDeckButton.button.setText(R.string.add_deck);
        addDeckButton.button.setOnClickListener(this::addDeck);
        bottomBar.addView(addDeckButton);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Thread thread = new Thread(() -> DatabaseHelper.loadDecks(this));
        thread.start();
    }
    public void addDeck(View view) {
        Intent intent = new Intent(this, AddDeckActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onStart() {
        super.onStart();

        // Start a new thread to load Json data right
        // as the application launches.
        DataHandler.loadFromJson();
    }
}