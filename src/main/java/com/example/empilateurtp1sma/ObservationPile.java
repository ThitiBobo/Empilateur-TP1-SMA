package com.example.empilateurtp1sma;

public class ObservationPile extends Observation{
    protected Agent agentBeneath;
    protected boolean isPushed;

    public boolean isPushed() {
        return isPushed;
    }

    public void setPushed(boolean pushed) {
        isPushed = pushed;
    }

    public ObservationPile(Agent agentBeneath) {
        this.agentBeneath = agentBeneath;
    }

    public Agent getAgentBeneath() {
        return agentBeneath;
    }

    public void setAgentBeneath(Agent agentBeneath) {
        this.agentBeneath = agentBeneath;
    }
}
