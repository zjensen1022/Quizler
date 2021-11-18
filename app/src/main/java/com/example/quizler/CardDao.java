package com.example.quizler;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CardDao {
    @Query("SELECT * FROM card")
    List<Card> getAll();

    @Query("SELECT * FROM card WHERE deck_id IS :deckId")
    List<Card> loadAllByDeckId(int deckId);

    @Query("SELECT * FROM card WHERE id IS :id LIMIT 1")
    Card findById(int id);

    @Insert
    void insertAll(Card... cards);

    @Update
    void update(Card card);

    @Delete
    void delete(Card card);
}
