package com.example.step03customadapter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //DetailActivity가 활성화 되기 위해서 전달된 Intent 객체의 참조값 얻어내기
        Intent intent = getIntent(); //메인엑티비티에서 생성했던 객체의 참조값이 전달된다. //dto라는 키값으로 serializable 타입을 담았던 것도 그대로 전달된다.
        //"dto"라는 키값으로 담긴 Serializable type 의 참조값을 얻어내서 CountryDto type으로 casting // 우리가 메인 엑티비티에서 put한 데이터를(어떤 데이터 타입으로 넣었는지 알아야함) get할 수 있따.
        // --> 아래에서 컨트리티디오타입이 필요하니까 캐스팅해줘야한다
        CountryDto dto = (CountryDto) intent.getSerializableExtra("dto");


        //선택한 cell에 해당하는 CountryDto를 얻어낼만 있다면

        //아래의 ImageView와 TextView에 해당 정보를 출력할 수 있다.
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(dto.getResId());

        TextView textView = findViewById(R.id.textView);
        textView.setText(dto.getContent());

        //확인 버튼을 눌렀을 때 액티비티 종료 시키기
        Button confirmBtn = findViewById(R.id.confirmBtn);
        confirmBtn.setOnClickListener(view ->
                //finish()메소드를 호출하면 액티비티가 종료된다.
                finish());
    }
}