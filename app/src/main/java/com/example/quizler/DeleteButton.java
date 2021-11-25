package com.example.quizler;

import android.content.Context;

import androidx.annotation.NonNull;

public class DeleteButton extends CustomButton{

    @Override
    protected void initCustomButton() {
        inflate(getContext(), R.layout.delete_button, this);
        button = this.findViewById(R.id.deleteButton);
    }

    public DeleteButton(@NonNull Context context) {
        super(context);
    }
}
