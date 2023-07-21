package com.example.step01activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //카운트를 셀 필드
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // activity_main.xml 문서를 전개해서 화면 구성하기
        setContentView(R.layout.activity_main);
        //this.setContentView 부모로부터 물려받은 메소드 (this가 생략된 것)
        //레이아웃 구성할 때 호출하는 메소드

    }

    //리셋 버튼을 눌렀을 때 실행되는 메소드
    public void resetClicked(View v){
        count = 0;
        /*
            현재 활성화 되어 있는 액티비티가 구성한 화면에서
            textView라는 아이디를 가지고 있는 UI의 참조값을 얻어와서
            TextView type의 a라는 지역 변수에 담기
         */
        TextView a = findViewById((R.id.textView));
        a.setText(Integer.toString(count));
    }

    //버튼 클릭했을 때 실행되는 메소드
    public void clicked(View v) {
        //필드의 값을 1 증가 시키기
        count++;
        //필드의 값을 TextView에 출력하기
        // res/layout/activity_main.xml 문서를 전개해서 레이아웃을 구성했는데
        // 거기에 TextView의 참조값을 얻어와야한다.
        TextView a = findViewById(R.id.textView);
        //textView라는 아이디가 가지고 있는 숫자를 전달하면  textView의 참조값이 리턴

        //findViewById도 부모로부터
        //아이디를 이용해서 뷰의 참조값을 얻어내는 메소드 - UI를 구성하는 모든 component들은 view를 상속받아서 만든다.
        //view type은 모든 UI의 부모타입
        a.setText(Integer.toString(count)); //숫자를 텍스트로 만들어서 넣어줘야함
    }



   
}