package com.example.barber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Register extends AppCompatActivity {

    String reg_name;
    String reg_username;
    String reg_email;
    String reg_password;
    String reg_conf_password;

    public class Register_db extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection http;


            try {
                url = new URL(urls[0]);
                http = (HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST");
                http.setRequestProperty("Content-Type", "application/json; utf-8");
                http.setRequestProperty("Accept", "application/json");
                http.setDoOutput(true);

                String jsonInputString = "{username:" + reg_username + "," +
                        "password:" + reg_password + "email:" + reg_email + "name:" + reg_name + "}";

                OutputStream os = http.getOutputStream();
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);

                /*
                InputStream in = http.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data= reader.read();

                while (data != -1){
                    char curr = (char) data;
                    result +=curr;
                    data = reader.read();
                }*/
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
            return result;
        }
    }


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