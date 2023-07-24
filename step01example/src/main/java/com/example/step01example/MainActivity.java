package com.example.step01example;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //[방법 1]
        //전송 버튼의 참조값 얻어오기
        Button sendBtn = findViewById(R.id.sendBtn);
        //버튼을 눌렀을 때 동작하기 위한 리스너 등록
        sendBtn.setOnClickListener(this);



        //[방법2] -> MainActivity를 implements 할 필요 없다.pp
        Button sendBtn2 = findViewById(R.id.sendBtn2);
        sendBtn2.setOnClickListener(view -> {
            // 1. EditText에 입력한 문자열을 읽어와서
            EditText editText = findViewById(R.id.editText);
            String msg = editText.getText().toString(); //getText() 하면 Editable type이 리턴된다. 따라서 toString()까지 해줘야함.

            // 2. TextView에 출력하기
            TextView textView = findViewById(R.id.textView);
            textView.setText(msg); // CharSequence type은 String type 넣어주면 된다.
        });

    }

    @Override
    public void onClick(View view) {
        // 1. EditText에 입력한 문자열을 읽어와서
        EditText editText = findViewById(R.id.editText);
        String msg = editText.getText().toString(); //getText() 하면 Editable type이 리턴된다. 따라서 toString()까지 해줘야함.

        // 2. TextView에 출력하기
        TextView textView = findViewById(R.id.textView);
        textView.setText(msg); // CharSequence type은 String type 넣어주면 된다.
    }
}