package com.example.empilateurtp1sma;

public class AgentBlock extends AgentBinary {

    private final int id;
    private int objectifId;
    private boolean waitingForBlockToMove = false;
    private StackHandler handler;

    public AgentBlock(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }

    public boolean isWaitingForBlockToMove() {
        return waitingForBlockToMove;
    }

    public void setObjectif(int objectif){
        this.objectifId = objectif;
    }

    public void setStackHandler(StackHandler handler){
        this.handler = handler;
    }

    public void move() throws InterruptedException {

        Stack origin = handler.find(this);
        Stack target = handler.acquireStack(this);

        try {
            origin.pop(this);
            target.push(this);
            //System.out.println(((EnvironmentPile) this.environment).display());
        } catch (Exception e) {
            e.printStackTrace();
        }
        handler.releaseStack(this);
    }

    public void push(AgentBlock agent){

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
                push(agentAbove);
                // attends le signal pour bouger à nouveau
            }
        }
    }

    @Override
    protected void terminate() {
        System.out.println("Bot:" + tag + " stopped");
    }

    private boolean checkObjective(){
        return this.handler.getAgentBelow(this) == this.objectif;
    }
}
