package com.example.empilateurtp1sma;

import java.util.ArrayList;
import java.util.List;

public class Stack implements Resource{

    private java.util.Stack<Agent> stack;

    public Stack(){
        this.stack = new java.util.Stack<>();
    }

    public void push(Agent agent){
        this.stack.push(agent);
    }

    public Agent pop(Agent agent){
        return this.stack.pop();
    }
}

