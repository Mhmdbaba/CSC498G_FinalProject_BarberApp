package com.example.barber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

    private void checkInDatabase(String s, String type){

    }

    public void Button(View view){
        Button btn = (Button) view;

        if(btn.getTag().toString().equalsIgnoreCase("back")){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if (btn.getTag().toString().equalsIgnoreCase("signup")){
            reg_name = ((EditText) findViewById(R.id.input_reg_name)).getText().toString();
            reg_email = ((EditText) findViewById(R.id.input_reg_email)).getText().toString();
            reg_username = ((EditText) findViewById(R.id.input_reg_username)).getText().toString();
            reg_password = ((EditText) findViewById(R.id.input_reg_password)).getText().toString();
            reg_conf_password = ((EditText) findViewById(R.id.input_reg_conf_password)).getText().toString();

            if (!reg_name.isEmpty() && !reg_username.isEmpty() && !reg_email.isEmpty() && !reg_password.isEmpty()
            && !reg_conf_password.isEmpty()){
                //1 check if username and email already found in database
                //2 check passwords, if they match
                //3 insert credentials to database
                //4 direct to home page
            

            /*Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);*/
            } else {
                Toast.makeText(this, "Enter all Fields", Toast.LENGTH_SHORT).show();
                return;
            }
            

        }
    }
}