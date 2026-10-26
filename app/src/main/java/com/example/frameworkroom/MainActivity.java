package com.example.frameworkroom;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;
import androidx.room.RoomDatabase;

public class MainActivity extends AppCompatActivity {

    SzkolaDataBase szkolaDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        szkolaDataBase = Room.databaseBuilder(
                getApplicationContext(), SzkolaDataBase.class, "szkola_db")
                .allowMainThreadQueries()
                .build();

        szkolaDataBase.getNauczycielDAO().wstawNauczycielaDoBazy(
                new Nauczyciel("Anna", "matematyka")
        );
    }
}