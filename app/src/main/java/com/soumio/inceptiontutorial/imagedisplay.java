package com.soumio.inceptiontutorial;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class imagedisplay extends AppCompatActivity {
    String[][] items = {

            {"typewriter keyboard","12 th Sept"},


    };

        private TextView itemDisplay,test;
        private Button opencamera, nextItem ;
        private String result1, result2,result3;

        private Integer count=1;

        private View sucessnotice;
    final int min = 0;
    final int max = 6;
    final int random = new Random().nextInt((max - min) + 1) + min;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagedisplay);
        Bundle extras = getIntent().getExtras();

        final SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = prefs.edit();

       String object = prefs.getString("search","");
        count= prefs.getInt("count",0);


        if (extras != null) {
            result1=extras.getString("result1");
            result2=extras.getString("result2");
            result3=extras.getString("result3");

        }



        test=findViewById(R.id.test);
        test.setText(""+count);
        itemDisplay = findViewById(R.id.require);
        sucessnotice= findViewById(R.id.successnotice);
        opencamera = findViewById(R.id.camera_open);
        nextItem= findViewById(R.id.nextItem);
        nextItem.setVisibility(View.INVISIBLE);
        if(object.equals("")) {
            String found = items[0][0];
            itemDisplay.setText(found);
            editor.putString("search", found);
            editor.putInt("count",count);
            editor.commit();


            opencamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(imagedisplay.this, ChooseModel.class);
                    startActivity(intent);
                }
            });

        }
        else {
            if (object.equals(result1) || object.equals(result2) || object.equals(result3)) {
                sucessnotice.setBackgroundResource(R.color.colorGreen);
                itemDisplay.setText(("alarm close success"));
                opencamera.setText("close");
                editor.clear();
                editor.commit();



                opencamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                });
            } else {
                sucessnotice.setBackgroundResource(R.color.colorRed);
                itemDisplay.setText(("Please try again"));
                opencamera.setText("Try again");
                count=count+1;
                editor.putInt("count",count);
                editor.commit();
                opencamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(imagedisplay.this, ChooseModel.class);
                        startActivity(intent);
                    }
                });


                if(count>=3)
                {
                    nextItem.setVisibility(View.VISIBLE);


                }




            }
        }








    }



}
