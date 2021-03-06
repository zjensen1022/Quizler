package com.example.quizler;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_review);
        setTitle(getIntent().getStringExtra("deck_name"));

        Button exitBtn = findViewById(R.id.exitReviewButton);
        Button reviewBtn = findViewById(R.id.reviewAgainButton);

        exitBtn.setOnClickListener(this::exit);
        reviewBtn.setOnClickListener(this::reviewAgain);

        Context context = this;
        int time = getIntent().getIntExtra("final_time",0);
        TextView timeText = findViewById(R.id.lgtvLabel) ;
        String endMessage = String.format("Finished reviewing deck in %d seconds, would you like to review again?",time);
        timeText.setText(endMessage);
        // Ensures that the back button returns to cardListActivity
        OnBackPressedCallback callback = new OnBackPressedCallback(true){

            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(getBaseContext(), CardListActivity.class);
                intent.putExtra("deck_id", getIntent().getIntExtra("deck_id", 0));
                intent.putExtra("deck_name", getIntent().getStringExtra("deck_name"));
                startActivity(intent);
                finish();
            }
        };

        getOnBackPressedDispatcher().addCallback(callback);
    }

    public void exit(View view) {
        setResult(ReviewCardActivity.EXIT_REVIEW_CODE);
        finish();
    }

    public void reviewAgain(View view) {
        setResult(ReviewCardActivity.REVIEW_AGAIN_CODE);
        finish();
    }
}