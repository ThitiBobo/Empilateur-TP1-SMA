package com.example.empilateurtp1sma;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class StackHandler {

    private List<Stack> stacks = new ArrayList<>();
    private ReentrantLock mutex = new ReentrantLock();
    private Random rand = new Random();

    public void add(Stack stack){
        stacks.add(stack);
    }

    public Stack acquireStack(Agent agent){
        mutex.lock();
        Stack stack = find(agent);
        AtomicReference<Stack> res = null;
        do {
             res.set(getRandomStack());
        }while(res.get() == stack);
        return res.get();
    }

    public void releaseStack(Agent agent){
        mutex.unlock();
    }


    public Stack find(Agent agent){
        Stack container = null;
        for(Stack stack : stacks)
            if (stack.contains(agent)) {
                container = stack;
            }
        return container;
    }

    private Stack getRandomStack(){
        return stacks.get(rand.nextInt(stacks.size()));
    }

    @Override
    public String toString() {
        return "StackHandler{" +
                "stacks=" + stacks +
                '}';
    }

    public String display(){
        StringBuilder builder = new StringBuilder();
        this.stacks.forEach( stack -> builder.append(stack.display()).append("\n"));
        return builder.toString();
    }
}
