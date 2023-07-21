package com.example.step03customadapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    //필드
    List<CountryDto> countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //어뎁터에 연결할 모델 객체 생성
        countries = new ArrayList<>(); //필드 선언하고 객체를 생성해서 넣어줘야함!! 그냥 필드만 선언하면 null이다
        //샘플데이터
        countries.add(new CountryDto(R.drawable.austria,
                "오스트리아", "어쩌구.. 저쩌구.."));
        countries.add(new CountryDto(R.drawable.belgium,
                "벨기에", "어쩌구.. 저쩌구.."));
        countries.add(new CountryDto(R.drawable.brazil,
                "브라질", "어쩌구.. 저쩌구.."));
        countries.add(new CountryDto(R.drawable.france,
                "프랑스", "어쩌구.. 저쩌구.."));
        countries.add(new CountryDto(R.drawable.germany,
                "독일", "어쩌구.. 저쩌구.."));
        countries.add(new CountryDto(R.drawable.greece,
                "그리스", "어쩌구.. 저쩌구.."));
        countries.add(new CountryDto(R.drawable.israel,
                "이스라엘", "어쩌구.. 저쩌구.."));
        countries.add(new CountryDto(R.drawable.italy,
                "이탈리아", "어쩌구.. 저쩌구.."));
        countries.add(new CountryDto(R.drawable.japan,
                "일본", "그지 같은 나라~"));
        countries.add(new CountryDto(R.drawable.korea,
                "대한민국", "어쩌구.. 저쩌구.."));
        countries.add(new CountryDto(R.drawable.poland,
                "폴란드", "어쩌구.. 저쩌구.."));
        countries.add(new CountryDto(R.drawable.spain,
                "스페인", "어쩌구.. 저쩌구.."));
        countries.add(new CountryDto(R.drawable.usa,
                "미국", "어쩌구.. 저쩌구.."));

        //context를 전달하라그러면, 액티비티의 부모 객체중에 context가 있으므로 this를 전달하면 됨!
        //listView에 연결할 어댑터 객체 생성
        BaseAdapter adapter = new CountryAdapter(this, R.layout.listview_cell, countries);

        //listView의 참조값을 얻어와서
        ListView listView = findViewById(R.id.listView);
        //어뎁터 연결하기
        listView.setAdapter(adapter); //CountryAdapter baseAdapter 타입으로 내부에서 인식가고 있기 때문에 여기서 adapter로 받아올 수있따 (다향성...???)

        //cell view를 만든느데 필요한 xml문서를 직접 만들자
        //만들어서 adapter 생성자에 R.layout.listview_cell 전달

        //20230721
        //listView에 아이템 클릭 리스너 등록
        listView.setOnItemClickListener(this);



    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //어떤 Activity 활성화 시키는것은 intent객체 -> 여러개의 Activity중 내가 원하는 액티비티를 활성화 시킬 수 있도록 intent객체를 잘 작성해야함.
        
        //DetailActivity로 이동할 Intent 객체 생성하기
        Intent intent = new Intent(this, DetailActivity.class);

        //click한 cell에 해당하는 countryDto를 전달해줘야 한다.
        CountryDto dto = countries.get(i); //클릭한 셀의 인덱스 i의 정보 전달 받음
        //it could be Serializable dto = countries.get(i);

        //이 선택된 cell에(i) 해당하는 CountryDto를 DetailActivity에 전달
        //Intent 객체에 "dto"라는 키값으로 Serializable type인 CountryDto 객체의 참조값 전달하기
        intent.putExtra("dto", dto);
                //putExtra(key , value)
                //CountryDto를 serializable type으로 바꿔서 넣어주자. -> 객체 직렬화
                //CountryDto class에 serializable interface을 implements 시켜준다.
                // Object , CountryDto, Serializable a = new CountryDto()가 됨. -> 타입이 3가지가 됨


        //startActivity() 메소드 호출하면서 Intent 객체를 전달해서 액티비티 시작 시키기
        startActivity(intent);


    }
}