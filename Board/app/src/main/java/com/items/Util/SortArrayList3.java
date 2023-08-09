package com.items.Util;

import java.util.ArrayList;
import java.util.Collections;
 
public class SortArrayList3 {
    public static void main(String[] args) {
 
        // ArrayList �غ�
        ArrayList<Fruit> list = new ArrayList<>();
        list.add(new Fruit("Apple", 2000));
        list.add(new Fruit("Orange", 3000));
        list.add(new Fruit("Banana", 1000));
        System.out.println("���� : " + list); // [[ Apple: 2000 ], [ Orange: 3000 ], [ Banana: 1000 ]]
 
        // price�� ������������ ���� (�Ʒ� Fruit�� implements Comparable<Fruit> �߰� �Ǿ���, 2�� �ִ°� ��� ���� �ʿ�)
        // Collections.sort(list);
        System.out.println("�������� : " + list); // [[ Banana: 1000 ], [ Apple: 2000 ], [ Orange: 3000 ]]
 
        // price�� ������������ ����
        Collections.sort(list, Collections.reverseOrder());
        System.out.println("�������� : " + list); // [[ Orange: 3000 ], [ Apple: 2000 ], [ Banana: 1000 ]]
 
    }
}
 
/*
 * class Fruit implements Comparable<Fruit> { private String name; private int
 * price;
 * 
 * public Fruit(String name, int price) { this.name = name; this.price = price;
 * }
 * 
 * @Override public int compareTo(Fruit fruit) { if (fruit.price < price) {
 * return 1; } else if (fruit.price > price) { return -1; } return 0; }
 * 
 * @Override public String toString() { return "[ " + this.name + ": " +
 * this.price + " ]"; } }
 */