package com.example.empilateurtp1sma;

public class EnvironmentPile implements Environment {

    protected Stack[] blockStacks = {new Stack(), new Stack(), new Stack()};

    @Override
    public Agent pickAgent() {
        return null;
    }

    @Override
    public void awakeAgent(Agent agent) {

    }

    @Override
    public void initialiseEnvironment() {
        Block[] blocs = {
            new Block(1),
            new Block(2),
            new Block(3),
            new Block(4)
        };

        // For each blocs, starting at SECOND, set objective to the previous one
        // (the first will have no objective (thus it is bottom))
        for(var i = 1; i < blocs.length; i++){
            blocs[i].setObjectif(blocs[i-1]);
        }
    }
}
