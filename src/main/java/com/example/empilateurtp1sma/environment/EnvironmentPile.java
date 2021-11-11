package com.example.empilateurtp1sma.environment;

import com.example.empilateurtp1sma.agents.AgentBlock;
import com.example.empilateurtp1sma.agents.Agent;

import java.util.HashMap;

public class EnvironmentPile extends Environment {

    private StackHandler handler = new StackHandler();

    public StackHandler getHandler() {
        return handler;
    }

    @Override
    public void addAgent(Agent agent){
        super.addAgent(agent);
        ((AgentBlock) agent).setStackHandler(handler);
    }

    @Override
    public void addResource(Resource resource){
        super.addResource(resource);
        if (resource instanceof Stack){
            handler.add((Stack)resource);
        }
    }

    @Override
    synchronized public void start(long timeoutMillis) {
        this.report.setInitialState(display());
        this.report.addState(display());
        this.agents.forEach(Thread::start);
        try {
            wait(timeoutMillis);
            stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        this.agents.forEach(Thread::interrupt);
        this.report.addState(display());
        this.report.setFinalState(display());
    }

    public void setAgent(String a, String s){
        AgentBlock agent = findAgent(a);
        Stack stack = findRessource(s);
        stack.push(agent);
    }

    public void setObjectif(String sourceString, String targetString){
        AgentBlock agent = findAgent(sourceString);
        AgentBlock target = findAgent(targetString);
        agent.setObjectif(target);
    }

    public AgentBlock findAgent(String agentString){
        return (AgentBlock) this.agents.stream()
                .filter(agent -> agent.getTag().equals(agentString))
                .findFirst()
                .orElse(null);
    }

    public Stack findRessource(String stackString){
        return (Stack) this.ressources.stream()
                .filter( s -> stackString.equals(s.getTag()))
                .findFirst()
                .orElse(null);
    }

    public String display(){
        return handler.display();
    }
}