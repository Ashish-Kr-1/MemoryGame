package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    private Button[] Btn = new Button[9];
    private Button Back,Next,EndBack;
    private TextView Lvl, Final;
    int i; int count = 0, score = 1;
    int TimeGap = 1500, limit = 2;
    int[] order = new int[9];
    int[] realArr = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    int[] arr = realArr;
    final boolean[] allowClick = {false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        setUpUI();
        Back.setText("Memorise !");
        int c = 0; int ran;

        TimeGap = 1000;

        while(c<limit) {
            ran = (int) Math.round(Math.random() * (arr.length-1));
            order[c] = arr[ran];
            arr = remove(arr,arr[ran]);
            c++;
        }

        showOrder();

        Handler del = new Handler();

        c = 0;

        for(i=0;i<Btn.length;i++){
            Btn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(allowClick[0]) {
                        int num = returnNum(v.getId());
                        if (order[count] == num) {
                            Btn[num].setBackgroundColor(Color.GREEN);
                            if (count < (limit - 1))
                                Back.setText((limit - count - 1) + " more");
                            else {
                                Back.setBackgroundColor(Color.GREEN);
                                Back.setText("Great!");
                                allowClick[0] = false;
                                Next.setVisibility(View.VISIBLE);
                            }
                            count++;
                        } else {
                            Btn[num].setBackgroundColor(Color.RED);
                            Back.setBackgroundColor(Color.RED);
                            Back.setText("You lost");
                            EndBack.setVisibility(View.VISIBLE);
                            Final.setText("Final score : "+(score-1));
                            Final.setVisibility(View.VISIBLE);
                            allowClick[0] = false;
                        }
                    }
                }
            });
        }

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score++;
                Lvl.setText(score+"");
                Next.setVisibility(View.INVISIBLE);
                Back.setBackgroundColor(0xffffaf00);

                if(score>17){
                    TimeGap = 300;
                    limit = 9;
                }
                else if(score>14){
                    limit = 8;
                }
                else if(score>11){
                    TimeGap = 400;
                    limit = 7;
                }
                else if(score>8){
                    TimeGap = 450;
                }
                else if(score>7){
                    limit = 6;
                }
                else if(score>4){
                    TimeGap = 500;
                    limit = 5;
                }
                else if(score>2) {
                    TimeGap = 600;
                    limit = 4;
                }
                else if(score>1) {
                    limit = 3;
                    TimeGap = 600;
                }

                count = 0;
                setOrder();
                for(i=0;i<Btn.length;i++)
                    Btn[i].setVisibility(View.INVISIBLE);
                showOrder();
            }
        });

        EndBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity1();
            }
        });
    }

    public int[] remove(int[] ar, int n){
        int c;
        int[] arrdup = new int[ar.length - 1];
        for(i=0,c=0;i<ar.length;i++,c++){
            if(ar[i]!=n)
                arrdup[c]=ar[i];
            else
                c--;
        }
        return arrdup;
    }

    public void setUpUI() {
        Btn[0] = (Button)findViewById(R.id.imageBtn1);
        Btn[1] = (Button)findViewById(R.id.imageBtn2);
        Btn[2] = (Button)findViewById(R.id.imageBtn3);
        Btn[3] = (Button)findViewById(R.id.imageBtn4);
        Btn[4] = (Button)findViewById(R.id.imageBtn5);
        Btn[5] = (Button)findViewById(R.id.imageBtn6);
        Btn[6] = (Button)findViewById(R.id.imageBtn7);
        Btn[7] = (Button)findViewById(R.id.imageBtn8);
        Btn[8] = (Button)findViewById(R.id.imageBtn9);

        Back = (Button)findViewById(R.id.button3);
        Next = (Button)findViewById(R.id.btnNext);
        Final = (TextView)findViewById(R.id.Final);
        EndBack = (Button)findViewById(R.id.btnBack);
        Lvl = (TextView)findViewById(R.id.lvlNo);
    }

    public void Delay(int n) {
        try{
            Thread.sleep(n*10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int returnNum(int id){
        int n=-1;
        for(i=0;i<Btn.length;i++){
            if(id==Btn[i].getId())
                n = i;
        }
        return n;
    }

    public void setOrder() {
        arr = realArr;
        int c = 0; int ran;

        while(c<limit) {
            ran = (int) Math.round(Math.random() * (arr.length-1));
            order[c] = arr[ran];
            arr = remove(arr,arr[ran]);
            c++;
        }
    }

    public void openActivity1() {
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
    }

    public void showOrder(){
        Handler delay = new Handler();

        Back.setText("Memorise");
        for(int k=0;k<limit;k++){
            int finalK = k;
            delay.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Btn[order[finalK]].setVisibility(View.VISIBLE);
                    Btn[order[finalK]].setBackgroundColor(Color.BLUE);
                }
            },TimeGap*(k+1));

            delay.postDelayed(new Runnable() {
                @Override
                public void run() {
                    for(i=0;i<limit;i++) {
                        Btn[order[i]].setVisibility(View.INVISIBLE);
                        Btn[order[i]].setBackgroundColor(Color.WHITE);
                    }
                    Back.setText("Prepare");
                }
            },TimeGap * limit + 500);

            delay.postDelayed(new Runnable() {
                @Override
                public void run() {
                    for(i=0;i<Btn.length;i++) {
                        Btn[i].setBackgroundColor(Color.WHITE);
                        Btn[i].setVisibility(View.VISIBLE);
                    }
                    Back.setText("Find !");
                    allowClick[0] = true;
                }
            },TimeGap * limit + 500);
        }
    }
}