package com.example.frameworkroom;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Nauczyciel.class}, version = 1)
public abstract class SzkolaDataBase extends RoomDatabase {
    public abstract NauczycielDAO getNauczycielDAO();
}
