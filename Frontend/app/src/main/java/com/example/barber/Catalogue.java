package com.example.barber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Catalogue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogue);
    }

    public void Button(View view) {
        Button btn = (Button) view;

        if (btn.getTag().toString().equalsIgnoreCase("back")) {
            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
        }
    }

}