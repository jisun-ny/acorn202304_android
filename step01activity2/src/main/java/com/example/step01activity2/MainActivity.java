package com.example.step01activity2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

//    [패턴1 ] - 액티비티를 리스너로 만들어버리기
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //이동 버튼의 참조값 얻어오기
        Button moveBtn = findViewById(R.id.moveBtn);
        //버튼을 눌렀을 때 동작하기 위한 리스너 등록
        moveBtn.setOnClickListener(this);
        //인터페이스 타입으로 implement필요
        // -> implement받았기 때문에 this(MainActivity)는 OnClickListener이기도 한 것
    }

    //버튼을 눌렀을 때 아래의 메소드가 실행된다.
    //View.OnClickListener 이 인터페이스에는 오버라이드할 추상메소드가 하나밖에 없다
    // -> functional interface로 쓸 수 있다.
    //View.OnClickListener listener = new View.OnClickListener()
    //오류인 이유는 자바에서 메소드는 단독으로 존재할 수 없기 떄문에.

    //    [패턴2 ]
//    리스너 객체를 직접 만들어서 전달
//    View.OnClickListener listener = new View.OnClickListener() {//여기서 바로 객체 생성해서 오버라이드해서 사용
//        @Override
//        public void onClick(View view) {
//
//        }
//    };
//    moveBtn.setOnClickListener(listener);


    //    [패턴3 ]
//    //메소드가 하나기 떄문에 위의 식을 줄여서 이렇게 작성 가능
//    moveBtn.setOnClickListener(view -> {
//        //SubActivity를 활성화 시키겠다는 의도(intent) 객체 생성하기
//        Intent intent = new Intent(this, SubActivity.class);
//        //startActivity() 메소드를 호출하면서 Intent 객체를 전달하기
//        startActivity(intent);
//    });


    @Override
    public void onClick(View view) {
        //SubActivity를 활성화 시키겠다는 의도(intent) 객체 생성하기
        Intent intent = new Intent(this, SubActivity.class); //this는 MainActivity의 참조값. MainActivity는 부모를 쭉 따라 올라가보면  Context 타입이 있따... 즉 액티비티는 컨텍트 타입이니까 액티비티 타입을 넣어주면 됨...?
        //startActivity() 메소드를 호출하면서 Intent 객체를 전달하기
        startActivity(intent);
    }

    /*
    안드로이드 4대 component
    1. Activity 2. Service 3.BroadcastReceiver 4. Content Provider

    Intent 객체를 사용해서 활성화 시킬 수 있다
    Intent 생성자 (context, class type)
    Intent 객체를 운영체제가 받아서
     */
}