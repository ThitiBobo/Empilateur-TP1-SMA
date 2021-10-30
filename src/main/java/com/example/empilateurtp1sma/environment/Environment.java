package com.example.empilateurtp1sma.environment;

import com.example.empilateurtp1sma.agents.Agent;

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

    protected Report report = new Report();

    public Report getReport(){
        return report;
    }

    public void setAgentSatisfied(Agent agent){
        this.agentIsSatisfied.put(agent, true);
    }

    public void setAgentUnsatisfied(Agent agent){
        this.agentIsSatisfied.put(agent, false);
    }

    public void addAgent(Agent agent){
        agents.add(agent);
        agentIsSatisfied.put(agent, false);
        agent.setEnvironment(this);
    }

    public void addResource(Resource resource){
        ressources.add(resource);
    }

    abstract public void start();
    abstract public void stop();

    public boolean checkEndCondition(){
        return !this.agentIsSatisfied.containsValue(false);
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
