package com.example.quizler;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class BottomBarItem extends ConstraintLayout {
    public ImageView icon;
    private void initBottomBarItem() {
        inflate(getContext(), R.layout.bottom_bar_item, this);
        icon = this.findViewById(R.id.ivIcon);
    }

    public BottomBarItem(@NonNull Context context) {
        super(context);
        initBottomBarItem();
    }

    public BottomBarItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initBottomBarItem();
    }

    public BottomBarItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initBottomBarItem();
    }

    public BottomBarItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initBottomBarItem();
    }
}
