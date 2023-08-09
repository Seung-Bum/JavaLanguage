package com.items.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class SortArrayList {
    public static void main(String[] args) {
 
        // ArrayList �غ�
        ArrayList<String> list = new ArrayList<>(Arrays.asList("C", "A", "B", "a"));
        System.out.println("���� : " + list);  // [C, A, B, a]
 
        // ������������ ����
        list.sort(Comparator.naturalOrder());
        System.out.println("�������� : " + list);  // [A, B, C, a]
 
        // ������������ ����
        list.sort(Comparator.reverseOrder());
        System.out.println("�������� : " + list); // [a, C, B, A]
        
        // ��ҹ��� ���о��� �������� ����
        list.sort(String.CASE_INSENSITIVE_ORDER);
        System.out.println("��ҹ��� ���о��� �������� : " + list); // [a, A, B, C]
        
        // ��ҹ��� ���о��� �������� ����
        list.sort(Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
        System.out.println("��ҹ��� ���о��� �������� : " + list); // [C, B, a, A]
    }
}
