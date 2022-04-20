package com.example.barber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Register extends AppCompatActivity {

    String reg_name;
    String reg_username;
    String reg_email;
    String reg_password;
    String reg_conf_password;
    boolean cont;

    public class Register_db extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection http;


            try {
                url = new URL(urls[0]);
                http = (HttpURLConnection) url.openConnection();

                //set request method
                http.setRequestMethod("POST");
                http.setRequestProperty("Content-Type", "application/json; utf-8");
                http.setRequestProperty( "charset", "utf-8");
                http.setRequestProperty("Accept", "application/json");
                http.setDoOutput(true);

                String jsonInputString = "{username:" + reg_username + "," +
                        "password:" + reg_password + "email:" + reg_email + "name:" + reg_name + "}";

                String urlParams = "username=" + reg_username + "&password=" + reg_password +
                        "&email=" + reg_email + "&name=" + reg_name;
                byte[] postData = urlParams.getBytes( StandardCharsets.UTF_8 );
                int postDataLength = postData.length;

                DataOutputStream wr = new DataOutputStream(http.getOutputStream());
                wr.write(postData);



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
            if (s.equalsIgnoreCase("true"))
                cont = true;
            else
                cont = false;
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
                //check passwords, if they match
                if (reg_password.equals(reg_conf_password)){
                    //insert credentials to database
                    String url = "http://192.168.157.1/BarberServer/Register.php";
                    Register_db r = new Register_db();
                    r.execute(url);
                    //4 direct to home page
                }
                else {
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

            /*Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);*/
            } else {
                Toast.makeText(this, "Enter all Fields", Toast.LENGTH_SHORT).show();
                return;
            }
            

        }
    }
}