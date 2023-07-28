package com.example.step05fragment;
/*
    [Fragment]
    -액티비티의 제어하에 존재하는 mini Controller
    -재활용을 염두에 두고 만드는 경우가 많다
    -재활용이라는 것은 여러개의 액티비티에서 활용된다는 의미

    [Fragment 만든느 방법]
    1. Fragment 클래스를 상속 받는다.
    2. 프래그먼트의 layout.xml 문서를 만든다.
    3. onCreateView() 메소드를 오버라이딩 한다.


    화면이 전환되어도 액티비티가 전환 안되게할 수 있다. --> 사실상 프래그먼트가 전환되는 것

 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/*
    MyFragment객체 안에서는 MainActivity type이 쓰이지 않음 -> 독립적으로 쓰인다.
 */
public class MyFragment extends Fragment implements View.OnClickListener {
    TextView textView;
    // onCreateView(): 해당 프레그먼트가 제어할 View 객체를 만들어서 리턴해주는 메소드
    @Nullable
    @Override
                                    //레이아웃 전개자 객체 --> View를 만듬
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // 1. fragment_my.xml 문서를 전개해서 View를 만든 다음
        View view = inflater.inflate(R.layout.fragment_my, container, false); //container는 인자로 전달받음. 마지막 인자는 항상 false
        //만든 View 객체안에 있는 TextView의 참조값을 얻어낸다
        textView = view.findViewById(R.id.textView);
        //TextView에 OnClick 리스너를 등록한다.
        textView.setOnClickListener(this);
        // 2.해당 View를 리턴해준다.
        return view;
    }

    @Override
    public void onClick(View view) {
        //TextView에 출력한 문자열을 숫자로 바꿔서 얻어낸다.
        //위에서 얻어낸 텍스트뷰의 참조값이 여기에서도 필요. --> 필드에 선언하자
        int count = Integer.parseInt(textView.getText().toString());
        //count를 1 증가 시킨다.
        count++;
        //TextView에 문자열로 바꿔서 출력한다.
        textView.setText(Integer.toString(count));
        /*
            만일 프레그먼트에서 현재 자신을 제어하고 있는 액티비티에 어떠한 데이터를
            전달하고자 한다면 어떻게 해야할까?
         */

/*
    이렇게 코딩하면 mainactivity에서만 사용 가능. 다른 곳에서 재활용 불가

        FragmentActivity a = getActivity(); //new MainActivity된 객체의 참조값을 부모 타입으로 리턴해줌.
        Context b = getActivity();
        //MainActivity c = getActivity();
        MainActivity c = (MainActivity) getActivity(); //자식타입으로 캐스팅을 해야함
        //이떄 a, b, c의 참조값은 같다. 변수의 타입(값에 대한 사용설명서)만 다를 뿐.
        c.sendMsg("hello!");
*/
    //FragmentActivity는 모든 Activity의 부모타입 또한 모든 Activity는 Context 타입
        //Fragment는 context타입이아니다. 따라서 context에 this를 쓰면 안됨. getActivity()를 써야함. 리턴

        //이 fragment를 제어하고 있는 액티비티의 참조값 얻어내기
        FragmentActivity fa = getActivity();
        //만일 이 액티비티가 MyFragmentListenter type이 맞다면
        if(fa instanceof MyFragmentListener) {
            //액티비티의 참조값을 MyFragmentListener type으로 casting해서
            MyFragmentListener ma = (MyFragmentListener) fa;
            ma.sendMsg("hello");
        }



    }

    // 액티비티에서 특정 시점에 호출할 예정인 메소드
    public void reset() {
        textView.setText("0");
    }

    public interface MyFragmentListener{
        public void sendMsg(String msg);
    }
}
