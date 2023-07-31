package com.example.hellokotlin
/*
class Person constructor(name:String){
    //필드 선언
    var name:String
    //초기화 블럭
    init {
        //생성자의 인자로 전달받은 값을 매개 변수에 저장
        this.name=name
    }
}
*/

//위를 줄이면 아래와 같다
//var or val을 생성자의 인자에 선언(반드시 써야함) 하면 전달받은 값이 자동으로 같은 이름의 필드가 만들어지면서
//값이 필드에 대입된다.
//var은 수정이 가능한 필드가 만들어지고 , val은 수정이 불가능한 필드가 만들어진다.

class Person(var name:String)
/*
    Java에서의 생성자
    class Person{
        String name;
        public Person(String name) {
             this.name = name
        }
    } --> 생성자에서 String name을 받아서 그 값을 필드에 저장하는 코드
 */

data class Member(var num:Int, var name:String, var addr:String)



fun main() {
    var p1 = Person("김구라")
    println("p1.name:"+p1.name)
    println(p1) //person객체의 hash값이 출력됨

    //Member 객체 생성
    var m1 = Member(1, "김구라", "노량진")
    println("번호:${m1.num} 이름:${m1.name} 주소:${m1.addr}")
    println(m1) // Member객체의 해쉬값
    // 클래스 선언할 때 데이터 예약어를 붙이면 객체를 직접 출력했을 때 콘솔창에 어떤 데이터 값을 가지고 있는지 출력해줌


    //수정 가능한 List객체를 얻어내서 참조값을 members라는 상수에 담기
    var members: MutableList<Member> = mutableListOf<Member>()
        //in JAVA List<Member> members
//     val members: MutableList<Member>
//     in JAVA final List<Members> members

    // type 추론이 가능하기 때문에 member2의 타입 생략가능
    var members2= mutableListOf<Member>()

    //List의 add메소드를 이용해서 Member객체의 참조값 저장하기
    members.add(m1);
    members.add(Member(2,"해골", "행신동"));

    //List의 forEach() 함수를 호출하면서 함수를 전달하면 전달한 함수의 매개 변수
    // List에 저장된 아이템이 순서대로 전달된다.

    //forEach 함수에 아이템을 전달하면 전달된 즉시 호출이 되고, 호출되는 횟수는 list의 아이템 갯수만큼 실행이 된다.
    //함수를 전달해놓고 중괄호 안에서 작업
    members.forEach(fun(item){
        println(item)
    })
    println("---------");
    //매개변수가 1개인 경우 생략 가능 --> it:Member 전달된 바로 그것! it!
//    members.forEach({
//    })

    // ( )도 생략 가능
        members.forEach{
            println(it)
    }






}
