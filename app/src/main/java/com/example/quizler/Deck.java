package com.example.quizler;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Deck {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "deck_name")
    public String name;

    public Deck(String name) {
        this.name = name;
    }
}
