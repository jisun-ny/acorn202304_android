package com.example.step04gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class GalleryAdapter extends BaseAdapter {

    //필요한 필드 정의하기

    private LayoutInflater inflater; // 레이아웃 전개자 객체를 담을 필드
    /* 밑 세개는 생성자의 인자로 받을 예정 */
    private Context context; //앱 컨텍스트를 담을 필드
    private int layoutRes;// 셀의 레이아웃 리소스id를 담을 필드
    private List<GalleryDto> list; //모델

    //생성자
    public GalleryAdapter(Context context, int layoutRes, List<GalleryDto> list) {
        this.context = context;
        this.layoutRes = layoutRes;
        this.list = list;
        //레이아웃 전개자 객체(LayoutInflater)의 참조값을 얻어내서 필드에 저장
        inflater = LayoutInflater.from(context); //LayoutInflater 클래스에 점 찍어서 메소드 호출(스테틱 메소드니까 가능)
        // 메소드 호출해주면서 생성자의 인자로 전달받을 예정인 context를 전달 --> (LayoutInflater-> 레이아웃 xml문서를 전개해서 view를 만든느 역할을 하는 객체// 이 객체의 도음을 받아야 view를 만들 수 있ㄸ.)
    }

    //여기 밑에 모든 메소드들은 listview객체가 필요하다면 알아서 호출해서 사용
    // 모델이 갯수
    @Override
    public int getCount() {
        return list.size();
    }

    //i번째 모델
    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    //i번째 모델의 아이디(Primary Key)가 있으면 리턴
    @Override
    public long getItemId(int i) {
        return list.get(i).getNum();
    }

    // i번째 cell의 view를 만들거나 이미 만들어져 있다면 수정해서 리턴
    // 이미지 요청 경로만 넣어주면 알아서 비동기로 처리해서 앱에다 이미지를 처리해주는 glide 라이브러리 이용
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView == null){
            //레이아웃 전개자 객체를 이용해서 cell view를 만든다.
            convertView=inflater.inflate(layoutRes, viewGroup, false);
            //layoutRes에 나중에 R.layout.listview.cell.xml이 전달될 예정
        }
        // position 에 해당하는 GalleryDto 를 얻어내서
        GalleryDto dto=list.get(position);
        ImageView imageView=convertView.findViewById(R.id.imageView);
        TextView textWriter=convertView.findViewById(R.id.writer);
        TextView textCaption=convertView.findViewById(R.id.caption);
        TextView textRegdate=convertView.findViewById(R.id.regdate);

        //TextView에는 작성자, 제목, 등록일을 출력하고
        textWriter.setText(dto.getWriter());
        textCaption.setText(dto.getCaption());
        textRegdate.setText(dto.getRegdate());
        //ImageView에는 Glide를 이용해서 이미지를 출력하기
        Glide.with(context)
                .load(dto.getImagePath())    //여기에는 "http://192.168.043:9000/android/gallery/images/xxx.png" 같은 형식의 경로가 들어갈 예정
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);
        //gallery 정보가 출력된 View 객체 리턴해주기
        return convertView;
    }
}
