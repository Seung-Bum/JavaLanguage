package com.items.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class SortArrayList2 {
    public static void main(String[] args) {
 
        // ArrayList �غ�
        ArrayList<Fruit> list = new ArrayList<>();
        list.add(new Fruit("Apple", 2000));
        list.add(new Fruit("Orange", 3000));
        list.add(new Fruit("Banana", 1000));
        System.out.println("���� : " + list); // [[ Apple: 2000 ], [ Orange: 3000 ], [ Banana: 1000 ]]
 
        // price�� ������������ ����
        Collections.sort(list, new FruitPriceComparator());
        System.out.println("price �� �������� : " + list); // [[ Banana: 1000 ], [ Apple: 2000 ], [ Orange: 3000 ]]
 
        // price�� ������������ ����
        Collections.sort(list, new FruitPriceComparator().reversed());
        System.out.println("price �� �������� : " + list); // [[ Orange: 3000 ], [ Apple: 2000 ], [ Banana: 1000 ]]
 
        // name�� ������������ ����
        Collections.sort(list, new FruitNameComparator());
        System.out.println("price �� �������� : " + list); // [[ Apple: 2000 ], [ Banana: 1000 ], [ Orange: 3000 ]]
 
        // name�� ������������ ����
        Collections.sort(list, new FruitNameComparator().reversed());
        System.out.println("price �� �������� : " + list); // [[ Orange: 3000 ], [ Banana: 1000 ], [ Apple: 2000 ]]
 
    }
}
 
class FruitPriceComparator implements Comparator<Fruit> {
    @Override
    public int compare(Fruit f1, Fruit f2) {
        if (f1.price > f2.price) {
            return 1;
        } else if (f1.price < f2.price) {
            return -1;
        }
        return 0;
    }
}
 
class FruitNameComparator implements Comparator<Fruit> {
    @Override
    public int compare(Fruit f1, Fruit f2) {
        return f1.name.compareTo(f2.name);
    }
}

// SortArrayList3�� �̹� Fruit ����
class Fruit {
    String name;
    int price;
 
    public Fruit(String name, int price) {
        this.name = name;
        this.price = price;
    }
 
    @Override
    public String toString() {
        return "[ " + this.name + ": " + this.price + " ]";
    }
}
