package com.sid.nasaapod;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public String downloadURL="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        updateImages();
        Button button = (Button)findViewById(R.id.button), download = (Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                updateImages();
            }
        });
        download.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(downloadURL));
                startActivity(browserIntent);
            }
        });
    }

    public void updateImages(){
        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        TextView textView = (TextView)findViewById(R.id.textView), textView2 = (TextView)findViewById(R.id.textView2), textView5 = (TextView)findViewById(R.id.textView5);
        getCard(imageView, textView, textView2, textView5, ""+((int)(Math.random()*21+1996))+"-"+((int)(Math.random()*12+1))+"-"+((int)(Math.random()*28+1)));
    }

    public void getCard(ImageView imageView, TextView textView, TextView textView2, TextView textView5, String date){
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = jsonParser.getJSONFromUrl("https://api.nasa.gov/planetary/apod?api_key=uZvWdb4HL99nymXFPQlFfUBVZSuumS0MvqlVUHXs&date="+date, null);
        Log.e("DATE", date);
        String desc="No description.", url = "https://fabiusmaximus.files.wordpress.com/2012/12/20121230-no-error.png", text="Title not found", author="unknown", captureDate="Date not found";
        try {
            desc = jsonObject.get("explanation").toString();
            url = jsonObject.get("url").toString();
            text = jsonObject.get("title").toString();
            captureDate = jsonObject.get("date").toString();
            downloadURL = url;
            downloadURL = jsonObject.get("hdurl").toString();
            author = jsonObject.get("copyright").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("url=",url);
        imageView.setImageBitmap(getBitmapFromURL(url));
        textView.setText(text);
        textView5.setText("    "+desc);
        textView5.setMovementMethod(new ScrollingMovementMethod());
        textView5.scrollTo(0,0);
        textView2.setText("Published by "+author+" on " + captureDate);
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src",src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }
}
