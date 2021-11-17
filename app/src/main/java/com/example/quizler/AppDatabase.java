package com.example.quizler;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Deck.class, Card.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DeckDao deckDao();
    public abstract CardDao cardDao();
}
