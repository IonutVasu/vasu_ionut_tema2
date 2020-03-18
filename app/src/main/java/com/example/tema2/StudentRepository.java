package com.example.tema2;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.List;

public class StudentRepository {

    private String DB_NAME = "db_stud";
    private BazaStudenti bazaStudenti;

    public StudentRepository(Context context){

        bazaStudenti = Room.databaseBuilder(context, BazaStudenti.class, DB_NAME).allowMainThreadQueries().build();
    }

    public void insertStudent(String name, int mark) {

        Student student = new Student();
        student.setName(name);
        student.setMark(mark);

        insertStudent(student);
    }

    public void insertStudent(final Student student){
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                bazaStudenti.studentDao().insertTask(student);
                return null;
            }
        }.execute();
    }

    public List<Student> getStudents(){
        return bazaStudenti.studentDao().getAll();
    }

    public List<Student> getStudents(String name){
        return bazaStudenti.studentDao().findByName(name);
    }

    public Student getStudent(int id){
        return bazaStudenti.studentDao().findById(id);
    }

    public void deleteStudent(final int id){
        final Student student = getStudent(id);
        if(student != null){
            new AsyncTask<Void, Void, Void>(){
                @Override
                protected Void doInBackground(Void... voids) {

                    bazaStudenti.studentDao().delete(student);
                    return null;
                }
            }.execute();
        }
    }

    public boolean deleteStudent(final String name){   //ii sterge pe toti cu numele asta
        List<Student> studenti = getStudents(name);
        if(!studenti.isEmpty()){
            for(Student student : studenti){
                deleteStudent(student.getUid());
            }
            return true;
        }
        return false;
    }

}


