package com.example.empilateurtp1sma;

public class Block extends AgentBinary {

    private final int id;
    private int objectifId;
    private boolean waitingForBlockToMove = false;

    public Block(int id){
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

    @Override
    protected Object checkObjective(Observation observation) {
        ObservationPile observationPile = (ObservationPile) observation;
        return observationPile.getAgentBeneathId() == objectifId && !observationPile.isPushed();
    }

    public void move(){
        EnvironmentPile env = (EnvironmentPile) environment;
        Stack stack = env.getRandomStack();
        boolean move_done = env.moveTo(this, stack);

        if(!move_done){
            env.agentPush(this);
            waitingForBlockToMove = true;
            enterSleepMode();
        }
    }
}
