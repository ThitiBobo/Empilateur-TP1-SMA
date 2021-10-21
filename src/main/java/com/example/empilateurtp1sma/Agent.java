package com.example.empilateurtp1sma;

import java.beans.PropertyChangeSupport;

public abstract class Agent extends Thread{

    protected String tag;
    protected Environment environment;
    private final PropertyChangeSupport support;

    public String getTag(){
        return tag;
    }

    public Agent(String tag) {
        this.tag = tag;
        support = new PropertyChangeSupport(this);
    }

    public void setEnvironment(Environment newEnvironment){
        environment = newEnvironment;
        support.addPropertyChangeListener(newEnvironment);
    }

    public void run(){
        init();
        while (true){
            try{
                execute();

                //System.out.println("running");
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                break;
            }
        }
        terminate();
    }

    protected abstract void init();
    protected abstract void execute() throws InterruptedException;
    protected abstract void terminate();

    abstract protected Object checkObjective(Observation observation);

    public void enterSleepMode(){} // TODO
    public void quitSleepMode(){} // TODO


    protected boolean updateSatisfaction(boolean satisfaction){
        support.firePropertyChange("satisfaction", null, satisfaction);
        return satisfaction;
    }
}
