package com.example.frameworkroom;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "nauczyciele")
public class Nauczyciel {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String imie;
    public String przedmiot;

    public Nauczyciel(String przedmiot, String imie) {
        this.imie = imie;
        this.przedmiot = przedmiot;
    }

    @Override
    public String toString() {
        return  "imie: " + imie + '\n' +
                "przedmiot: " + przedmiot;
    }
}
