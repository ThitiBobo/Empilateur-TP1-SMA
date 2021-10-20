package com.example.empilateurtp1sma;

// Binary Agents are agents that are either fully satisfied or not at all.
// Once their requirements are fullfilled, they enter sleep mode and won't move anymore.
// If their surrondings change, the environment will wake them up to double check they are still satisfied

public abstract class AgentBinary extends Agent{

    public void run(){
        Observation observation = environment.observe(this);
        if(updateSatisfaction((boolean) checkObjective(observation))) {
            enterSleepMode();
        }
        else {
            move();
        }
    }
}
