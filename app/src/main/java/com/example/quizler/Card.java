package com.example.quizler;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Card {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "card_title")
    public String title;
    @ColumnInfo(name = "card_description")
    public String description;
    @ColumnInfo(name = "deck_id")
    public int deckId;

    public Card(String title, String description, int deckId) {
        this.title = title;
        this.description = description;
        this.deckId = deckId;
    }

    @Ignore
    public Card(String title, String description, int deckId, int cardId) {
        this(title, description, deckId);
        id = cardId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
