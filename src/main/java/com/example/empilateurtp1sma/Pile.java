package com.example.empilateurtp1sma;

import java.util.Stack;

public class Pile {

    private Stack stack;

    public Pile(){
        this.stack = new Stack<Agent>();
    }

    public void add(Agent agent){
        this.stack.add(agent);
    }

    public Agent pop(){
        this.stack.pop();
    }

}

