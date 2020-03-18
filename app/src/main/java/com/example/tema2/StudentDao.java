package com.example.tema2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StudentDao {
    @Query("SELECT * FROM Student")
    List<Student> getAll();

    @Query("SELECT * FROM Student WHERE uid = (:userId)")
    Student findById(int userId);

    @Query("SELECT * FROM Student WHERE name = (:name)" )
    List<Student> findByName(String name);

    @Insert
    void insertAll(Student... students);

    @Delete
    void delete(Student student);

    @Insert
    void insertTask(Student student);
}
