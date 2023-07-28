package com.example.test2_20230725;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Util.RequestListener {

    //필드
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

        Button fortuneBtn = findViewById(R.id.fortuneBtn);
        fortuneBtn.setOnClickListener(view -> {
            //Util클래스를 이용해서 spring boot서버에 요청하고 결과 받아오기
            Util.sendGetRequest(10,"http://192.168.0.43:9000/boot07/android/fortue", null, this );

        });

        EditText inputName = findViewById(R.id.inputName);
        Button addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(view -> {
            String name = inputName.getText().toString();
            //요청파라미터로 전달하기 위해서 Map에 담는다
            Map<String, String> map = new HashMap<>();
            map.put("name", name);
            Util.sendPostRequest(20, "http://192.168.0.43:9000/boot07/android/insert", map, this );
        });
    }

    @Override
    public void onSuccess(int requestId, Map<String, Object> result) {
        //spring boot 서버로 부터 응답된 json 문자열 읽어오기
        String data = (String) result.get("data");
        if(requestId == 10) {
            try {
                JSONObject obj=new JSONObject(data);
                //JSONObject 로 부터 오늘의 운세 얻어내기
                String fortuneToday = obj.getString("fortuneToday") ;
                editText.setText(fortuneToday);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            //만일 입력한 이름을 전송한 요청에 대한 응답이라면
        }else if(requestId == 20){
            try {
                JSONArray arr=new JSONArray(data);
                for(int i=0; i<arr.length(); i++){
                    // JSONArray 객체로 부터 이름 하나 얻어내기
                    String tmp = arr.getString(i);
                    editText.append(tmp+"\n");
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void onFail(int requestId, Map<String, Object> result) {

    }
}

