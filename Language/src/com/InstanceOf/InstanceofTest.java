package com.InstanceOf;

public class InstanceofTest {

    public static void main(String[] args){

        Parent parent = new Parent();
        Child child = new Child();
        
        // instancof�� �ش� Ŭ������ �ڱ����� �´��� Ȯ���� �ִ� ���̶�� ��������
        System.out.println( parent instanceof Parent );  // true
        System.out.println( child instanceof Parent );   // true child�� Parent�� ��ӹ���
        System.out.println( parent instanceof Child );   // false
        System.out.println( child instanceof Child );   // true
    }
    // https://mine-it-record.tistory.com/m/120
}
