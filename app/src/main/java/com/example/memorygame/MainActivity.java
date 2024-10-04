
package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = (Button)findViewById(R.id.button2);
        Button Game = (Button)findViewById(R.id.about);
        Button Deve = (Button)findViewById(R.id.deve);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        Game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAboutGame();
            }
        });

        Deve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDeveInfo();
            }
        });
    }

    public void openActivity2() {
        Intent in = new Intent(this, Activity2.class);
        startActivity(in);
    }

    public void openAboutGame(){
        Intent in = new Intent(this, About_Game.class);
        startActivity(in);
    }

    public void openDeveInfo(){
        Intent in = new Intent(this, developerInfo.class);
        startActivity(in);
    }
}