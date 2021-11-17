package com.example.quizler;

import java.util.ArrayList;

public class DataHandler {

    private static final ArrayList<Card> cards = new ArrayList<>();

    public static void loadFromJson() {
        Thread thread = new Thread(new JsonReader(cards));
        thread.start();
    }

    public static ArrayList<Card> getCards() {
        return cards;
    }
}
