package com.example.quizler;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DeckDao {
    @Query("SELECT * FROM deck")
    List<Deck> getAll();

    @Query("SELECT * FROM deck WHERE id IN (:deckIds)")
    List<Deck> loadAllByIds(int[] deckIds);

    @Query("SELECT * FROM deck WHERE id LIKE :id LIMIT 1")
    Deck findById(int id);

    @Query("SELECT * FROM card WHERE deck_id IS :deckId")
    List<Card> getCards(int deckId);

    @Query("SELECT COUNT(*) FROM card WHERE deck_id IS :deckId")
    int getCardCount(int deckId);

    @Insert
    void insertAll(Deck... decks);

    @Delete
    void delete(Deck deck);
}
