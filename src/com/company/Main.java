package com.company;


import MyPack.TreapClass;


public class Main {

    public static void main(String[] args) {

        TreapClass tree = new TreapClass(-1, "Жопа");

        for (int i = 0; i < 5; i++) {
             tree.push(i, "" + i);
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(tree.find(i));
        }
        System.out.println("-----------");
        System.out.println(tree.pop(4));
        System.out.println("-----------");

        for (int i = 0; i < 5; i++) {
           System.out.println(tree.find(i));
        }
        System.out.println("-----------");

        System.out.println(tree.pop(3));
        System.out.println("-----------");
        for (int i = -1; i < 5; i++) {
            System.out.println(tree.find(i));
        }

    }
}

