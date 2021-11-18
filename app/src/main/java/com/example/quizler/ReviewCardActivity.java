package com.example.quizler;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReviewCardActivity extends AppCompatActivity {

    private boolean flipped; // tracks whether the answer has been revealed.
    private int currentIndex; // index of current card being reviewed
    private Card currentCard; // reference to current card being reviewed.

    private Button nextBtn;
    private TextView frontTextView;
    private TextView backTextView;

    List<Card> deck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_card);

        flipped = false;

        frontTextView = findViewById(R.id.ReviewFrontText);
        backTextView = findViewById(R.id.ReviewBackText);
        nextBtn = findViewById(R.id.nextButton);

        nextBtn.setOnClickListener(this::next);

        Context context = this;
        OnBackPressedCallback callback = new OnBackPressedCallback(true){
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        };
        getOnBackPressedDispatcher().addCallback(callback);
    }
    public void setupReview() {
        if (deck.size() == 0) {
            endReview();
            return;
        }
        Collections.shuffle(deck);

        if(currentIndex < deck.size()) {
            currentCard = deck.get(currentIndex);

            frontTextView.setText(currentCard.getTitle());
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        int deckId = intent.getIntExtra("deck_id", 0);
        Thread thread = new Thread(() -> DatabaseHelper.loadCardsForReview(deckId, this));
        thread.start();
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
            // end of cards
            endReview();
        }
    }

    private void flipCard() {
        backTextView.setText(currentCard.getDescription());
        nextBtn.setText(R.string.next);

        currentIndex++;
        flipped = true;
    }

    private void displayNext() {
        flipped = false;
        currentCard = deck.get(currentIndex);

        frontTextView.setText(currentCard.getTitle());
        backTextView.setText("");
        nextBtn.setText(R.string.show);
    }

    private void endReview() {
        Intent intent = new Intent(this, EndReview.class);
        intent.putExtra("deck_id", getIntent().getIntExtra("deck_id", 0));
        startActivity(intent);
    }

}