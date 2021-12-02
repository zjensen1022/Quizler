package com.example.quizler;

import android.widget.TextView;

import android.os.Handler;

public class Timer {
    private TextView countText;

    private int timeInSeconds = 0;
    private boolean timeRunning;

    public void timerStart()        { timeRunning = true;}
    public int getTimeInSeconds()   { return timeInSeconds;}
    public void timerStop()         { timeRunning = false;}

    public void timerRestart()
    {
        timeRunning = false;
        timeInSeconds = 0;
    }

    public void runTimer()
    {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                if(timeRunning)
                {
                    timeInSeconds++;
                }
                handler.postDelayed(this, 1000);

            }
        });

    }

}
