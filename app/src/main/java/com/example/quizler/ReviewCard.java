package com.example.quizler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ReviewCard extends AppCompatActivity {

    private boolean flipped; //tracks whether the answer has been revealed.
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_card);

        flipped = false;

        //get data and populate textViews here

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

        TextView rearText = findViewById(R.id.reviewBackText);

        if(flipped) {
            rearText.setText("");
            nextBtn.setText(R.string.show);
            flipped = false;
        }
        else {
            // populate text fields with data from the
            // next card at this step.
            rearText.setText(R.string.back_hint);
            nextBtn.setText(R.string.next);
            flipped = true;
        }
    }
}