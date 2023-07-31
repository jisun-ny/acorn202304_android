package com.example.hellokotlin

/*
    in Java

    class Util{
        public static int number = 10
        public String version = "1.0"
        public static void get(){}
        public void send(){}
    }
    Util.number;
    new Util().version;
    Util.get();
    new Util().send();
 */

//이렇게 사용하면 static 처럼 사용할 수 있음
class Util{
    //Util 클래스와 함께하는 동반 객체
    companion object{
        //동반객체의 필드와 메소드(함수)를 정의하면 됨
        val version:String = "1.0"
        fun send(){
            println("전송합니다")
        }
    }
}
fun main(){
    Util.send()
    println(Util.version)
}