package com.example.c5_p34;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    EditText eventName;
    EditText dueDate;
    Button submitEvent;
    DBHandler dbManager;
    Button backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        dbManager = new DBHandler(this);
        eventName = findViewById(R.id.eventName);
        dueDate = findViewById(R.id.dueDate);
        submitEvent = findViewById(R.id.submitTodo);
        backBtn = findViewById(R.id.backButton);
        submitEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eN = eventName.getText().toString();
                String dD = dueDate.getText().toString();
                System.out.println("hi "+ eN);
                System.out.println("there " + dD);
                Todo todo = new Todo(0, eN, dD);
                dbManager.insert(todo);
                Toast.makeText(getApplicationContext(),"Successfully added",Toast.LENGTH_SHORT).show();
                eventName.setText("");
                dueDate.setText("");
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddActivity.this, MainActivity.class);
                startActivity(i);
            }
        });




    }
}