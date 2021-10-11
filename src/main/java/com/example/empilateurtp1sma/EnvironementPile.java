package com.example.empilateurtp1sma;

import java.util.ArrayList;
import java.util.List;

public class EnvironementPile implements Environement{

    protected Pile[] piles = {new Pile(), new Pile(), new Pile()};

    @Override
    public Agent pickAgent() {
        return null;
    }

    @Override
    public void awakeAgent(Agent agent) {

    }

    @Override
    public void initialiseEnvironement() {
        Bloc[] blocs = {
            new Bloc(1),
            new Bloc(2),
            new Bloc(3),
            new Bloc(4)
        };

        // For each blocs, starting at SECOND, set objective to the previous one
        // (the first will have no objective (thus it is bottom))
        for(var i = 1; i < blocs.length; i++){
            blocs[i].setObjectif(blocs[i-1]);
        }

        for(var i = 0; i < blocs.length; i++){
            piles[0].add((Agent) blocs[i]);
        }
    }
}
