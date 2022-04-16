package com.example.barber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class admin extends AppCompatActivity {

    ArrayList<String> arr = new ArrayList<>();
    ArrayAdapter<String> arrAd;
    Spinner sp_cate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        sp_cate = (Spinner) findViewById(R.id.sp_category);
        arr.add("Select");
        arr.add("Haircut");
        arr.add("Treatments");
        arr.add("Hair Gel & Wax");
        arr.add("Oils");
        arr.add("Accessories");
        arrAd = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arr);
        sp_cate.setAdapter(arrAd);
    }

    public void Button(View view) {
        Button btn = (Button) view;

        if(btn.getTag().toString().equalsIgnoreCase("back")){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }

}