package com.example.frameworkroom;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button button, aktualizuj;
    ListView listView;
    List<Nauczyciel> nauczycielList;
    ArrayAdapter<Nauczyciel> arrayAdapter;
    EditText editText;
    Spinner spinner;
    SzkolaDataBase szkolaDataBase;
    Nauczyciel wybranyNauczyciel = null;

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
        listView = findViewById(R.id.listView);
        nauczycielList = szkolaDataBase.getNauczycielDAO().zworcWszystkichNauczycielliZBazy();
        arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, nauczycielList);
        listView.setAdapter(arrayAdapter);
        aktualizuj = findViewById(R.id.aktualizacja);

        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Nauczyciel nauczyciel = new Nauczyciel(
                                spinner.getSelectedItem().toString(),
                                editText.getText().toString()
                        );
                        szkolaDataBase.getNauczycielDAO().wstawNauczycielaDoBazy(nauczyciel);
                        //nauczycielList = szkolaDataBase.getNauczycielDAO().zworcWszystkichNauczycielliZBazy();
                        nauczycielList.add(nauczyciel);
                        arrayAdapter.notifyDataSetChanged();

                    }
                }
        );

        listView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        szkolaDataBase.getNauczycielDAO().usunNauczycielaZBazy(nauczycielList.get(position));
                        nauczycielList.remove(position);
                        arrayAdapter.notifyDataSetChanged();
                        return false;
                    }
                }
        );

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        aktualizuj.setVisibility(View.VISIBLE);
                        wybranyNauczyciel = nauczycielList.get(position);
                        editText.setText(wybranyNauczyciel.imie);
                        ArrayList<String> przedmioty = new ArrayList<>(
                                Arrays.asList("wf", "plastyka", "informatyka", "programowanie", "fizyka", "język angileski", "język chinski")
                        );
                        spinner.setSelection(przedmioty.indexOf(wybranyNauczyciel.przedmiot));

                    }
                }
        );

        aktualizuj.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        wybranyNauczyciel.przedmiot = spinner.getSelectedItem().toString();
                        wybranyNauczyciel.imie = editText.getText().toString();
                        szkolaDataBase.getNauczycielDAO().zaktualizujDaneNauczyciela(wybranyNauczyciel);
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
        );


    }
}