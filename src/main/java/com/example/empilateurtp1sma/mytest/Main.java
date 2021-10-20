package com.example.empilateurtp1sma.mytest;

public class Main {

    public static SemaphoreTest sm1;
    public static SemaphoreTest sm2;
    public static SemaphoreTest sm3;


    public static void main(String[] args) throws InterruptedException {

        Main.sm1 = new SemaphoreTest("1");
        Main.sm2 = new SemaphoreTest("2");
        Main.sm3 = new SemaphoreTest("3");


        Main.sm1.aquiere("Main");

        new Thread(new Test("1")).start();

        Thread.sleep(3000);
        Main.sm1.release("Main");

    }
}
