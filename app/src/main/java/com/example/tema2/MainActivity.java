package com.example.tema2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button buttonInsert;
    private Button buttonRemove;
    private EditText editTextName;
    private EditText editTextMark;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final StudentRepository studentRepository = new StudentRepository(getApplicationContext());


        buttonInsert = findViewById(R.id.button1);
        buttonRemove = findViewById(R.id.button2);
        editTextName = findViewById(R.id.textEditor1);
        editTextMark = findViewById(R.id.textEditor2);

        recyclerView = (RecyclerView) findViewById(R.id.main_recycler);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editTextName.getText().toString();
                String mark = editTextMark.getText().toString();
                studentRepository.insertStudent(name, 10);



                List<Student> studenti = studentRepository.getStudents();
                StringBuilder sb = new StringBuilder();
                for(Student student : studenti){
                    sb.append(student.getName()).append(", ").append(student.getMark()).append(".\n");
                }
                Toast toast = Toast.makeText(getApplicationContext(), sb.toString(), Toast.LENGTH_LONG);
                toast.show();
            }
        });

        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                if(!studentRepository.deleteStudent(name)){
                    Toast toast = Toast.makeText(getApplicationContext(), "Niciun student cu acest nume", Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        });

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<Student> studenti = studentRepository.getStudents();
        mAdapter = new MyAdapter(studenti);
        recyclerView.setAdapter(mAdapter);
    }

}