package com.InstanceOf;

public class InstanceofTest {

    public static void main(String[] args){

        Parent parent = new Parent();
        Child child = new Child();
        
        // instancof는 해당 클래스가 자기집이 맞는지 확인해 주는 것이라고 생각하자
        System.out.println( parent instanceof Parent );  // true
        System.out.println( child instanceof Parent );   // true child가 Parent를 상속받음
        System.out.println( parent instanceof Child );   // false
        System.out.println( child instanceof Child );   // true
    }
    // https://mine-it-record.tistory.com/m/120
}
