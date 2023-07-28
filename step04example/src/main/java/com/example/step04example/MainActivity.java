package com.example.step04example;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/*
    문자열을 입력하고 "전송" 버튼을 누르면 Util 클래스를 이용해서
    GET방식으로 http://아이피주소:9000/boot07/android/tweet에 요청하는 예제 //앱 안에서의 로컬호스트는 본인 기기의 주소
    요청 파라미터는 msg라는 파라미터 명으로 입력한 문자열이 전송되도록한다.
    서버가 웅답하는 문자열은 오렌지색 EditText에 출력하기
 */
public class MainActivity extends AppCompatActivity implements Util.RequestListener{
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //activity_main.xml 문서에 정의된 객체의 참조값 얻어오기
        editText = (EditText)findViewById(R.id.editText);
        EditText inputMsg = (EditText)findViewById(R.id.inputMsg);
        Button sendBtn = (Button)findViewById(R.id.sendBtn);
        //버튼에 리스너 등록하기
        sendBtn.setOnClickListener(view -> {
            //입력한 문자열
            String msg = inputMsg.getText().toString();
            //요청 파라미터로 전달하기 위해 "msg"라는 키값으로 Map에 담는다. //요청파라미터가 있따면 맵에 넣어서 전송하면 알아서 파라미터로 들어감
            Map<String, String> map = new HashMap<>();
            map.put("msg", msg);
            //Util을 이용해서 http요청을 한다. //implements Util.RequestListener해줘야함.
            Util.sendGetRequest(0,"http://192.168.0.43:9000/boot07/android/tweet", map, this);

        });

        //두번째 버튼도 리스너 등록하기
        Button sendBtn2 = (Button) findViewById(R.id.sendBtn2);
        sendBtn2.setOnClickListener(view -> {
            //입력한 문자열
            String msg = inputMsg.getText().toString();
            //요청 파라미터로 전달하기 위해 "msg"라는 키값으로 Map에 담는다. //요청파라미터가 있따면 맵에 넣어서 전송하면 알아서 파라미터로 들어감
            Map<String, String> map = new HashMap<>();
            map.put("msg", msg);
            //Util을 이용해서 http요청을 한다. //implements Util.RequestListener해줘야함.
            Util.sendGetRequest(1,"http://192.168.0.43:9000/boot07/android/tweet2", map, this);
        });

        //세번째 버튼도 리스너 등록하기
        Button sendBtn3 = (Button) findViewById(R.id.sendBtn3);
        sendBtn3.setOnClickListener(view -> {
            //입력한 문자열
            String msg = inputMsg.getText().toString();
            //요청 파라미터로 전달하기 위해 "msg"라는 키값으로 Map에 담는다. //요청파라미터가 있따면 맵에 넣어서 전송하면 알아서 파라미터로 들어감
            Map<String, String> map = new HashMap<>();
            map.put("msg", msg);
            //Util을 이용해서 http요청을 한다. //implements Util.RequestListener해줘야함.
            Util.sendGetRequest(2,"http://192.168.0.43:9000/boot07/android/tweet3", map, this);
        });


        Button listBtn = (Button) findViewById(R.id.listBtn);
        listBtn.setOnClickListener(view -> {
            Util.sendGetRequest(3,"http://192.168.0.43:9000/boot07/android/list", null, this);
            //[ {}, {}, {}, {}... ] 형태로 응답될거임
        });

    }

    @Override
    public void onSuccess(int requestId, Map<String, Object> result) {
        if (requestId == 0) {
            //서버가 응답한 문자열 가져와서
            String data = (String) result.get("data");
            // editText에다가 출력하기
            editText.setText(data);
        } else if (requestId == 1) {
            //서버가 응답한 문자열을 editText에다가 출력하기
            String data = (String) result.get("data");
            //data는 json문장열이다.
            //editText.setText(data);

            //{"isSuccess":true} 형식의 json 문자열은 JSONObject 객체를 이용해서 원하는 데이터를 추출할 수 있다.
            //안드로이드 기본 패키지 JSONObject 사용
            try {
                //JSONObject를 생성해서
                JSONObject obj = new JSONObject(data);
                //"isSuccess"라는 키값으로 저장된 true라는 boolean type데이터 얻어내기
                boolean isSuccess = obj.getBoolean("isSuccess");
                //자바스크립트에선 json문자열에 JSON.parse해서 오브젝트로 만들어서 점 찍어서 사용 가능
                //자바에서는 불리언이면 불리언 인트면 인트.. 각각 메소드를 사용해야함.

                editText.setText(Boolean.toString(isSuccess));

            } catch (JSONException e) {
                //data가 json 형식에 어긋나면 예외가 발생한다.
                editText.setText(e.getMessage());
            }

        } else if (requestId == 2) {
            String data=(String)result.get("data");
            //data 는 [] 형식의 json 문자열이다.  [] 형식의 json 문자열은 JSONArray 객체를 활용한다.
            try {
                JSONArray arr=new JSONArray(data);
//                arr.getString(0);
//                arr.getString(1);
//                arr.getString(2);
                //배열 형태니까 반복문을 돌리자
                for(int i = 0; i < arr.length(); i++) {
                    //i번쨰 방에 있는 String type을 얻어내서
                    String tmp = arr.getString(i);
                    //EditText 객체에 출력한다.
                    editText.append(tmp+"\n");
                }
            } catch (JSONException e) {
                //data 가 json 형식에 어긋나면 예외가 발생한다.
                editText.setText(e.getMessage());
            }
        } else if (requestId == 3) {
            // data는 [{}, {}, {}, ...] 형식의 json문자열이다.
            String data = (String) result.get("data");
            // [] 형식의 문자열이기 떄문에 JSONArray 객체를 생성한다.
            try {
                JSONArray arr = new JSONArray(data);
                for(int i = 0; i < arr.length(); i++) {
                    // {"num":1, "writer":"xxx", "title":"xxx", ...}
                    JSONObject obj = arr.getJSONObject(i); //제이슨어레이 안에는 제이슨 오브젝트가 있으니까
                    //글번호
                    int num = obj.getInt("num");
                    //작성자
                    String writer= obj.getString("name");
                    //제목
                    String title = obj.getString("title");
                    //출력
                    editText.append(num + " | " + writer + " | " + title + " \n");
                }
            } catch (JSONException e) {
                editText.setText(e.getMessage());
            }
        }
    }

        @Override
        public void onFail(int requestId, Map<String, Object> result) {
            if(requestId == 0){
                String data=(String)result.get("data");
                editText.setText(data);
            }

    }
}