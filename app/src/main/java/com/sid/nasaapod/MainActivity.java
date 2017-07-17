package com.sid.nasaapod;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.StrictMode;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //final ScrollView scrollView = (ScrollView)findViewById(R.id.scrollView);
        //final int[] subtraction = {0};
        updateImages(/*subtraction[0]*/);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //subtraction[0] +=10;
                updateImages(/*subtraction[0]*/);
                //scrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });
    }

    public void updateImages(){
        ImageView imageView = (ImageView)findViewById(R.id.imageView);//, imageView2 = (ImageView)findViewById(R.id.imageView2), imageView3 = (ImageView)findViewById(R.id.imageView3), imageView4 = (ImageView)findViewById(R.id.imageView4), imageView5 = (ImageView)findViewById(R.id.imageView5), imageView6 = (ImageView)findViewById(R.id.imageView6), imageView7 = (ImageView)findViewById(R.id.imageView7), imageView8 = (ImageView)findViewById(R.id.imageView8), imageView9 = (ImageView)findViewById(R.id.imageView9), imageView10 = (ImageView)findViewById(R.id.imageView10);
        TextView textView = (TextView)findViewById(R.id.textView), textView2 = (TextView)findViewById(R.id.textView2), /*textView3 = (TextView)findViewById(R.id.textView3), textView4 = (TextView)findViewById(R.id.textView4), */textView5 = (TextView)findViewById(R.id.textView5);//, textView6 = (TextView)findViewById(R.id.textView6), textView7 = (TextView)findViewById(R.id.textView7), textView8 = (TextView)findViewById(R.id.textView8), textView9 = (TextView)findViewById(R.id.textView9), textView10 = (TextView)findViewById(R.id.textView10);

        getCard(imageView, textView, textView2, textView5, ""+((int)(Math.random()*21+1996))+"-"+((int)(Math.random()*12+1))+"-"+((int)(Math.random()*28+1)));
        //getCard(imageView2, textView2, ""+((int)(Math.random()*21+1996))+"-"+((int)(Math.random()*12+1))+"-"+((int)(Math.random()*28+1)));
        //getCard(imageView3, textView3, ""+((int)(Math.random()*21+1996))+"-"+((int)(Math.random()*12+1))+"-"+((int)(Math.random()*28+1)));
        //getCard(imageView4, textView4, ""+((int)(Math.random()*21+1996))+"-"+((int)(Math.random()*12+1))+"-"+((int)(Math.random()*28+1)));
        //getCard(imageView5, textView5, ""+((int)(Math.random()*21+1996))+"-"+((int)(Math.random()*12+1))+"-"+((int)(Math.random()*28+1)));
        //getCard(imageView6, textView6, ""+((int)(Math.random()*21+1996))+"-"+((int)(Math.random()*12+1))+"-"+((int)(Math.random()*28+1)));
        //getCard(imageView7, textView7, ""+((int)(Math.random()*21+1996))+"-"+((int)(Math.random()*12+1))+"-"+((int)(Math.random()*28+1)));
        //getCard(imageView8, textView8, ""+((int)(Math.random()*21+1996))+"-"+((int)(Math.random()*12+1))+"-"+((int)(Math.random()*28+1)));
        //getCard(imageView9, textView9, ""+((int)(Math.random()*21+1996))+"-"+((int)(Math.random()*12+1))+"-"+((int)(Math.random()*28+1)));
        //getCard(imageView10, textView10, ""+((int)(Math.random()*21+1996))+"-"+((int)(Math.random()*12+1))+"-"+((int)(Math.random()*28+1)));
    }


    /*public void updateImages(int sub){
        ImageView imageView = (ImageView)findViewById(R.id.imageView), imageView2 = (ImageView)findViewById(R.id.imageView2), imageView3 = (ImageView)findViewById(R.id.imageView3), imageView4 = (ImageView)findViewById(R.id.imageView4), imageView5 = (ImageView)findViewById(R.id.imageView5), imageView6 = (ImageView)findViewById(R.id.imageView6), imageView7 = (ImageView)findViewById(R.id.imageView7), imageView8 = (ImageView)findViewById(R.id.imageView8), imageView9 = (ImageView)findViewById(R.id.imageView9), imageView10 = (ImageView)findViewById(R.id.imageView10);
        TextView textView = (TextView)findViewById(R.id.textView), textView2 = (TextView)findViewById(R.id.textView2), textView3 = (TextView)findViewById(R.id.textView3), textView4 = (TextView)findViewById(R.id.textView4), textView5 = (TextView)findViewById(R.id.textView5), textView6 = (TextView)findViewById(R.id.textView6), textView7 = (TextView)findViewById(R.id.textView7), textView8 = (TextView)findViewById(R.id.textView8), textView9 = (TextView)findViewById(R.id.textView9), textView10 = (TextView)findViewById(R.id.textView10);

        Calendar c = Calendar.getInstance();
        Log.e("Current time => ", ""+c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime());
        Log.e("date=", formattedDate);

        getCard(imageView, textView, ""+(Integer.parseInt(formattedDate.substring(0,4))-(1+sub))+formattedDate.substring(4));
        getCard(imageView2, textView2, ""+(Integer.parseInt(formattedDate.substring(0,4))-(2+sub))+formattedDate.substring(4));
        getCard(imageView3, textView3, ""+(Integer.parseInt(formattedDate.substring(0,4))-(3+sub))+formattedDate.substring(4));
        getCard(imageView4, textView4, ""+(Integer.parseInt(formattedDate.substring(0,4))-(4+sub))+formattedDate.substring(4));
        getCard(imageView5, textView5, ""+(Integer.parseInt(formattedDate.substring(0,4))-(5+sub))+formattedDate.substring(4));
        getCard(imageView6, textView6, ""+(Integer.parseInt(formattedDate.substring(0,4))-(6+sub))+formattedDate.substring(4));
        getCard(imageView7, textView7, ""+(Integer.parseInt(formattedDate.substring(0,4))-(7+sub))+formattedDate.substring(4));
        getCard(imageView8, textView8, ""+(Integer.parseInt(formattedDate.substring(0,4))-(8+sub))+formattedDate.substring(4));
        getCard(imageView9, textView9, ""+(Integer.parseInt(formattedDate.substring(0,4))-(9+sub))+formattedDate.substring(4));
        getCard(imageView10, textView10, ""+(Integer.parseInt(formattedDate.substring(0,4))-(10+sub))+formattedDate.substring(4));
    }*/

    public void getCard(ImageView imageView, TextView textView, TextView textView2, TextView textView5, String date){
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = jsonParser.getJSONFromUrl("https://api.nasa.gov/planetary/apod?api_key=uZvWdb4HL99nymXFPQlFfUBVZSuumS0MvqlVUHXs&date="+date, null);
        Log.e("DATE", date);
        String desc="No description", url = "https://fabiusmaximus.files.wordpress.com/2012/12/20121230-no-error.png", text="Title not found", author="unknown", captureDate="Date not found";
        try {
            url = jsonObject.get("url").toString();
            text = jsonObject.get("title").toString();
            captureDate = jsonObject.get("date").toString();
            author = jsonObject.get("copyright").toString();
            desc = jsonObject.get("explanation").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("url=",url);
        imageView.setImageBitmap(getBitmapFromURL(url));
        textView.setText(text);
        textView5.setText("    "+desc);
        textView5.setMovementMethod(new ScrollingMovementMethod());
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
