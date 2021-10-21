package com.example.empilateurtp1sma;

import java.util.ArrayList;
import java.util.List;

public class StackHandler {

    private List<Stack> stacks = new ArrayList<>();

    public StackHandler(Stack[] blockStacks) {
        for(var i = 0; i < blockStacks.length; i++){
            stacks.add(blockStacks[i]);
        }
    }


    public boolean move(Agent agent, Stack destination){

        // cherche l'agent
        // vérifie si l'agent est en haut de sa pile
        // essaye de bloquer sa pile
        // essaye de bloquer la pile cible

        // retourne vrai si le déplacement a pu se faire
        return true;  // TODO
    }

    public Stack find(Agent agent){
        return null;  // TODO
    }

    public Stack getRandomStack(){
        return null;  // TODO
    }

    public Object getAgentAbove(Agent agent) {
        return null; // TODO
    }
    public Object getAgentBeneath(Agent agent) {
        return null; // TODO
    }
}
