package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class About_Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about__game);

        Button ok = (Button)findViewById(R.id.button4);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMainScreen();
            }
        });
    }

    public void loadMainScreen(){
        Intent in = new Intent(this,MainActivity.class);
        startActivity(in);
    }
}