package com.example.quizler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class CardListActivity extends AppCompatActivity {

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);

        Intent intent = getIntent();
//        db = Room.databaseBuilder(getApplicationContext(),
//                AppDatabase.class, "quizler").build();
//        CardDao cardDao = db.cardDao();
//        List<Card> cards = cardDao.loadAllByDeckId(intent.getIntExtra("deck_id", 0));
    }
}