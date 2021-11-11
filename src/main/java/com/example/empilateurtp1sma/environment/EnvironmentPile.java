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

    public void setAgent(String a, String s){
        AgentBlock agent = findAgent(a);
        Stack stack = findRessource(s);
        stack.push(agent);
    }

    public void setObjectif(String sourceString, String targetString){
        AgentBlock agent = findAgent(sourceString);
        agent.setObjectif(targetString);
    }

    @Override
    synchronized public void start(long timeoutMillis) {
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
    }

    public void setAgent(String a, String s){
        AgentBlock agent = findAgent(a);
        Stack stack = findRessource(s);
        stack.push(agent);
    }

    @Override
    public Observation observe(Agent agent) {
        AgentBlock agentBeneath = (AgentBlock) stackHandler.getAgentBeneath(agent);
        ObservationPile observation = new ObservationPile(agentBeneath.getId());
        observation.setPushed(agentBeneath.isWaitingForBlockToMove());
        return observation;
    }

    public AgentBlock findAgent(String agentString){
        return (AgentBlock) this.agents.stream()
                .filter(agent -> agent.getTag().equals(agentString))
                .findFirst()
                .orElse(null);
    }

    protected boolean moveTo(Agent agent, Stack destination){
        return stackHandler.move(agent, destination);
    }

    protected void agentPush(Agent agent){
        ((AgentBlock) stackHandler.getAgentAbove(agent)).quitSleepMode();
    }
}