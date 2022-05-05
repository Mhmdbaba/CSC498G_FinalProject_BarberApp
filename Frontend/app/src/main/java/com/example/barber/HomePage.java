package com.example.barber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HomePage extends AppCompatActivity {

    TextView tv_admin;
    String username;

    TextView tv_output_appointment;
    TextView tv_edit;
    TextView tv_cancel;


    public class MainPage extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection http;


            try {
                url = new URL(urls[0]);
                http = (HttpURLConnection) url.openConnection();

                InputStream in = http.getInputStream();

                Log.i("onPostExecute: ","hello");

                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }

            } catch( Exception e) {
                e.printStackTrace();
                return null;
            }
            return result;
    }

    @Override
        protected void onPostExecute (String s) {
            super.onPostExecute(s);
//        Log.i("onPostExecute: ",s);

            try {
                JSONObject json = new JSONObject(s);

                for (int i = 0; i < json.length(); i++){
                    if (json.getJSONArray(String.valueOf(i)).getString(0) == username){

                        tv_output_appointment.setVisibility(View.VISIBLE);
                        tv_edit.setVisibility(View.VISIBLE);
                        tv_cancel.setVisibility(View.VISIBLE);

                        //display the appointment if any in the user home page
                        tv_output_appointment.setText(json.getJSONArray(String.valueOf(i)).getString(2) + " " +
                                json.getJSONArray(String.valueOf(i)).getString(3));

                        break;
                    } else {
                        tv_output_appointment.setVisibility(View.INVISIBLE);
                        tv_edit.setVisibility(View.INVISIBLE);
                        tv_cancel.setVisibility(View.INVISIBLE);
                    }
                }

//                Log.i("onPostExecute: ",s);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
    }


}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        //tv_admin = (TextView) findViewById(R.id.tv_admin);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        tv_output_appointment = (TextView) findViewById(R.id.output_appointment);
        tv_edit = (TextView) findViewById(R.id.tv_edit);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);

        if (!username.equalsIgnoreCase("admin"))
            tv_admin.setVisibility(View.INVISIBLE);
        String url = "http://192.168.157.1/BarberServer/homePage.php";
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