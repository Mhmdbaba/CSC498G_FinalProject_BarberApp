package com.example.barber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Register extends AppCompatActivity {

    String reg_name;
    String reg_username;
    String reg_email;
    String reg_password;
    String reg_conf_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void Button(View view){
        Button btn = (Button) view;

        if(btn.getTag().toString().equalsIgnoreCase("back")){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else if (btn.getTag().toString().equalsIgnoreCase("signup")){
            reg_name = (String) findViewById(R.id.input_reg_name).toString();
            reg_email = (String) findViewById(R.id.input_reg_email).toString();
            reg_username = (String) findViewById(R.id.input_reg_username).toString();
            reg_password = findViewById(R.id.input_password).toString();
            reg_conf_password = findViewById(R.id.input_reg_conf_password).toString();

            //check if username and email already found in database
            //check passwords, if they match
            //insert credentials to database
            //direct to home page

            /*Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);*/
        }
    }
}