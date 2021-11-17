package com.example.quizler;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class EndReview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_review);

        Button exitBtn = findViewById(R.id.exitReviewButton);
        Button reviewBtn = findViewById(R.id.reviewAgainButton);

        exitBtn.setOnClickListener(this::exit);
        reviewBtn.setOnClickListener(this::reviewAgain);

        Context context = this;

        // Ensures that the back button returns to the main activity.
        OnBackPressedCallback callback = new OnBackPressedCallback(true){

            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        };

        getOnBackPressedDispatcher().addCallback(callback);
    }

    public void exit(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void reviewAgain(View view) {
        Intent intent = new Intent(this, ReviewCardActivity.class);
        startActivity(intent);
    }
}