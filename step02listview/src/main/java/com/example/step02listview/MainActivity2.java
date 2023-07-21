package com.example.step02listview;

import android.app.AlertDialog;
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

public class MainActivity2 extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    //필드선언
    List<String> names;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // res/layout/activity_main을 전개해서 화면 구성
        // 전개하면서 new LinearLayout(), new ListView() 이런 객체들이 생겨나고 findViewById(R.id.listView)해서 참조값을 리턴받아 사용 가능.
        setContentView(R.layout.activity_main);

        //listView의 참조값 /listview: 사용자가 정의한 데이터 목록을 아이템 단위로 구성하여 화면에 출력하는 ViewGroup의 한 종류
        ListView listView = findViewById(R.id.listView);

        //ListView에 출력할 SampleData -나중엔 DB에서 데이터 가져오기
        //지역변수는 밖에서 사용할수 없으니까 필드 선언한다.
        //List<String> names = new ArrayList<>();
        names = new ArrayList<>(); //필드에 선언하면 null 이 코드를 안넣어주면 add를 할 수 없음.
        names.add("김구라");
        names.add("해골");
        names.add("원숭이");
        for (int i = 0; i < 100; i++) {
            names.add("아무개" + i);
        }


        //ListView에 연결할 어뎁터 객체
        // new ArrayAdapter<>( Context, Layout resource, 모델)

        adapter = new ArrayAdapter<>(
                this, //Context객체 ->MainActivity가 Context를 상속했기 때문에 this로 제공 가능
                android.R.layout.simple_list_item_1, //미리 준비된 레이아웃 문서를 참조-> android.R.layout해서 찾는다(R.layout은 우리가 만든것 중 찾는것) simple_list_item_1.xml 문서를 참조하는 것..
                names //데이터가 들어있는 배열
        );

        //어뎁터를 ListView에 연결하기
        listView.setAdapter(adapter);

        //listView.setOnClickListener();
        //listView는 화면 전체여서 아무데나 클릭해도 동작하기 때문에 몇번째 아이템을 클릭했는지 알 수 없다.
        listView.setOnItemClickListener(this); //this하기 위해서는 MainActivity를 implements 시켜줘야한다.
        //implements 시킨후 오버라이드해야하는데 추상메소드가 하나다! --> 함수 형태로 간편하게 만들 수 있다.

        listView.setOnItemLongClickListener(this);//this를 쓰기 위해서 인터페이스를 구현해준다. (인터페이스는 여러개 구현 가능)

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
        //view: 텍스트뷰 하나를 가지고 있는 cell //i:셀의 인덱스
        //i는 클릭한 Item의 인덱스 값
        Log.d("MainActivity", "i:" + i); //Log.d 디버깅 -> logcat에서 확인
        //클릭한 Item에 출력된 이름
        String clickedName = names.get(i);
        //토스트(가벼운 메세지) 출력
        Toast.makeText(this, clickedName, Toast.LENGTH_SHORT).show();

    }

    //listView의 cell을 오랫동안 클릭하면 호출되는 메소드
    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

        //알림창에 있는 버튼을 눌렀을때 호출되는 메소드를 가지고 있는 리스너 객체
        //익명(클래스)의 local innerClass(메소드 안에 정의된 클래스 -> 이너클래스)
        //이너클래스의 메소드 안에서는 바깥영역에 있는 지역변수 참조 가능, 필드 참조 가능
        //즉, 해골을 롱클릭하면 i에 1이 전달

        //일회용으로 쓰임 오랬동안 클릭할때마다 생성되고, 생성되고, 또 오랫동안 클릭되면 또 생성되고 생성되고,,..



    //일회용으로 쓰임 오랬동안 클릭할때마다 생성되고, 생성되고, 또 오랫동안 클릭되면 또 생성되고 생성되고,,..
        //[방법2-2]
        new AlertDialog.Builder(this)
                .setTitle("알림")
                .setMessage("삭제할까요?")
                .setPositiveButton("네", (a, b) -> { //이렇게 함수 형태로 전달할 수도 있다.
                    // 1.모델(names)에서 제거하고
                    names.remove(i);
                    // 2. 모델이 변경되었다고 어뎁터에 알리면 listView가 업데이트 된다.
                    adapter.notifyDataSetChanged();
                })
                .setNeutralButton("아니요", null)
                .create()
                .show();
        return false;

        //[ 방법 2-1]
        // - 오버라이드할 메소드가 1개 일 경우
/*
        new AlertDialog.Builder(this)
                .setTitle("알림")
                .setMessage("삭제할까요?")
                .setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if( result == DialogInterface.BUTTON_POSITIVE ) {
                            // 1.모델(names)에서 제거하고
                            names.remove(i);
                            // 2. 모델이 변경되었다고 어뎁터에 알리면 listView가 업데이트 된다.
                            adapter.notifyDataSetChanged();
                        }
                    } )
                .setNeutralButton("아니요", null)
                .create()
                .show();
        return false;

*/
    }
}