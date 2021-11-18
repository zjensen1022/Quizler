package com.example.quizler;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.flexbox.FlexboxLayout;

import java.util.List;

public class CardListActivity extends AppCompatActivity {

    private AppDatabase db;
    // Handles whether or not deck was edited
    ActivityResultLauncher<Intent> editDeckActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        finish();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);
        setTitle(getIntent().getStringExtra("deck_name"));
        createBottomBar();
    }

    private void createBottomBar() {
        FlexboxLayout bottomBar = findViewById(R.id.cardListBottomBarRef);
        CustomButton editDeck = new CustomButton(bottomBar.getContext());
        editDeck.button.setText("Edit Deck");
        editDeck.button.setOnClickListener(this::editDeck);
        bottomBar.addView(editDeck);
        CustomButton reviewButton = new CustomButton(bottomBar.getContext());
        reviewButton.button.setText("Review");
        reviewButton.button.setOnClickListener(this::reviewDeck);
        bottomBar.addView(reviewButton);
        CustomButton addCardButton = new CustomButton(bottomBar.getContext());
        addCardButton.button.setText("Add Card");
        addCardButton.button.setOnClickListener(this::addCard);
        bottomBar.addView(addCardButton);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        int deckId = getIntent().getIntExtra("deck_id", 0);
        Thread thread = new Thread(() -> DatabaseHelper.loadCardsForList(deckId, this));
        thread.start();
    }

    public void addCard(View view) {
        Intent intent = new Intent(this, AddCardActivity.class);
        intent.putExtra("deck_id", getIntent().getIntExtra("deck_id", -1));
        startActivity(intent);
    }

    public void editDeck(View view) {
        Intent intent = new Intent(this, AddDeckActivity.class);
        intent.putExtra("deck_id", getIntent().getIntExtra("deck_id", 0));
        editDeckActivityResultLauncher.launch(intent);
//        startActivity(intent);
    }

    public void reviewDeck(View view) {
        Intent intent = new Intent(this, ReviewCardActivity.class);
        intent.putExtra("deck_id", getIntent().getIntExtra("deck_id", 0));
        startActivity(intent);
    }
}