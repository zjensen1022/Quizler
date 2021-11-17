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

        //test functionality
        deck.get().add(new Card("Front 1", "Back 1"));
        deck.get().add(new Card("Front 2", "Back 2"));
        deck.get().add(new Card("Front 3", "Back 3"));
    }
}
