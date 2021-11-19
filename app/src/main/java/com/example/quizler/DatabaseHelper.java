package com.example.quizler;

import android.app.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class DatabaseHelper {
    static final String DB_NAME = "quizler";
    private static AppDatabase createDB(Activity activity) {
        return Room.databaseBuilder(activity.getApplicationContext(),
                AppDatabase.class, DB_NAME).build();
    }
    public static void loadDecksForMain(MainActivity activity) {
        AppDatabase db = createDB(activity);
        DeckDao deckDao = db.deckDao();
        List<Deck> decks = deckDao.getAll();
        db.close();
        activity.runOnUiThread(() -> {
            RecyclerView rv = activity.findViewById(R.id.rvDecks);
            rv.setLayoutManager(new LinearLayoutManager(activity));
            rv.setAdapter(new DeckListAdapter(decks));
        });
    }
    public static void getSingleDeck(int deckId, AddDeckActivity activity) {
        AppDatabase db = createDB(activity);
        DeckDao deckDao = db.deckDao();
        activity.currentDeck = deckDao.findById(deckId);
        db.close();
        activity.runOnUiThread(activity::displayDeckData);
    }
    public static void saveDeck(String deckName, AddDeckActivity activity) {
        AppDatabase db = createDB(activity);
        DeckDao deckDao = db.deckDao();
        Deck deck = new Deck(deckName);
        deckDao.insertAll(deck);
        db.close();
        activity.finish();
    }
    public static void updateDeck(Deck deck, AddDeckActivity activity) {
        AppDatabase db = createDB(activity);
        DeckDao deckDao = db.deckDao();
        deckDao.update(deck);
        db.close();
        activity.setResult(Activity.RESULT_OK);
        activity.finish();
    }
    public static void deleteDeck(Deck deck, AddDeckActivity activity) {
        AppDatabase db = createDB(activity);
        DeckDao deckDao = db.deckDao();
        deckDao.delete(deck);
        db.close();
        activity.setResult(Activity.RESULT_OK);
        activity.finish();
    }
    public static void loadCardsForList(int deck_id, CardListActivity activity) {
        AppDatabase db = createDB(activity);
        CardDao cardDao = db.cardDao();
        List<Card> deck = cardDao.loadAllByDeckId(deck_id);
        db.close();
        activity.runOnUiThread(() -> {
            RecyclerView rv = activity.findViewById(R.id.rvCards);
            rv.setLayoutManager(new LinearLayoutManager(activity));
            rv.setAdapter(new CardListAdapter(deck));
        });
    }
    public static void loadCardsForReview(int deck_id, ReviewCardActivity activity) {
        AppDatabase db = createDB(activity);
        CardDao cardDao = db.cardDao();
        activity.deck = cardDao.loadAllByDeckId(deck_id);
        activity.runOnUiThread(activity::setupReview);
        db.close();
    }
    public static void getSingleCardForEdit(int cardId, AddCardActivity activity) {
        AppDatabase db = createDB(activity);
        CardDao cardDao = db.cardDao();
        activity.currentCard = cardDao.findById(cardId);
        db.close();
        activity.runOnUiThread(activity::displayCardData);
    }
    public static void saveCard(Card card, AddCardActivity activity) {
        AppDatabase db = createDB(activity);
        CardDao cardDao = db.cardDao();
        cardDao.insertAll(card);
        db.close();
        activity.finish();
    }
    public static void updateCard(Card card, AddCardActivity activity) {
        AppDatabase db = createDB(activity);
        CardDao cardDao = db.cardDao();
        cardDao.update(card);
        db.close();
        activity.finish();
    }
    public static void deleteCard(Card card, AddCardActivity activity) {
        AppDatabase db = createDB(activity);
        CardDao cardDao = db.cardDao();
        cardDao.delete(card);
        db.close();
        activity.finish();
    }
}
