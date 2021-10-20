package com.example.empilateurtp1sma;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Objects;

public abstract class Environment implements PropertyChangeListener {

    @SuppressWarnings("rawtypes")
    protected List<Agent> agents = new ArrayList();
    protected HashMap<Agent, Boolean> agentIsSatisfied = new HashMap<Agent, Boolean>();

    protected void wireAgent(Agent agent){
        agents.add(agent);
        agentIsSatisfied.put(agent, false);
        agent.setEnvironement(this);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(Objects.equals(evt.getPropertyName(), "satisfaction")) {
            agentIsSatisfied.put((Agent) evt.getSource(), (Boolean) evt.getNewValue());
            if((Boolean) evt.getNewValue())
                checkEndCondition();
        }
    }

    abstract protected void initialiseEnvironement();
    abstract protected void startSimulation();
    abstract protected void stopSimulation();
    abstract public Observation observe(Agent agent);

    protected void checkEndCondition(){
        for (Boolean bool : agentIsSatisfied.values()) {
            if(!bool) return; //If at least one is not yet satisfied, we pursue the simulation
        }

        stopSimulation();
    }

}
