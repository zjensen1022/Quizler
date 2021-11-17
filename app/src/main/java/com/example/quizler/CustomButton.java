package com.example.quizler;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class CustomButton extends ConstraintLayout {
    Button button;
    private void initCustomButton() {
        inflate(getContext(), R.layout.button, this);
        button = this.findViewById(R.id.customButton);
    }

    public CustomButton(@NonNull Context context) {
        super(context);
        initCustomButton();
    }

    public CustomButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initCustomButton();
    }

    public CustomButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCustomButton();
    }

    public CustomButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initCustomButton();
    }
}
