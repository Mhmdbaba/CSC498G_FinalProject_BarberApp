package com.example.barber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class Book extends AppCompatActivity {

    Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        //Get the available time slots from database and add them to spinner (sp) by array adapter

    }

    public void Button (View view){
        Button btn = (Button) view;

        if (btn.getTag().toString().equalsIgnoreCase("book")){

        }

    }

}