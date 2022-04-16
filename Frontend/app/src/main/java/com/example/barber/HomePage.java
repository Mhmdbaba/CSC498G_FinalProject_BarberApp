package com.example.barber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    TextView tv_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        //tv_admin = (TextView) findViewById(R.id.tv_admin);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        if (!username.equalsIgnoreCase("admin"))
            tv_admin.setVisibility(View.INVISIBLE);



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
        TextView btn = (TextView) view;
        if (btn.getTag().toString().equalsIgnoreCase("logout")){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        if(btn.getTag().toString().equalsIgnoreCase("admin")){
            Intent intent = new Intent(this,admin.class);
            startActivity(intent);
        }
        if (btn.getText().toString().equalsIgnoreCase("catalogue")){
            Intent intent = new Intent(this, Catalogue.class);
            startActivity(intent);
        }
    }
}