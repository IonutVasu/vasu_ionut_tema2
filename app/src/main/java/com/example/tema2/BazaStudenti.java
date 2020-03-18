package com.example.tema2;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class BazaStudenti extends RoomDatabase {

    public abstract StudentDao studentDao();

}