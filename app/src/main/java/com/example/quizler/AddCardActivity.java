package com.example.quizler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

public class AddCardActivity extends AppCompatActivity {

    Card currentCard;
    private EditText titleInput;
    private EditText descriptionInput;
    private boolean isEdit;
    final int DEFAULT_CARD_ID = -1;
    private int cardId = DEFAULT_CARD_ID;
    private int deckId = DEFAULT_CARD_ID;
    private final Thread getCardForUpdate = new Thread(() -> DatabaseHelper.getSingleCardForEdit(cardId, this));
    private final Thread saveCard = new Thread(() -> DatabaseHelper.saveCard(currentCard, this));
    private final Thread updateCard = new Thread(() -> DatabaseHelper.updateCard(currentCard, this));
    private final Thread deleteCard = new Thread(() -> DatabaseHelper.deleteCard(currentCard, this));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        cardId = getIntent().getIntExtra("card_id", DEFAULT_CARD_ID);
        deckId = getIntent().getIntExtra("deck_id", DEFAULT_CARD_ID);
        isEdit = cardId != DEFAULT_CARD_ID;
        if (isEdit && !hasRunningThreads()) {
            getCardForUpdate.start();
        }
        if (isEdit) {
            setTitle(R.string.edit_card);
        } else {
            setTitle(R.string.add_card);
        }
        setupBottomBar();
        setupFieldNames();
    }
    private void setupBottomBar() {
        FlexboxLayout bottomBar = findViewById(R.id.addCardBottomBar);
        CustomButton cancelButton = new CustomButton(bottomBar.getContext());
        cancelButton.button.setText(R.string.cancel);
        cancelButton.button.setOnClickListener(this::cancelButton);
        CustomButton saveButton = new CustomButton(bottomBar.getContext());
        saveButton.button.setText(R.string.save);
        saveButton.button.setOnClickListener(this::saveButton);
        bottomBar.addView(saveButton);
        bottomBar.addView(cancelButton);
        if (isEdit) {
            CustomButton deleteButton = new DeleteButton(bottomBar.getContext());
            deleteButton.button.setText(R.string.delete);
            deleteButton.button.setOnClickListener(this::deleteButton);
            bottomBar.addView(deleteButton);
        }
    }
    private void setupFieldNames() {
        TextView titleInputLabel = findViewById(R.id.tvLabel);
        titleInput = findViewById(R.id.etValue);
        TextView descriptionInputLabel = findViewById(R.id.lgtvLabel);
        descriptionInput = findViewById(R.id.etLargeValue);
        if (isEdit) {
            titleInputLabel.setText(R.string.edit_card_title);
            descriptionInputLabel.setText(R.string.edit_card_desc);
        } else {
            titleInputLabel.setText(R.string.new_card_title);
            descriptionInputLabel.setText(R.string.new_card_desc);
        }
        titleInput.setHint(R.string.card_title);
        descriptionInput.setHint(R.string.card_desc);
    }

    public boolean hasRunningThreads() {
        return getCardForUpdate.isAlive() || saveCard.isAlive() || updateCard.isAlive() || deleteCard.isAlive();
    }

    public void displayCardData() {
        titleInput.setText(currentCard.title);
        descriptionInput.setText(currentCard.description);
    }

    public void cancelButton(View view) {
        if (hasRunningThreads()) return;
        finish();
    }
    public void deleteButton(View view) {
        if (hasRunningThreads()) return;
        deleteCard.start();
    }
    public void saveButton(View view) {
        if (hasRunningThreads()) return;
        if (isEdit) {
            currentCard.title = titleInput.getText().toString();
            currentCard.description = descriptionInput.getText().toString();
            updateCard.start();
        } else {
            currentCard = new Card(
                    titleInput.getText().toString(),
                    descriptionInput.getText().toString(),
                    deckId);
            saveCard.start();
        }
    }
}