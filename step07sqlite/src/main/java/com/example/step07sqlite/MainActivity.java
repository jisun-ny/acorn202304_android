package com.example.step07sqlite;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.step07sqlite.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //view binding 설정
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        /*
            DBHelper 객체를 생성해서 참조값을 필드에 저장하기
            name은 DB의 이름을 마음대로 정해서 전달하면 된다.
         */
        dbHelper = new DBHelper(this, "MyDB.sqlite", null, 1);

    }
}