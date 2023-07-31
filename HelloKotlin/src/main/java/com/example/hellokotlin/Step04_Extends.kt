package com.example.hellokotlin


/*
    코틀린에서 클래스 선언 시, 기본 값은 상속 받지 못하게 되어있다.
    마치 java에서 final class Phone() 인것 처럼...
    상속을 받을 수 있게 하려면 open이라는 예약어를 붙여준다.
 */
open class Phone {
    fun call () {
        println("전화를 걸어요!")
    }
}

open class HandPhone : Phone() { //Phone 클래스를 상속받기
    fun mobileCall () {
        println("이동 중에 전화를 걸어요!")
    }
    //코틀린에서는 재 정의 가능한 메소드(오버라이드)를 만들려면 메소드 앞에도  open을 붙여줘야한다.
    open fun takePicture() {
        println("100만 화소의 사진을 찍어요")
    }
}

class SmartPhone : HandPhone() {
    fun doInternet() {
        println("인터넷 오예")
    }
    //override라는 예약어를 이용해서 open된 메소드를 오버라이드 할 수 있다.
    override fun takePicture() {
        //필요시 부모의 메소드를 호출할 수도 있다. (Java와 동일)
        super.takePicture() // 100만 화소의 사진을 찍어요
        println("난 1000만 화소로 찍을건데") // 난 1000만 화소로 찍을건데
    }
}

fun main () {
    val p1 = Phone()
    val p2 = HandPhone()
    //Phone 클래스를 상속받은 클래스로 생성한 객체이므로 2개의 메소드를 모두 사용할 수 있다.
    p2.call()
    p2.mobileCall()
    p2.takePicture()

    val p3 = SmartPhone()
    p3.takePicture()
}