package com.example.empilateurtp1sma.agents;

import com.example.empilateurtp1sma.environment.EnvironmentPile;
import com.example.empilateurtp1sma.environment.Stack;

public class AgentBlock2 extends AgentBlock{

    public AgentBlock2(String tag) {
        super(tag);
    }

    @Override
    protected void move() throws InterruptedException {

        Stack origin = handler.find(this);
        Stack target = handler.acquireStack(this);

        AgentBlock2 a = ((AgentBlock2) target.peek());
        boolean usefulMovement;
        if (a == null) {
            usefulMovement = this.objectif == a;
        }else {
            usefulMovement = this.objectif == a && a.checkObjective();
        }

        if (usefulMovement){
            System.out.println("mouvement utile");
        }

        if (pushed || usefulMovement){
            try {
                origin.pop(this);
                target.push(this);
                this.pushed = false;
                this.environment.getReport().addState(((EnvironmentPile) this.environment).display());
                this.environment.getReport().addAction(
                        "MOVE",
                        tag + ": " + origin.getTag() + " > " + target.getTag());
                this.environment.getReport().increment();
            } catch (Exception e) {
                System.out.println("BOT:" + tag + " had a problem to move");
            }
        }
        handler.releaseStack(this);
    }

    /**
     * check if the agent is satisfied
     * @return true if the agent is satsfied, else return false
     */
    @Override
    protected boolean checkObjective(){
        if (isPushed()){
            return false;
        }

        AgentBlock agent = this.handler.getAgentBelow(this);
        if (agent == null)
            return this.objectif == agent;
        return this.objectif == agent && agent.checkObjective();
    }
}
