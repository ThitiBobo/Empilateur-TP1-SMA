package com.example.empilateurtp1sma.agents;

import com.example.empilateurtp1sma.environment.EnvironmentPile;
import com.example.empilateurtp1sma.environment.Stack;
import com.example.empilateurtp1sma.environment.StackHandler;

public class AgentBlock extends Agent {

    protected AgentBlock objectif;
    protected boolean pushed = false;
    protected StackHandler handler;

    public AgentBlock(String tag){
        super(tag);
        this.pushed = false;
    }
    public int getId(){
        return id;
    }

    public boolean isPushed() {
        return pushed;
    }

    public void setObjectif(int objectif){
        this.objectifId = objectif;
    }

    public void setStackHandler(StackHandler handler){
        this.handler = handler;
    }

    protected void move() throws InterruptedException {

        Stack origin = handler.find(this);
        Stack target = handler.acquireStack(this);

        try {
            origin.pop(this);
            target.push(this);
            this.environment.getReport().addState(((EnvironmentPile) this.environment).display());
            this.environment.getReport().addAction(
                    "MOVE",
                    tag + ": " + origin.getTag() + " > " + target.getTag());
            this.environment.getReport().increment();
        } catch (Exception e) {
            //System.out.println("BOT:" + tag + " had a problem to move");
        }
        handler.releaseStack(this);
    }

    protected void push(){
        this.pushed = true;
        this.environment.setAgentUnsatisfied(this);
    }


    @Override
    protected void init() {
        //System.out.println("BOT:" + tag + " started");
    }

    @Override
    protected void execute() throws InterruptedException {

        StackHandler handler = ((EnvironmentPile) environment).getHandler();

        if (this.checkObjective()) {
            this.environment.setAgentSatisfied(this);
        } else{
            AgentBlock agentAbove = this.handler.getAgentAbove(this);
            if (agentAbove == null) {
                move();
            } else {
                agentAbove.push();
                this.environment.getReport().addAction("PUSH", tag + " push " + agentAbove.tag);
            }
        }
    }

    @Override
    protected void terminate() {
        //System.out.println("BOT:" + tag + " stopped");
    }

    protected boolean checkObjective(){
        if (isPushed()){
            this.pushed = false;
            return false;
        }
        return this.handler.getAgentBelow(this) == this.objectif;
    }
}
