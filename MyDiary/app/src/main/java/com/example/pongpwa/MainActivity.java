package com.example.pongpwa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_writeDiary;
    Button btn_diaryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_writeDiary = (Button)findViewById(R.id.btn_WriteDiary);
        btn_writeDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WriteDiary.class);
                startActivity(intent);
            }
        });
        btn_diaryList = (Button)findViewById(R.id.btn_DiaryList);
        btn_diaryList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DiaryList.class);
                startActivity(intent);
            }
        });
    }
}