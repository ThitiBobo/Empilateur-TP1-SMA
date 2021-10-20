package com.example.empilateurtp1sma.mytest;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    private String number;
    private Semaphore semaphore ;

    public SemaphoreTest(String number){
        this.semaphore = new Semaphore(1);
        this.number = number;
    }

    public void aquiere(String m) throws InterruptedException {
        semaphore.acquire();
        System.out.println(m + " aquire r:" + number);
    }

    public void release(String m) throws InterruptedException {
        semaphore.release();
        System.out.println(m + " release r:" + number);
    }

    public boolean tryAquire(){
        return semaphore.tryAcquire();
    }

}
