package com.example.barber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }

    public void Appointment(View view) {
        Button btn = (Button) view;

        if (btn.getTag().toString().equalsIgnoreCase("book")){ //book an appointment getting the button by using tag
            Intent intent = new Intent(this,Book.class);
            startActivity(intent);
        }
        else if (btn.getTag().toString().equalsIgnoreCase("edit")){ //editing the appointment
            Intent intent = new Intent(this, Book.class);
            startActivity(intent);
        }
        else if (btn.getTag().toString().equalsIgnoreCase("cancel")){
            //make the appointment non active in the database
        }

    }

    public void Button(View view){
        Button btn = (Button) view;

        if (btn.getTag().toString().equalsIgnoreCase("back")){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }
}