package com.example.empilateurtp1sma.environment;

import com.example.empilateurtp1sma.agents.AgentBlock;
import com.example.empilateurtp1sma.agents.Agent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class StackHandler {

    private List<Stack> stacks = new ArrayList<>();
    private ReentrantLock mutex = new ReentrantLock();
    private Random rand = new Random();

    public void add(Stack stack){
        stacks.add(stack);
    }

    public Stack find(Agent agent){
        Stack container = null;
        for(Stack stack : stacks)
            if (stack.contains(agent)) {
                container = stack;
            }
        return container;
    }

    public Stack acquireStack(Agent agent) throws InterruptedException {


        mutex.lockInterruptibly();

        Stack stack = find(agent);
        Stack res = null;
        do {
             res = getRandomStack();
        }while(res == stack);
        return res;
    }

    public void releaseStack(Agent agent) throws InterruptedException {
        mutex.unlock();
        Thread.sleep(10);
        // System.out.println(mutex.getHoldCount());
    }

    private Stack getRandomStack(){
        return stacks.get(rand.nextInt(stacks.size()));
    }

    public AgentBlock getAgentBelow(AgentBlock agentBlock){
        Stack stack = find(agentBlock);
        try {
            return (AgentBlock) stack.getAgentBelow(agentBlock);
        } catch (Exception e) {
            return null;
        }
    }

    public AgentBlock getAgentAbove(AgentBlock agentBlock) {
        Stack stack = find(agentBlock);
        try {
            return (AgentBlock) stack.getAgentAbove(agentBlock);
        } catch (Exception e) {
            return null;
        }
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
