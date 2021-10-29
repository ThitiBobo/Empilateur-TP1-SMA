package com.example.empilateurtp1sma;

import com.example.empilateurtp1sma.base.Agent;

public class AgentBlock extends Agent {

    private AgentBlock objectif;
    private boolean pushed = false;
    private StackHandler handler;

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

    private void move() throws InterruptedException {

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
            e.printStackTrace();
        }
        handler.releaseStack(this);
    }

    private void push(){
        this.pushed = true;
    }


    @Override
    protected void init() {
        System.out.println("Bot:" + tag + " started");
    }

    @Override
    protected void execute() throws InterruptedException {

        StackHandler handler = ((EnvironmentPile) environment).getHandler();

        // TODO à améliorer
        if (this.checkObjective()) {
            // TODO
        } else{
            AgentBlock agentAbove = this.handler.getAgentAbove(this);
            if (agentAbove == null) {
                move();
                // envoie un signal
            } else {
                agentAbove.push();
                this.environment.getReport().addAction("PUSH", tag + " push " + agentAbove.tag);
                // attends le signal pour bouger à nouveau
            }
        }
    }

    @Override
    protected void terminate() {
        System.out.println("Bot:" + tag + " stopped");
    }

    private boolean checkObjective(){
        if (isPushed()){
            this.pushed = false;
            return false;
        }
        return this.handler.getAgentBelow(this) == this.objectif;
    }
}
