package com.example.quizler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.quizler.databinding.ActivityAddDeckBinding;
import com.example.quizler.databinding.ActivityMainBinding;
import com.google.android.flexbox.FlexboxLayout;

public class AddDeckActivity extends AppCompatActivity {

    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_deck);

        // Set up bottom bar
        FlexboxLayout bottomBar = findViewById(R.id.createDeckBottomBar);
        CustomButton cancelButton = new CustomButton(bottomBar.getContext());
        cancelButton.button.setText(R.string.cancel);
        cancelButton.button.setOnClickListener(this::cancelButton);
        CustomButton saveButton = new CustomButton(bottomBar.getContext());
        saveButton.button.setText(R.string.save);
        saveButton.button.setOnClickListener(this::saveButton);
        bottomBar.addView(cancelButton);
        bottomBar.addView(saveButton);

        // Set up Input values
        TextView inputLabel = findViewById(R.id.tvLabel);
        inputLabel.setText(R.string.new_deck_name);
        input = findViewById(R.id.etValue);
        input.setHint(R.string.new_deck_name);
    }

    public void cancelButton(View view) {
        finish();
    }
    public void saveButton(View view) {
        EditText deckEditText = findViewById(R.id.etValue);
        String value = deckEditText.getText().toString();
        Thread thread = new Thread(() -> DatabaseHelper.saveDeck(value, this));
        thread.start();
    }
}