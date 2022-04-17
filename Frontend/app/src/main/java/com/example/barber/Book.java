package com.example.barber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;

public class Book extends AppCompatActivity {

    Spinner sp;
    String selected_time;
    CheckBox cb_haircut;
    CheckBox cb_beardtrim;
    CheckBox cb_hottowels;
    CheckBox cb_scalpmassage;
    CheckBox cb_antidandruff;

    ArrayList<String> selected = new ArrayList<String>();

    ArrayList<String> arr = new ArrayList<>();
    ArrayAdapter<String> arrAd;

    public class TimeSlots extends AsyncTask<String, Void, String>{

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

        protected void onPostExecute (String s){
            super.onPostExecute(s);

            try{
                JSONArray json = new JSONArray(s);
                ArrayList<String>arrayl = new ArrayList<String>();
                for (int i = 0; i < json.length(); i++){
                    arrayl.add(json.getString(i).toString().substring(json.getString(i).toString().indexOf("e")));
                }
                for (int i = 0; i < arrayl.size(); i++){
                    arr.add(arrayl.get(i).substring(4,9));
                    Log.i("onPostExecute: ",arrayl.get(i).substring(4,9));
                }

            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        cb_haircut = (CheckBox) findViewById(R.id.cb_haircut);
        cb_beardtrim = (CheckBox) findViewById(R.id.cb_beardtrim);
        cb_hottowels = (CheckBox) findViewById(R.id.cb_hottowel);
        cb_scalpmassage = (CheckBox) findViewById(R.id.cb_scalpmassage);
        cb_antidandruff = (CheckBox) findViewById(R.id.cb_antidandruff);
        sp = (Spinner) findViewById(R.id.spinner);

        //Get the available time slots from database and add them to spinner (sp) by array adapter
        String url = "http://192.168.157.1/BarberServer/GetTimeSlots.php";
        TimeSlots ts = new TimeSlots();
        ts.execute(url);
        arrAd = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arr);
        sp.setAdapter(arrAd);
    }

    public void Button (View view){
        Button btn = (Button) view;

        if (btn.getTag().toString().equalsIgnoreCase("book")){
            //get the values checked and add them to an array;
            if (cb_haircut.isChecked())
                selected.add("Haircut");
            if (cb_beardtrim.isChecked())
                selected.add("Beard Trim");
            if (cb_hottowels.isChecked())
                selected.add("Hot Towels");
            if (cb_scalpmassage.isChecked())
                selected.add("Scalp Massage");
            if (cb_antidandruff.isChecked())
                selected.add("Anti-dandruff Remedies");


            //make sure that values are selected
            if(!sp.getSelectedItem().toString().isEmpty() && !selected.isEmpty()){
                //get time selected by user
                selected_time = sp.getSelectedItem().toString();

                //send the selected time and selected items to database

                Intent intent = new Intent(this, Register.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Please fill the required blanks!", Toast.LENGTH_LONG);
                return;
            }

        }
        if (btn.getTag().toString().equalsIgnoreCase("back")){
            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
        }

    }

}