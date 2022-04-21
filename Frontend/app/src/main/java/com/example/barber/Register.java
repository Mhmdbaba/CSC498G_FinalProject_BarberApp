package com.example.barber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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

        String name, username, email, password;

        public  Register_db (String name, String username, String email, String password){
            this.name = name;
            this.username = username;
            this.email = email;
            this.password = password;
        }

        @Override
        protected String doInBackground(String... urls) {
            JSONObject jsonObject = new JSONObject();

            URL url;
            HttpURLConnection http = null;
            OutputStream out = null;
            InputStream in = null;

            try{
                jsonObject.put("username", username);
                jsonObject.put("name",name);
                jsonObject.put("email",email);
                jsonObject.put("password",password);
                String message = jsonObject.toString();

                url = new URL(urls[0]);
                http = (HttpURLConnection) url.openConnection();

                //request setup
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);
                http.setFixedLengthStreamingMode(message.getBytes().length);

                http.setRequestProperty("Content-Type","application/json;charset=utf-8");
                http.setRequestProperty("X-Request-With","XMLHttpRequest");

                http.connect();

                out = new BufferedOutputStream(http.getOutputStream());
                out.write(message.getBytes());
                out.flush();

                in = http.getInputStream();;


            } catch (IOException e){
                e.printStackTrace();
                return null;
            } catch (JSONException e){
                e.printStackTrace();
                return null;
            } finally {

                try{
                    out.close();
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
                http.disconnect();
            }
            return  null;
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
                    Register_db r = new Register_db(reg_name,reg_username,reg_email,reg_password);
                    r.execute(url);
                    //direct to home page if credentials are not found in database
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