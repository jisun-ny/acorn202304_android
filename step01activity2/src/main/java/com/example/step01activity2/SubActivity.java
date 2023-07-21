package com.example.step01activity2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class SubActivity extends AppCompatActivity {

    //액티비티가 최초 실행될 때 실행되는 함수 ( 준비작업 )
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        //디버깅 로그 출력하기  Log.d(, ": ");
        Log.d("SubActivity", "onCreate() 호출됨");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("SubActivity", "onStart() 호출됨");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("SubActivity", "onResume() 호출됨");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("SubActivity", "onRestart() 호출됨");
    }

    @Override
    protected void onPause() {
        Log.d("SubActivity", "onPause() 호출됨");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("SubActivity", "onStop() 호출됨");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("SubActivity", "onDestroy() 호출됨");
    }
    
    
}