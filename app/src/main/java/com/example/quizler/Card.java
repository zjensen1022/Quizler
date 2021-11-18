package com.example.quizler;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
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

    public Card(String title, String description, int cardId) {
        this(title, description);
        id = cardId;
    }


    public Card(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
