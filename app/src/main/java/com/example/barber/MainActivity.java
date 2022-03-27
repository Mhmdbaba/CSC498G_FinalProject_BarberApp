package com.example.barber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Button(View view){
        Button btn = (Button) view;

        if (btn.getTag().toString().equalsIgnoreCase("forgot")){
            //In case the user forgets password
            /*
            Intent intent = new Intent(this, Forgot.class);
            startActivity(intent);
             */
        }
        else if (btn.getTag().toString().equalsIgnoreCase("login")){
            //In case the user presses login
            //check the user credentials in database and direct the user to home page
            /*
            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
             */
        }
        else if (btn.getTag().toString().equalsIgnoreCase(("register"))){
            //In case the user doesn't have an account
            Intent intent = new Intent(this, Register.class);
            startActivity(intent);
        }
    }
}