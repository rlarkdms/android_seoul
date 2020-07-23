package com.example.crawling2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class firstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_first);

        @SuppressLint("WrongViewCast")
        Button page1=(Button)findViewById(R.id.btn1);

        Button page2=(Button)findViewById(R.id.btn2);

        Button page3=(Button)findViewById(R.id.btn3);

        Button page4=(Button)findViewById(R.id.btn4);


        page1.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {

            Intent myintent =new Intent(firstActivity.this,MainActivity.class);

            startActivity(myintent);
            finish();

            }

        });

//
//        page2.setOnClickListener(new View.OnClickListener(){
//
//
//            @Override
//            public void onClick(View v) {
//
//                Intent myintent =new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.naver.com"));
//
//                startActivity(myintent);
//                finish();
//
//            }
//
//        });
    }


}
