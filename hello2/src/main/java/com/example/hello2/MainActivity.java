package com.example.hello2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
/*
    이 MainActivity는 hello2 앱이 launch(실행) 될 때 최초로 사용자를 대면하는 액티비티이다.
    대체로 액티비티 하나는 화면 하나이다.


 */
public class MainActivity extends AppCompatActivity {
    //액티비티가 활성화 될 때 최초 호출되는 메소드
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // res/layout/activity_main.xml 문서를 전개(해석)해서 화면 구성하기
        //이 액티비티가 여러 레이아웃 중 하필 res/layout/activity_main.xml을 사용하는 이유, 연결고리
        setContentView(R.layout.activity_main);

    }

    //버튼을 클릭했을 때 호출되는 메소드 (View객체의 참조값이 매개 변수에 전달된다. 반드시 View type을 전달 받아야함.)
    public void clicked (View v) {
        Toast.makeText(this, "버튼을 눌렀네요?", Toast.LENGTH_SHORT).show();
    }
    
    public void deleteClicked(View v) {
        Toast.makeText(this, "삭제합니다", Toast.LENGTH_SHORT).show();
    }
}