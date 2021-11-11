package com.example.quizler;

public class Card {

    String frontText;
    String backText;

    public Card(String frontText, String backText) {
        this.frontText = frontText;
        this.backText = backText;
    }

    public String getFrontText() {
        return frontText;
    }

    public String getBackText() {
        return backText;
    }
}
