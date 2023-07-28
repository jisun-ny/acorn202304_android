package com.example.hellokotlin

// [필드 선언방식 1]
class Cat constructor(){
    //init 블럭은 primary 생성자의 일부
    init {
        println("야옹이가 생겼어요!")
    }
    //뒤늦은 초기화가 가능한 필드 lateinit 예약어 붙여서 선언
    lateinit var name:String
    constructor(name:String) : this() { //this(): primary 생성자를 호출하는 표현식
        println("야옹이의 이름은:${name}")
        this.name = name
    }
}

// [필드 선언방식 2]
class Dog constructor(){
    init {
        println("멍멍이가 생겼어요!")
    }
    var name:String? = null
    constructor(name:String):this(){
        // null 이 가능한 type 공간에  null 이 불가능한 type 공간에 담긴 값을 대입하는것은 가능
        this.name=name
    }
}



fun main(){
    val c1 = Cat("톰캣")
    println(c1.name)

    val c2 = Cat()
//    println(c2.name) //lateinit property name has not been initialized --> 초기화를 해주지 않아서 오류
    //lateinit 필드에 값을 넣어주고
    c2.name = "키티"
    //필드를 참조해야한다.
    println(c2.name)

    //null 값이 가능한 data type은 type 뒤에 ?를 붙여주어야한다.
    //String type과 String? type은 다른 type으로 간주된다.
    var myName:String? = null
    myName = "남지선"
    myName = null
    //이걸 통해 코틀린에서는 null오류를 잡을 수 있다. --> 아예 type? 이 아니면 null이들어가지 않음
    //자바는 기본데이터타입이 있지만
    //코틀린은 전부 참조 데이터타입
    /*
    즉
    bar myNum:Int = 10
    은 10이 진짜 들어간느게 아니라
    힙 영역에 23번 방에 10이라는 숫자가 들어가고 23번 참조값을 myNum에 넣어준 것.

    즉, Int타입을 초기화하기 위한 빈 공간을 넣어줄 수 있다 (자바에서는 Integer에 null넣을 수 없다 초기화하기 위해서 0을 넣어줬었음)
     */
    //Int(숫자) type도 null을 넣어 놓고 값을 나중에 대입할 수 있다.
    var myNum:Int? = null;
    myNum = 999;
    myNum = null;

    var d1 = Dog("바둑이")
    var d2 = Dog()
    println(d1.name)
    println(d2.name)

}