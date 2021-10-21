package com.example.empilateurtp1sma;

// Binary Agents are agents that are either fully satisfied or not at all.
// Once their requirements are fullfilled, they enter sleep mode and won't move anymore.
// If their surrondings change, the environment will wake them up to double check they are still satisfied

public abstract class AgentBinary extends Agent{

    public AgentBinary(String tag) {
        super(tag);
    }

    public void run(){
        Observation observation = environment.observe(this);
        if(updateSatisfaction((boolean) checkObjective(observation))) {
            enterSleepMode();
        }
        else {
            // TODO problème de logique d'implémentation, la classe agent à une méthode
            // move, pourtant tous les agents ne réalise pas des actions move()
            // move();
        }
    }
}
