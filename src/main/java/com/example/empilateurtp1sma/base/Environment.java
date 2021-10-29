package com.example.empilateurtp1sma.base;

import com.example.empilateurtp1sma.Report;
import com.example.empilateurtp1sma.base.Agent;
import com.example.empilateurtp1sma.base.Resource;

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

    protected boolean checkEndCondition(){
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
