package com.items.Util;

import java.util.ArrayList;
import java.util.Collections;
 
public class SortArrayList3 {
    public static void main(String[] args) {
 
        // ArrayList 준비
        ArrayList<Fruit> list = new ArrayList<>();
        list.add(new Fruit("Apple", 2000));
        list.add(new Fruit("Orange", 3000));
        list.add(new Fruit("Banana", 1000));
        System.out.println("원본 : " + list); // [[ Apple: 2000 ], [ Orange: 3000 ], [ Banana: 1000 ]]
 
        // price순 오름차순으로 정렬 (아래 Fruit에 implements Comparable<Fruit> 추가 되야함, 2에 있는걸 잠깐 종료 필요)
        // Collections.sort(list);
        System.out.println("오름차순 : " + list); // [[ Banana: 1000 ], [ Apple: 2000 ], [ Orange: 3000 ]]
 
        // price순 내림차순으로 정렬
        Collections.sort(list, Collections.reverseOrder());
        System.out.println("내림차순 : " + list); // [[ Orange: 3000 ], [ Apple: 2000 ], [ Banana: 1000 ]]
 
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