package com.example.quizler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.quizler.databinding.ActivityAddDeckBinding;
import com.example.quizler.databinding.ActivityMainBinding;
import com.google.android.flexbox.FlexboxLayout;

public class AddDeckActivity extends AppCompatActivity {

    private EditText input;
    private boolean isEdit;
    public Deck currentDeck;
    final int DEFAULT_DECK_ID = -1;
    private int deckId = DEFAULT_DECK_ID;
    private Thread getDeckForUpdate = new Thread(() -> DatabaseHelper.getSingleDeck(deckId, this));
    private Thread saveDeck = new Thread(() -> DatabaseHelper.saveDeck(input.getText().toString(), this));
    private Thread updateDeck = new Thread(() -> DatabaseHelper.updateDeck(currentDeck, this));
    private Thread deleteDeck = new Thread(() -> DatabaseHelper.deleteDeck(currentDeck, this));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_deck);
        deckId = getIntent().getIntExtra("deck_id", DEFAULT_DECK_ID);
        isEdit = deckId != DEFAULT_DECK_ID;
        if(isEdit && !hasRunningThreads()) {
            getDeckForUpdate.start();
        }
        if (isEdit) {
            setTitle(R.string.edit_deck);
        } else {
            setTitle(R.string.add_deck);
        }
        setupBottomBar();
        setupFieldNames();
    }

    private void setupBottomBar() {
        // Set up bottom bar
        FlexboxLayout bottomBar = findViewById(R.id.createDeckBottomBar);
        CustomButton cancelButton = new CustomButton(bottomBar.getContext());
        cancelButton.button.setText(R.string.cancel);
        cancelButton.button.setOnClickListener(this::cancelButton);
        CustomButton saveButton = new CustomButton(bottomBar.getContext());
        saveButton.button.setText(R.string.save);
        saveButton.button.setOnClickListener(this::saveButton);
        bottomBar.addView(saveButton);
        bottomBar.addView(cancelButton);
        if (isEdit) {
            CustomButton deleteButton = new CustomButton(bottomBar.getContext());
            deleteButton.button.setText(R.string.delete);
            deleteButton.button.setOnClickListener(this::deleteButton);
            bottomBar.addView(deleteButton);
        }

    }

    private void setupFieldNames() {
        // Set up Input values
        TextView inputLabel = findViewById(R.id.tvLabel);
        input = findViewById(R.id.etValue);
        if (isEdit) {
            inputLabel.setText(R.string.edit_deck_name);
        }
        else {
            inputLabel.setText(R.string.new_deck_name);
        }
        input.setHint(R.string.deck_name);
    }

    // Ensures that we don't have any threads running to keep from running
    // two conflicting tasks at the same time.
    public boolean hasRunningThreads() {
        return getDeckForUpdate.isAlive()
                || saveDeck.isAlive()
                || updateDeck.isAlive()
                || deleteDeck.isAlive();
    }

    public void displayDeckData() {
        input.setText(currentDeck.name);
    }
    public void cancelButton(View view) {
        if (hasRunningThreads()) return;
        setResult(RESULT_CANCELED);
        finish();
    }
    public void deleteButton(View view) {
        if (hasRunningThreads()) return;
        deleteDeck.start();
    }
    public void saveButton(View view) {
        if (hasRunningThreads()) return;
        if (isEdit) {
            currentDeck.name = input.getText().toString();
            updateDeck.start();
        } else {
            saveDeck.start();
        }
    }
}