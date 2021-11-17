package com.example.quizler;

import android.app.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class DatabaseHelper {
    static final String DB_NAME = "quizler";
    public static void loadDecks(Activity activity) {
        AppDatabase db = Room.databaseBuilder(activity.getApplicationContext(),
                AppDatabase.class, DB_NAME).build();
        DeckDao deckDao = db.deckDao();
        List<Deck> decks = deckDao.getAll();
        db.close();
        activity.runOnUiThread(() -> {
            RecyclerView rv = (RecyclerView) activity.findViewById(R.id.rvDecks);
            rv.setLayoutManager(new LinearLayoutManager(activity));
            rv.setAdapter(new DeckListAdapter(decks));
        });
    }
    public static void saveDeck(String deckName, Activity activity) {
        AppDatabase db = Room.databaseBuilder(activity.getApplicationContext(),
                AppDatabase.class, DB_NAME).build();
        DeckDao deckDao = db.deckDao();
        Deck deck = new Deck(deckName);
        deckDao.insertAll(deck);
        db.close();
        activity.finish();
    }
}
