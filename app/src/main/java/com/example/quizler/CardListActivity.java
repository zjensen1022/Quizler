package com.example.quizler;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.flexbox.FlexboxLayout;

public class CardListActivity extends AppCompatActivity {

    CustomButton reviewButton;

    // Handles whether or not deck was edited
    ActivityResultLauncher<Intent> editDeckActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    finish();
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
        editDeck.button.setText(R.string.edit_deck);
        editDeck.button.setOnClickListener(this::editDeck);
        bottomBar.addView(editDeck);
        reviewButton = new CustomButton(bottomBar.getContext());
        reviewButton.button.setText(R.string.review);
        bottomBar.addView(reviewButton);
        CustomButton addCardButton = new CustomButton(bottomBar.getContext());
        addCardButton.button.setText(R.string.add_card);
        addCardButton.button.setOnClickListener(this::addCard);
        bottomBar.addView(addCardButton);
    }

    public void handleReviewButton(boolean deckIsPopulated) {

        if (deckIsPopulated)
            reviewButton.button.setOnClickListener(this::reviewDeck);
        else {
            reviewButton.button.setOnClickListener(this::reviewDisabled);
            reviewButton.button.setAlpha(0.5f);
        }

    }

    @Override
    public void onBackPressed() {
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
    }

    public void reviewDeck(View view) {
        Intent intent = new Intent(this, ReviewCardActivity.class);
        intent.putExtra("deck_id", getIntent().getIntExtra("deck_id", 0));
        intent.putExtra("deck_name", getIntent().getStringExtra("deck_name"));
        startActivity(intent);
    }

    public void reviewDisabled(View view) {
        Toast.makeText(this, getString(R.string.no_cards_message), Toast.LENGTH_LONG).show();
    }

}