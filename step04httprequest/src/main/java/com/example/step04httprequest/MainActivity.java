package com.example.step04httprequest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         //onCreate() : <1> 액티비티가 활성화 되는 시점에 main Thread(UI Thread)가 여기를 실행
        editText = findViewById(R.id.editText);
        EditText inputUrl = findViewById(R.id.inputUrl);
        //요청 버튼을 클릭했을 때 동작할 준비
        Button requestBtn = findViewById(R.id.requestBtn);
        requestBtn.setOnClickListener(view -> {
            // <2>요청 버튼을 누를 때 마다 main thread가 여기를 실행한다.
            // 중요한 main thread가 hhtp요청(불안정 작업, 시간이 오래걸리는 작업)을 하는데 묶여있으면 앱이 반응을 안하기 때문에 안된다.
            // UI를 변경하는 작업은 main thread에서만 건들 수 있다.
            //엥 그럼 어케?
            // 하나의 스레드 새로 만들어서 작업한 다음에 그 결과값을 메인 스레드에 전달해서 메인 스레드에서 UI를 업데이트해줘야함.

            //1. 입력한 url 주소를 읽어와서
                String url = inputUrl.getText().toString();

            //2. http 요청을 하고
            //RequestTask 내부 클래스를 만들어놓고 execute하면 밑에 doInBackground()가 실행된다.
                new RequestTask().execute(url); //이 메소드는 호출 즉시 리턴된다.
            //3. 정상적으로 응답이 되면 응답된 문자열을 EditText 출력하기
            //onPostExecute()
        });
    }

    //비동기 task 객체를 생성할 클래스
    class RequestTask extends AsyncTask<String, Void, String>{// 세번째 매개변수는 결과 타입.

        @Override
        protected String doInBackground(String... strings) { //매개변수의 타입이 String... 타입인 이유는 위에 제너릭 첫번째가 String 이기 떄문에 // ...은 배열
            //백그라운드(새로운 스레드의 run() 메소드) 안에서 작업할 내용을 이 메소드에서 작업(시간이 많이 걸리는 작업)

            //ㅡㅡㅡㅡㅡㅡ

            //문자열을 누적시킬 객체
            StringBuilder builder=new StringBuilder();

            //strings 의 0 번방에 요청 url 이 들어 있다.
            try {
                //요청 url 을 생성자의 인자로 전달해서 URL 객체를 생성한다. //배열의 0번방에 위에서 전달한 url객체가 들어있다.
                URL url = new URL(strings[0]);
                //URLConnection 객체를 원래 type (자식 type) 으로 casting 해서 받는다.
                HttpURLConnection conn=(HttpURLConnection) url.openConnection();
                //정상적으로 연결이 되었다면
                if(conn != null){
                    conn.setConnectTimeout(20000); //응답을 기다리는 최대 대기 시간
                    conn.setRequestMethod("GET");// 요청 메소드 설정 (Default 는 GET)
                    conn.setUseCaches(false);//캐쉬 사용 여부
                    //응답 코드를 읽어온다.
                    int responseCode=conn.getResponseCode();
                    if(responseCode == HttpURLConnection.HTTP_OK){ //정상 응답이라면 //HTTP_OK --> 200번
                        //문자열을 읽어들일수 있는 객체의 참조값 얻어오기
                        BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        //반복문 돌면서
                        while(true){
                            //문자열을 한줄씩 읽어 들인다.
                            String line=br.readLine();
                            if(line==null)break;
                            //StringBuilder 객체에 읽어들인 문자열을 누적 시킨다.
                            builder.append(line);
                        }
                    }
                }
            }catch (Exception e){
                Log.e("RequestTask 클래스", e.getMessage());
            }
            //StringBuilder 객체에 누적된 문자열을 String type 으로 한번에 얻어내서 리턴해 준다.
            return builder.toString();

            //ㅡㅡㅡㅡㅡㅡ




//            //몇초의 시간을 소비해서 얻어낸 결과라고 가정
//            String result = "<div>hello</div>";
//
//            //여기는 UI스레드가 아니기 떄문에 UI업데이트를 할 수 없다.
//            editText.setText(result);
//
//            //결과값을 리턴해준다.
//            return result;
        }
    //doInBackground() 메소드에서 리턴된 문자열이 이 메소드가 호출되면서 메소드의 인자로 전달된다.
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // 여기는 UI 스레드 상에서 동작하는 메소드이다.
            editText.setText(s);
        }
    }
}