package com.example.barber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;

public class Book extends AppCompatActivity {

    Spinner sp;
    String selected_time;
    CheckBox cb_haircut;
    CheckBox cb_beardtrim;
    CheckBox cb_hottowels;
    CheckBox cb_scalpmassage;
    CheckBox cb_antidandruff;

    ArrayList<String> selected = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        cb_haircut = (CheckBox) findViewById(R.id.cb_haircut);
        cb_beardtrim = (CheckBox) findViewById(R.id.cb_beardtrim);
        cb_hottowels = (CheckBox) findViewById(R.id.cb_hottowel);
        cb_scalpmassage = (CheckBox) findViewById(R.id.cb_scalpmessage);
        cb_antidandruff = (CheckBox) findViewById(R.id.cb_antidandruff);

        //Get the available time slots from database and add them to spinner (sp) by array adapter

    }

    public void Button (View view){
        Button btn = (Button) view;

        if (btn.getTag().toString().equalsIgnoreCase("book")){
            //get the values checked and add them to an array;
            if (cb_haircut.isChecked())
                selected.add(cb_haircut.getText().toString());
            if (cb_beardtrim.isChecked())
                selected.add(cb_beardtrim.getText().toString());
            if (cb_hottowels.isChecked())
                selected.add(cb_hottowels.getText().toString());
            if (cb_scalpmassage.isChecked())
                selected.add(cb_scalpmassage.getText().toString());
            if (cb_antidandruff.isChecked())
                selected.add(cb_antidandruff.getText().toString());

            //make sure that values are selected
            if (sp.getSelectedItem() == null && !selected.isEmpty())
                Toast.makeText(this, "Please fill the required blanks!", Toast.LENGTH_LONG);
             else if (sp.getSelectedItem() == null && !selected.isEmpty())
                Toast.makeText(this, "Please fill the required blanks!", Toast.LENGTH_LONG);
             else if (sp.getSelectedItem() != null && selected.isEmpty())
                Toast.makeText(this, "Please fill the required blanks!", Toast.LENGTH_LONG);
             else {
                 //get time selected by user
                 selected_time = sp.getSelectedItem().toString();

                 //send the selected time and selected items to database
            }
        }

    }

}