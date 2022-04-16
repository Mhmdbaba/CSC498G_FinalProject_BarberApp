package com.example.barber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

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
        cb_scalpmassage = (CheckBox) findViewById(R.id.cb_scalpmassage);
        cb_antidandruff = (CheckBox) findViewById(R.id.cb_antidandruff);
        sp = (Spinner) findViewById(R.id.spinner);

        //Get the available time slots from database and add them to spinner (sp) by array adapter

    }

    public void Button (View view){
        Button btn = (Button) view;

        if (btn.getTag().toString().equalsIgnoreCase("book")){
            //get the values checked and add them to an array;
            if (cb_haircut.isChecked())
                selected.add("Haircut");
            if (cb_beardtrim.isChecked())
                selected.add("Beard Trim");
            if (cb_hottowels.isChecked())
                selected.add("Hot Towels");
            if (cb_scalpmassage.isChecked())
                selected.add("Scalp Massage");
            if (cb_antidandruff.isChecked())
                selected.add("Anti-dandruff Remedies");


            //make sure that values are selected
            if(!sp.getSelectedItem().toString().isEmpty() && !selected.isEmpty()){
                //get time selected by user
                selected_time = sp.getSelectedItem().toString();

                //send the selected time and selected items to database

                Intent intent = new Intent(this, Register.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Please fill the required blanks!", Toast.LENGTH_LONG);
                return;
            }

        }
        if (btn.getTag().toString().equalsIgnoreCase("back")){
            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
        }

    }

}