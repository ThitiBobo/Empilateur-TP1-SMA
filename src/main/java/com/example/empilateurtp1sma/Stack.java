package com.example.empilateurtp1sma;

import java.util.ArrayList;
import java.util.List;

public class Stack extends Resource{

    private java.util.Stack<Agent> stack;

    public Stack(String tag){
        super(tag);
        this.stack = new java.util.Stack<>();
    }

    /**
     * Pushes an {@code Agent} onto the top of this stack.
     * @param agent, an {@code Agent} to pushes
     */
    public void push(Agent agent){
        this.stack.push(agent);
    }

    public Agent pop(Agent agent){
        return this.stack.pop();

    }

    public boolean contains(Agent agent){
        return this.stack.contains(agent);
    }

    @Override
    public String toString() {
        return "Stack{" +
                "tag='" + tag + '\'' +
                ", stack=" + stack +
                '}';
    }

    public String display(){
        StringBuilder builder = new StringBuilder();
        builder.append("stack:" + this.tag);
        builder.append(" [ ");
        this.stack.forEach( agent -> builder.append(agent.getTag() + " "));
        builder.append("]");
        return builder.toString();
    }
}

