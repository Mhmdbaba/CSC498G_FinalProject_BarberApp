package com.example.barber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HomePage extends AppCompatActivity {

    TextView tv_admin;

    public class MainPage extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection http;

            BufferedReader reader;
            String line;
            StringBuffer response_content = new StringBuffer();

            try {
                url = new URL(urls[0]);
                http = (HttpURLConnection) url.openConnection();

                //request setup
                http.setRequestMethod("GET");
                http.setConnectTimeout(5000);
                http.setReadTimeout(5000);

                int status = http.getResponseCode();

                if (status > 299){
                    reader = new BufferedReader(new InputStreamReader(http.getErrorStream()));
                    while ((line = reader.readLine()) != null){
                        response_content.append(line);
                    }
                    reader.close();
                } else {
                    reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
                    while ((line = reader.readLine()) != null){
                        response_content.append(line);
                    }
                    reader.close();
                }

            } catch (Exception e){
                e.printStackTrace();
                return null;
            }

            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        //tv_admin = (TextView) findViewById(R.id.tv_admin);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        if (!username.equalsIgnoreCase("admin"))
            tv_admin.setVisibility(View.INVISIBLE);
        String url = "http://192.168.157.1/BarberServer/Register.php";
        MainPage r = new MainPage();
        r.execute(url);
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