package com.example.empilateurtp1sma;

public class AgentBlock extends Agent {

    private AgentBlock objectif;
    private boolean waitingForBlockToMove = false;

    public AgentBlock(String tag){
        super(tag);
    }

    public boolean isWaitingForBlockToMove() {
        return waitingForBlockToMove;
    }

    public void setObjectif(AgentBlock objectif){
        this.objectif = objectif;
    }

    public void move(){

        StackHandler handler = ((EnvironmentPile) environment).getHandler();

        Stack origin = handler.find(this);
        Stack target = handler.acquireStack(this);

        try {
            origin.pop(this);
            target.push(this);
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
        // TODO à améliorer
        if (this.checkObjective()) {
            // écoute
        } else{
            AgentBlock agentAbove = ((EnvironmentPile) this.environment).getAgentAbove(this);
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
        return ((EnvironmentPile) this.environment).getAgentBelow(this) == this.objectif;
    }

    @Override
    protected Object checkObjective(Observation observation) {
        ObservationPile observationPile = (ObservationPile) observation;
        // TODO revoir le code, j'y ai touché, sry
        return ((AgentBlock) observationPile.getAgentBeneath()).getId() == objectif.getId() && !observationPile.isPushed();
    }


}
