package com.example.frameworkroom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NauczycielDAO {

    @Insert
    void wstawNauczycielaDoBazy(Nauczyciel nauczyciel);

    @Insert
    void wstawListeNauczycieli(Nauczyciel ... nauczyciels);

    @Delete
    void usunNauczycielaZBazy(Nauczyciel nauczyciel);

    @Update
    void zaktualizujDaneNauczyciela(Nauczyciel nauczyciel);

    @Query("SELECT * from nauczyciele")
    List<Nauczyciel> zworcWszystkichNauczycielliZBazy();

    @Query("SELECT imie from nauczyciele WHERE przedmiot == :przedmiotArgument")
    List<String> zwrocImionaNauczycieliZPrzedmiotu(String przedmiotArgument);

}
