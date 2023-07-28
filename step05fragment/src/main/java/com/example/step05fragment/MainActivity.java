package com.example.step05fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.step05fragment.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
    //MyFragment에서 전달하는 메세지를 받고싶다면 인터페이스를 구현하면 된다.(could be an option)
        implements MyFragment.MyFragmentListener{
// binding사용해서 예제를 바꿔보자
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //activity_main.xml 문서에 <fragment> 요소가 있을때, 해당 Fragment의 참조값을 얻어내기
        //setContentView(R.layout.activity_main);
// view binding 객체를 얻어내서 필드에 저장
        binding = ActivityMainBinding.inflate(getLayoutInflater());
//화면 구성하기
        setContentView(binding.getRoot());


        //FragmentManager 객체 얻어오기
        FragmentManager fm = getSupportFragmentManager();
        //FragmentManger 객체의 메소드를 를 이용해서  프래그먼트객체의 참조값을 얻어낼 수 있다.

        //Fragment frag1 = fm.findFragmentById(R.id.fragment1); --> return Fragment type
        //frag1.rest(); --> error! MyFragment타입이 아니라 부모타입(Fragment)으로 리턴되기떄문에

        MyFragment frag1 = (MyFragment)fm.findFragmentById(R.id.fragment1);
        MyFragment frag2 = (MyFragment)fm.findFragmentById(R.id.fragment2);


        //리셋 버튼에 리스너 등록하기
//        Button resetBtn = findViewById(R.id.resetBtn);
//        resetBtn.setOnClickListener(view -> {
//        frag1.reset();
//        frag2.reset();
//
//    });
        binding.resetBtn.setOnClickListener(view -> {
            frag1.reset();
            frag2.reset();
        });

//        Button moveBtn = findViewById(R.id.moveBtn);
//        moveBtn.setOnClickListener(view -> {

        binding.moveBtn.setOnClickListener(view -> {
           //SubActivity로 이동하기
           startActivity(new Intent(this, SubActivity.class));
        });

    }

    //MyFragment 객체에서 특정 시점에 호출하는 메소드
    @Override
    public void sendMsg(String msg) {
        Toast.makeText(this, "MyFragment: " + msg, Toast.LENGTH_SHORT).show();
    }

//    public void sendMsg(String msg) { //MyFragment가 전달한 msg를 인자로 받아서 토스트 띄우는 메소드
//        Toast.makeText(this, "프레그먼트가 전달한 메세지: " + msg, Toast.LENGTH_SHORT).show();
//    }
}