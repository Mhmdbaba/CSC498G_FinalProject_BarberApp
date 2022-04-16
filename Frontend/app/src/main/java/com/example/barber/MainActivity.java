package com.example.barber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    String input_username;
    String input_password;


    public class DownloadTask extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection http;

            try {
                url = new URL(urls[0]);
                http = (HttpURLConnection) url.openConnection();

                InputStream in = http.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data= reader.read();

                while (data != -1){
                    char curr = (char) data;
                    result +=curr;
                    data = reader.read();
                }
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
        setContentView(R.layout.activity_main);


        String url = "http://192.168.157.1/BarberServer/login.php";
        DownloadTask task = new DownloadTask();
        task.execute(url);

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
            input_username = ((EditText) findViewById(R.id.input_username)).getText().toString();
            input_password = ((EditText) findViewById(R.id.input_password)).getText().toString();


            if (!input_password.isEmpty() && !input_username.isEmpty()){

                if (input_username.equals("admin") && input_password.equals("admin123")){
                    Intent intent = new Intent(this, HomePage.class);
                    intent.putExtra("username",input_username);
                    startActivity(intent);
                }
                /*
                //check if username and password are found in database
                Intent intent = new Intent(this, HomePage.class);
                intent.putExtra("username",input_username);
                startActivity(intent);
                 */
                else{
                    Toast.makeText(this,"Please enter username and password", Toast.LENGTH_LONG).show();
                    return;
                }

            }
            else{
                Toast.makeText(this,"Please enter username and password", Toast.LENGTH_LONG).show();
                return;
            }


        }
        else if (btn.getTag().toString().equalsIgnoreCase(("register"))){
            //In case the user doesn't have an account
            Intent intent = new Intent(this, Register.class);
            startActivity(intent);
        }
    }
}