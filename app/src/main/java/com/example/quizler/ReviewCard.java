package com.example.quizler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ReviewCard extends AppCompatActivity {

    private boolean flipped; //tracks whether the answer has been revealed.
    Button nextBtn;
    ArrayList<Card> deck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_card);

        flipped = false;
        deck = new ArrayList<>();

        //get data in background thread and populate the ArrayList here
        Thread thread = new Thread(new JsonReader(deck));
        thread.start();

        nextBtn = findViewById(R.id.nextButton);

        nextBtn.setOnClickListener(this::next);

    }

    /**
     * next
     * The next button is used for both revealing the back side of the card,
     * and moving to the next card once the answer on the current card has
     * been revealed.
     * @param view
     */
    public void next(View view) {

        TextView frontText = findViewById(R.id.ReviewFrontText);
        TextView backText = findViewById(R.id.ReviewBackText);

        if(flipped) {
            frontText.setText(deck.get(0).getFrontText());
            backText.setText("");
            nextBtn.setText(R.string.show);
            flipped = false;
        }
        else {
            // populate text fields with data from the
            // next card at this step.
            backText.setText(deck.get(0).getBackText());
            nextBtn.setText(R.string.next);
            flipped = true;
        }
    }
}