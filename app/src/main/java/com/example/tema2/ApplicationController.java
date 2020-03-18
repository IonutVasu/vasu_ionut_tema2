package com.example.tema2;

import android.app.Application;

import androidx.room.Room;

public class ApplicationController extends Application {
    private static ApplicationController mInstance;
    private static BazaStudenti mBazaStudenti;

    public static ApplicationController getInstance(){
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        mBazaStudenti = Room.databaseBuilder(getApplicationContext(), BazaStudenti.class, "baza_useri").build();
    }

    public static BazaStudenti getBaza(){
        return mBazaStudenti;
    }
}
