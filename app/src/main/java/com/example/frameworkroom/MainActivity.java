package com.example.frameworkroom;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;
import androidx.room.RoomDatabase;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText editText;
    Spinner spinner;
    SzkolaDataBase szkolaDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        szkolaDataBase = Room.databaseBuilder(
                getApplicationContext(), SzkolaDataBase.class, "szkola_db")
                .allowMainThreadQueries()
                .build();

//        szkolaDataBase.getNauczycielDAO().wstawNauczycielaDoBazy(
//                new Nauczyciel("Anna", "matematyka")
//        );

        button = findViewById(R.id.button);
        editText = findViewById(R.id.editTextText);
        spinner = findViewById(R.id.spinner);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Nauczyciel nauczyciel = new Nauczyciel(
                                editText.getText().toString(),
                                spinner.getSelectedItem().toString()
                        );
                        szkolaDataBase.getNauczycielDAO().wstawNauczycielaDoBazy(nauczyciel);


                    }
                }
        );

    }
}