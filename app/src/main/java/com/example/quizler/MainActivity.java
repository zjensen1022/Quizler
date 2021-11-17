package com.example.quizler;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;


import com.example.quizler.databinding.ActivityMainBinding;
import com.google.android.flexbox.FlexboxLayout;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        BottomBarItem plusButton = new BottomBarItem(this);
        plusButton.setOnClickListener(this::addDeck);
        BottomBarItem secondButton = new BottomBarItem(this);
        FlexboxLayout llBottomBar = binding.bottomBarRef.flBottomBar;
        llBottomBar.addView(plusButton);
        llBottomBar.addView(secondButton);
//        binding.bottomBarRef.llBottomBar.addView((View) plusButton);


        binding.newCardButton.setOnClickListener(this::AddCard);

        binding.reviewCardButton.setOnClickListener(view1 -> {
            Intent intent = new Intent(this, ReviewCard.class);
            startActivity(intent);
        });
    }

    public void addDeck(View view) {

    }

    public void AddCard(View view) {
        Intent intent = new Intent(this, AddCard.class);
        startActivity(intent);
    }
}