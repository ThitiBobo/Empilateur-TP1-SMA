package com.example.empilateurtp1sma;

public class AgentBlock extends Agent {

    private AgentBlock objectif;
    private AgentBlock agentBelow;
    private AgentBlock agentAbove;
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
//        EnvironmentPile env = (EnvironmentPile) environment;
//        Stack stack = env.getRandomStack();
//        boolean move_done = env.moveTo(this, stack);
//
//        if(!move_done){
//            env.agentPush(this);
//            waitingForBlockToMove = true;
//            enterSleepMode();
//        }
    }


    @Override
    protected void init() {
        System.out.println("Bot:" + tag + " started");
    }

    @Override
    protected void execute() throws InterruptedException {
        //Thread.sleep(0);
        //TODO pas compris la classe Observation
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }

        this.checkObjective();
    }

    @Override
    protected void terminate() {
        System.out.println("Bot:" + tag + " stopped");
    }

    private boolean checkObjective(){
        return false;
    }

    @Override
    protected Object checkObjective(Observation observation) {
        ObservationPile observationPile = (ObservationPile) observation;
        // TODO revoir le code, j'y ai touché, sry
        return ((AgentBlock) observationPile.getAgentBeneath()).getId() == objectif.getId() && !observationPile.isPushed();
    }


}
