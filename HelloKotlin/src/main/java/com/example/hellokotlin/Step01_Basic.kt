package com.example.hellokotlin

/*
    [kotlin 기본 data type] - import 없이 바로 사용할 수 있는 data type

    Byte, Short, Int, Long (자바에선 Integer)
    Float, Double
    Boolean, Char
    String
 */

// 메인 함수
fun main (){
    //정수를 담을 수 있는 num이라는 이름의 변수를 만들어서 10을 대입하기
    var num:Int = 10;
    //실수를 담을 수 있는 num2라는 이름의 변수를 만들어서 10.1 대입하기
    var num2:Double = 10.1;
    //myName이라는 변수에 문자열 "지선" 대입하기
    var myName:String = "지선";
    //yourName 이라는 상수에 문자열 "수정"대입하기
    val yourName:String = "수정";
    //변수는 값 변경 가능
    myName = "남지선";
    //상수는 값이 한번 결정되면 값 변경 불가
    //yourName = "수정공주";

    //type 추론이 가능한 경우 type 생략 가능
    var num3 = 30;
    var ourName = "에이콘";
    var num4 = 10.1;

    //변수를 미리 선언하고
    var num5:Int;
    //값을 나중에 넣을 수 있다.
    num5 = 50;

    //변수나 상수를 미리 만들고 값을 나중에 넣고 싶으면 반드시 type을 명시해야한다.
    //var num6; --> 이렇게 변수만 선언해 놓으면 type 추론 불가능
    //num6 = 60;

    //상수도 미리 만들어 놓고
    val PI:Float;
    //값을 나중에 대입할 수 있다.
    PI = 3.14159f;
    //PI = 3.14f --> 상수는 값 변경 불가



    println("main() 함수가 종료 됩니다");
}