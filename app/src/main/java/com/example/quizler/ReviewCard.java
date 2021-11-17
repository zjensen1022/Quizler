package com.example.quizler;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class ReviewCard extends AppCompatActivity {

    private boolean flipped; // tracks whether the answer has been revealed.
    private int currentIndex; // index of current card being reviewed
    private Card currentCard;

    private Button nextBtn;
    private TextView frontTextView;
    private TextView backTextView;

    ArrayList<Card> deck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_card);

        flipped = false;
        deck = DataHandler.getCards();

        frontTextView = findViewById(R.id.ReviewFrontText);
        backTextView = findViewById(R.id.ReviewBackText);
        nextBtn = findViewById(R.id.nextButton);

        nextBtn.setOnClickListener(this::next);

        Collections.shuffle(deck);

        if(currentIndex < deck.size()) {
            currentCard = deck.get(currentIndex);

            frontTextView.setText(currentCard.getFrontText());
        }

        Context context = this;

        OnBackPressedCallback callback = new OnBackPressedCallback(true){

            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
                Log.d("ReviewCard","Back Button Pressed");
            }
        };

        getOnBackPressedDispatcher().addCallback(callback);
    }

    /**
     * next
     * The next button is used for both revealing the back side of the card,
     * and moving to the next card once the answer on the current card has
     * been revealed.
     * @param view
     */
    public void next(View view) {

        if(currentIndex < deck.size()) {
            if(flipped)
                displayNext();
            else
                flipCard();
        }
        else {

        }
    }

    private void flipCard() {
        backTextView.setText(currentCard.getBackText());
        nextBtn.setText(R.string.next);

        currentIndex++;
        flipped = true;
    }

    private void displayNext() {
        flipped = false;
        currentCard = deck.get(currentIndex);

        frontTextView.setText(currentCard.getFrontText());
        backTextView.setText("");
        nextBtn.setText(R.string.show);
    }

}