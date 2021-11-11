package com.example.quizler;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class JsonReader implements Runnable{

    WeakReference<ArrayList<Card>> deck;

    public JsonReader(ArrayList<Card> deck) {
        this.deck = new WeakReference<>(deck);
    }

    @Override
    public void run() {
        deck.get().add(new Card("test card", "test"));
    }
}
