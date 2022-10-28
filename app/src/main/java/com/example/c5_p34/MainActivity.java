package com.example.c5_p34;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    DBHandler db;
    Button toAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHandler(this);
        ArrayList<HashMap<String,String>> todoList = db.GetAllInHash();
        lv = findViewById(R.id.list_todo);
        toAdd = findViewById(R.id.btnToAdd);
        ListAdapter adapter = new SimpleAdapter(this, todoList, R.layout.row_todo,
                new String [] { "id", "event", "duedate"},
                new int[] {R.id.idTodo, R.id.idEvent, R.id.idDuedate}) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                View v = convertView;
                for (int i = 0; i < todoList.size(); i++){
                    if(v== null){
                        LayoutInflater vi = (LayoutInflater)getLayoutInflater();
                        v=vi.inflate(R.layout.row_todo, null);
                    }
                    TextView i1 = v.findViewById(R.id.idTodo);
                    TextView i2 = v.findViewById(R.id.idEvent);
                    TextView i3 = v.findViewById(R.id.idDuedate);
                    String compare_due = todoList.get(position).get("duedate");
                    Date strDate = null;
                    try {
                        strDate = df.parse(compare_due);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    System.out.println("compare_due: " + compare_due);
                    boolean isOver = false;
                    if (new Date().after(strDate)) {
                        isOver = true;
                    }
                    if (isOver) v.setBackgroundColor(getResources().getColor(R.color.red));
                    i1.setText(todoList.get(position).get("id"));
                    i2.setText(todoList.get(position).get("event"));
                    i3.setText(todoList.get(position).get("duedate"));
                }

                return v;
            }
        };
        lv.setAdapter(adapter);
        toAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap<String,String> selectedItem = (HashMap<String, String>) adapterView.getItemAtPosition(i);
                int selectedId = Integer.parseInt(selectedItem.get("id"));
                db.deleteById(selectedId);
                Intent it = new Intent(MainActivity.this, DeleteActivity.class);
                startActivity(it);
            }
        });
    }
}
