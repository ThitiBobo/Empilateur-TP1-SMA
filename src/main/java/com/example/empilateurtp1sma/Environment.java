package com.example.empilateurtp1sma;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Objects;

public abstract class Environment implements PropertyChangeListener {

    protected List<Agent> agents = new ArrayList<>();
    protected List<Resource> ressources = new ArrayList<>();
    protected HashMap<Agent, Boolean> agentIsSatisfied = new HashMap<>();



    protected void addAgent(Agent agent){
        agents.add(agent);
        agentIsSatisfied.put(agent, false);
        agent.setEnvironment(this);
    }

    protected void addResource(Resource resource){
        ressources.add(resource);
    }

    abstract protected void initialiseEnvironment();
    abstract protected void start();
    abstract protected void stop();
    abstract public Observation observe(Agent agent);

    protected void checkEndCondition(){
        for (Boolean bool : agentIsSatisfied.values()) {
            if(!bool) return; //If at least one is not yet satisfied, we pursue the simulation
        }
        stop();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(Objects.equals(evt.getPropertyName(), "satisfaction")) {
            agentIsSatisfied.put((Agent) evt.getSource(), (Boolean) evt.getNewValue());
            if((Boolean) evt.getNewValue())
                checkEndCondition();
        }
    }
}
