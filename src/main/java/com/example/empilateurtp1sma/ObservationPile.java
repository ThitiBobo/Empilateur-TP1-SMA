package com.example.empilateurtp1sma;

public class ObservationPile extends Observation{
    protected int agentBeneathId;
    protected boolean isPushed;

    public boolean isPushed() {
        return isPushed;
    }

    public void setPushed(boolean pushed) {
        isPushed = pushed;
    }

    public ObservationPile(int agentBeneathId) {
        this.agentBeneathId = agentBeneathId;
    }

    public int getAgentBeneathId() {
        return agentBeneathId;
    }

    public void setAgentBeneathId(int agentBeneathId) {
        this.agentBeneathId = agentBeneathId;
    }
}
