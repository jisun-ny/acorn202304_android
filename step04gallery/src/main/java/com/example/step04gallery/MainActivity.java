package com.example.step04gallery;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Util.RequestListener{

    //필드
    List<GalleryDto> list;
    GalleryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //listView의 참조값 얻어오기
        ListView listView = findViewById(R.id.listView);
        //모델 객체 생성
        list = new ArrayList<>();
        //어뎁터 생성
        adapter = new GalleryAdapter(this, R.layout.listview_cell, list); //oncreate되는 시점에 아이템은 없지만 일단 list를 연결해놓고 dto로부터 리스트를 제이슨 형식으로 받고 어뎁터한테 알려줌.
        //listView에 어뎁터 연결하기
        listView.setAdapter(adapter);

        //listView에 아이템 클릭 리스너
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            //클릭한 셀의 인덱스에 해당하는 GalleryDto
            GalleryDto dto = list.get(i);
            //엑티비티를 이동할 Intent객체 생성
            Intent intent = new Intent(this, DetailActivity.class);
            //Intent객체에 Serializable type담기
            intent.putExtra("dto", dto); //dto는 Serializable type이기도 하니까 intent에 담을 수 있음
            //엑티비티 이동하기
            startActivity(intent);
        });


        //Util 클래스를 이용해서 gallery목록 요청하기
        Util.sendGetRequest(0, AppConstants.BASE_URL+"/android/gallery/list", null, this);
        //결과를 받을 객체를 4번째 인자로 전달해줘야함.? -> implements Util.RequestListener
    }

    @Override
    public void onSuccess(int requestId, Map<String, Object> result) {
        if(requestId == 0){
            //Map 에는 "data" 라는 키값으로  [{},{},{}] 형식의 json 문자열이 들어 있다.
            String json=(String)result.get("data");
            try {
                JSONArray arr=new JSONArray(json);
                //반복문 돌면서 JSONObject 객체를 하나씩 얻어낸다
                for(int i=0; i<arr.length(); i++){
                    JSONObject tmp=arr.getJSONObject(i);
                    //num, writer, caption, imagePath가 들어있음.
                    //JSONObject 안에 있는 정보를 추출해서 GalleryDto 에 담는다.
                    GalleryDto dto=new GalleryDto();
                    dto.setNum(tmp.getInt("num"));
                    dto.setWriter(tmp.getString("writer"));
                    dto.setCaption(tmp.getString("caption"));
                    dto.setRegdate(tmp.getString("regdate"));
                    String url= AppConstants.BASE_URL + "/gallery/images/" +tmp.getString("imagePath");
                    dto.setImagePath(url); //adapter가 glide에 전달해준다.
                    //GalleryDto 를 List 에 누적 시킨다.
                    list.add(dto);
                }
                //모델이 업데이트 되었다고 아답타에 알려서 ListView 가 업데이트 되도록한다.
                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }



    @Override
    public void onFail(int requestId, Map<String, Object> result) {

    }
}