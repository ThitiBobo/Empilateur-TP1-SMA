package com.example.empilateurtp1sma;

import java.util.Stack;

public class Pile {

    private Stack<Agent> stack;

    public Pile(){
        this.stack = new Stack<>();
    }

    public void add(Agent agent){
        this.stack.add(agent);
    }

    public Agent pop(){
        return this.stack.pop();
    }

}

