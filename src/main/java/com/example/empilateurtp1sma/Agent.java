package com.example.empilateurtp1sma;

import java.beans.PropertyChangeSupport;

public abstract class Agent {
    protected Environment environment;
    private final PropertyChangeSupport support;

    public Agent() {
        support = new PropertyChangeSupport(this);
    }

    public void setEnvironement(Environment new_environement){
        environment = new_environement;
        support.addPropertyChangeListener(new_environement);
    }

    abstract public void run();

    public void enterSleepMode(){} // TODO
    public void quitSleepMode(){} // TODO

    abstract protected Object checkObjective(Observation observation);

    abstract protected void move();

    protected boolean updateSatisfaction(boolean satisfaction){
        support.firePropertyChange("satisfaction", null, satisfaction);
        return satisfaction;
    }
}
