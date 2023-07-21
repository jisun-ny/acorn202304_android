package com.example.step02listview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    //필드선언
    List<String> names;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //listView의 참조값
        ListView listView = findViewById(R.id.listView);

        //ListView에 출력할 SampleData -나중엔 DB에서 데이터 가져오기
        names = new ArrayList<>(); //필드에 선언하면 null 이 코드를 안넣어주면 add를 할 수 없음.
        names.add("김구라");
        names.add("해골");
        names.add("원숭이");
        for (int i = 0; i < 100; i++) {
            names.add("아무개" + i);
        }


        //ListView에 연결할 어뎁터 객체
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                names
        );

        //어뎁터를 ListView에 연결하기
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(this);

        listView.setOnItemLongClickListener(this);


        //버튼에 리스너 등록
        Button addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener( view -> {
            //1. EditText에 입력한 문자열을 읽어와서
            EditText inputName = findViewById(R.id.inputName);
            String name = inputName.getText().toString();
            //2. names(모델)에 추가하고
            names.add(name);
            //3. 어뎁터에 names(모델)이 변경되었다고 알려준다.
            adapter.notifyDataSetChanged();
            //4.마지막 위치까지 부드럽게 스크롤 될 수 있도록해준다.
            int position = adapter.getCount(); //전체 아이템의 갯수
            listView.smoothScrollToPosition(position);
        });
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d("MainActivity", "i:" + i);
        //클릭한 Item에 출력된 이름
        String clickedName = names.get(i);
        //토스트(가벼운 메세지) 출력
        Toast.makeText(this, clickedName, Toast.LENGTH_SHORT).show();


    }

    //listView의 cell을 오랫동안 클릭하면 호출되는 메소드
    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
          //[코딩 순서 2]
          //[ 방법 1] - 객체 생성 후 implement
          DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialogInterface, int result) {

                    if( result == DialogInterface.BUTTON_POSITIVE ) {
                        // 1.모델(names)에서 제거하고
                            names.remove(i);
                        // 2. 모델이 변경되었다고 어뎁터에 알리면 listView가 업데이트 된다.
                        adapter.notifyDataSetChanged();
                    }
                }
            };


               //[코딩 순서 1]
               new AlertDialog.Builder(this)
                .setTitle("알림")
                .setMessage("삭제할까요?")
                .setPositiveButton("네", listener) //네 눌렀을 때 호출할 리스너 객체를 만들어주기 [순서2]
                .setNeutralButton("아니요", null)
                .create()
                .show();
        return false;
    }
}




