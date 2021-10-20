package com.example.empilateurtp1sma.mytest;

public class Test implements Runnable{

    private String number;
    public Test(String number){
        this.number = number;
    }

    @Override
    public void run() {
        System.out.println("T"+number + " started");
        try {
            Main.sm2.aquiere("T"+number);

            Main.sm1.tryAquire();
            Main.sm1.aquiere("T"+number);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("T"+number + " terminated");

    }
}
