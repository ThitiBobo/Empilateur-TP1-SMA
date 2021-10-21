package com.example.empilateurtp1sma;

public class EnvironmentPile extends Environment {

    protected Stack[] blockStacks;
    protected Block[] blocs;
    protected StackHandler stackHandler;

    @Override
    protected void initialiseEnvironement() {
        blockStacks = new Stack[]{
                new Stack(),
                new Stack(),
                new Stack()
        };
        stackHandler = new StackHandler(blockStacks);

        blocs = new Block[]{
                new Block(1),
                new Block(2),
                new Block(3),
                new Block(4)
        };

        // For each blocs, starting at SECOND, set objective to the previous one
        // (the first will have no objective (thus it is bottom))
        for(var i = 1; i < blocs.length; i++){
            blocs[i].setObjectif(blocs[i-1].getId());
        }

    }

    @Override
    protected void startSimulation() {
        for(var i = 1; i < blocs.length; i++){
            // blocs[i].run(); // THREAD MODE
        }
    }

    @Override
    protected void stopSimulation() {
        for(var i = 1; i < blocs.length; i++){
            // blocs[i].stop(); // THREAD MODE
        }
    }

    @Override
    public Observation observe(Agent agent) {
        Block agentBeneath = (Block) stackHandler.getAgentBeneath(agent);
        ObservationPile observation = new ObservationPile(agentBeneath.getId());
        observation.setPushed(agentBeneath.isWaitingForBlockToMove());
        return observation;
    }

    protected Stack getRandomStack(){
        return stackHandler.getRandomStack();
    }

    protected boolean moveTo(Agent agent, Stack destination){
        return stackHandler.move(agent, destination);
    }

    protected void agentPush(Agent agent){
        ((Block) stackHandler.getAgentAbove(agent)).quitSleepMode();
    }
}
