package com.company;


import MyPack.TreapClass;


public class Main {

    public static void main(String[] args) {

        TreapClass tree = new TreapClass(-1, "Жопа");

        for (int i = 0; i < 10; i++) {
             tree.push(i, "" + i);
        }
        System.out.println(tree.pop(4));
        System.out.println("-----------");

        for (int i = 0; i < 10; i++) {
           System.out.println(tree.find(i));
        }
        System.out.println(tree.pop(5));
        System.out.println("-----------");
        for (int i = 0; i < 10; i++) {
            System.out.println(tree.find(i));
        }
        System.out.println(tree.find(-1));
    }
}

