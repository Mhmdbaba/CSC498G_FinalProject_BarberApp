package com.example.barber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;


import org.apache.http.params.HttpConnectionParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class admin extends AppCompatActivity {

    ArrayList<String> arr = new ArrayList<>();
    ArrayAdapter<String> arrAd;
    Spinner sp_cate;
    ImageView iv_uploaded_image;
    private static final int RESULT_LOAD_IMAGE = 1;
    String name, price, category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        iv_uploaded_image = findViewById(R.id.iv_uploaded_image);

        sp_cate = (Spinner) findViewById(R.id.sp_category);
        arr.add("Select");
        arr.add("Haircuts");
        arr.add("Treatments");
        arr.add("Hair Gel & Wax");
        arr.add("Oils");
        arr.add("Accessories");
        arrAd = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arr);
        sp_cate.setAdapter(arrAd);
    }

    public void Button(View view) {
        Button btn = (Button) view;

        if(btn.getTag().toString().equalsIgnoreCase("back")){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if (btn.getTag().toString().equalsIgnoreCase("add")){
            Bitmap image = ((BitmapDrawable) iv_uploaded_image.getDrawable()).getBitmap();
            price = (String) findViewById(R.id.input_admin_price).toString();
            name = (String) findViewById(R.id.input_admin_product_name).toString();
            category = (String) sp_cate.getSelectedItem().toString();
            String url = "http://192.168.157.1/BarberServer/GET_REQUEST.php";
            Upload up = new Upload(name, price, category, image);
            up.execute(url);

        }

    }

    public void uploadImage (View view){
        Intent gallery_intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery_intent, RESULT_LOAD_IMAGE);
    }
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data!= null){
            Uri selected_image = data.getData();
            iv_uploaded_image.setImageURI(selected_image);
        }
    }

    private class Upload extends AsyncTask<String, Void, String>{

        String name, price, category;
        Bitmap image;

        public Upload (String name, String price, String category, Bitmap image){
            this.name = name;
            this.price = price;
            this.category = category;
            this.image = image;
        }

        @Override
        protected String doInBackground(String... urls) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            String encoded = Base64.getEncoder().encodeToString(bytes);

            ArrayList<String> data_to_send = new ArrayList<>();
            JSONObject jsonObject = new JSONObject();


            URL url;
            HttpURLConnection http = null;
            OutputStream out = null;
            InputStream in = null;

            try {
                jsonObject.put("getcate","item");
                jsonObject.put("name",name);
                jsonObject.put("price",price);
                jsonObject.put("category", category);
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



            return null;
        }

    }

}