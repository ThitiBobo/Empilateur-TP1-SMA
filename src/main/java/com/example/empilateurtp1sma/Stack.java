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

    public void pop(Agent agent) throws Exception {
        if (this.stack.peek() != agent)
            throw new Exception("The moving agent is not at the top of the stack");
        this.stack.pop();

    }

    public boolean contains(Agent agent){
        return this.stack.contains(agent);
    }

    public Agent getAgentBelow(Agent agent) throws Exception {
        if (!contains(agent)) throw new Exception("the Agent is not in the Stack");
        int index = stack.indexOf(agent);
        try{
            return stack.elementAt(index - 1);
        }catch (IndexOutOfBoundsException e){
            return null;
        }
    }

    public Agent getAgentAbove(Agent agent) throws Exception {
        if (!contains(agent)) throw new Exception("the Agent is not in the Stack");
        int index = stack.indexOf(agent);
        try{
            return stack.elementAt(index + 1);
        }catch (IndexOutOfBoundsException e){
            return null;
        }
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

