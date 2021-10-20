package com.example.empilateurtp1sma;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class StackHandler {

    private static List<Stack> stacks = new ArrayList<>();
    private static ReentrantLock mutex = new ReentrantLock();
    private static Random rand = new Random();


    public static Stack acquireStack(Agent agent){
        mutex.lock();
        Stack stack = StackHandler.find(agent);
        AtomicReference<Stack> res = null;
        do {
             res.set(StackHandler.getRandomStack());
        }while(res.get() == stack);
        return res.get();
    }

    public static void releaseStack(Agent agent){
        mutex.unlock();
    }


    public static Stack find(Agent agent){
        Stack container = null;
        for(Stack stack : stacks)
            if (stack.contains(agent)) {
                container = stack;
            }
        return container;
    }

    private static Stack getRandomStack(){
        return stacks.get(rand.nextInt(stacks.size()));
    }

}
