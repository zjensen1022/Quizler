package com.example.quizler;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.flexbox.FlexboxLayout;


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
        Thread getNewData = new Thread(() -> DatabaseHelper.loadDecksForMain(this));
        getNewData.start();
    }
    public void addDeck(View view) {
        Intent intent = new Intent(this, AddDeckActivity.class);
        startActivity(intent);
    }
}