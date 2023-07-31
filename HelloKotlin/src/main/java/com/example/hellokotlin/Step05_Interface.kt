package com.example.hellokotlin

//kotlin에서 인터페이스 만들기
interface Remocon {
    fun up()
    fun down()
}

//인터페이스를 구현한 클래스 만들기
/*
    [클래스 상속]
    : 클래스명()

    [인터페이스 구현]
    : 인터페이스명 --> 인터페이스는 생성자가 없기때문에 소괄호가 없다.
 */
class MyRemocon : Remocon{
    //more option or alt + enter
    //return type Unit ==> same as void in Java
    override fun up() {
      println("무언가를 올려요!")
    }

    override fun down() {
        println("무언가를 내려요")
    }
}

fun main () {
    val r1 = MyRemocon()
    r1.up()
    r1.down()


/*
    in java
        Remocon r = new Remocon() {
            @override
            public void up(){}
            @override
            public void down(){}
        };

    in kotlin
 */

//클래스를 정의함과 동시에 객체를 생성한느 것이 object 키워드이다. (익명클래스 (이름이 없는 클래스) 생성하는 법)
    // Remocon {} 이 클래스로 만든 클래스의 참조값이 object다~ 이 값을 이용해서 클래스 객체 만들어라~
    //익명클래스로 참조값 얻어내기
    val r2:Remocon = object : Remocon {
        override fun up() {
            println("채널을 올려요")
        }

        override fun down() {
            println("채널을 내려요")
        }
    }
    r2.up()
    r2.down()
}