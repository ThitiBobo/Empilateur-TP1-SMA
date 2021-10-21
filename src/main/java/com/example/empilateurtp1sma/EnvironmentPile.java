package com.example.empilateurtp1sma;

public class EnvironmentPile extends Environment {

    private StackHandler handler = new StackHandler();

    @Override
    protected void addResource(Resource resource){
        super.addResource(resource);
        if (resource instanceof Stack){
            handler.add((Stack)resource);
        }
    }

    @Override
    protected void initialiseEnvironment() {
    }

    @Override
    protected void start() {
        this.agents.forEach(Thread::start);
    }

    @Override
    protected void stop() {
        this.agents.forEach(Thread::interrupt);
    }

    @Override
    public Observation observe(Agent agent) {
        return null;
    }

    public void setAgent(String a, String s){
        AgentBlock agent = findAgent(a);
        Stack stack = findRessource(s);
        stack.push(agent);
    }

    public void setObjectif(String sourceString, String targetString){
        AgentBlock agent = findAgent(sourceString);
        AgentBlock target = findAgent(targetString);
        agent.setObjectif(target);
    }

    public AgentBlock findAgent(String agentString){

        return (AgentBlock) this.agents.stream()
                .filter(agent -> agent.getTag().equals(agentString))
                .findFirst()
                .orElse(null);
    }

    public Stack findRessource(String stackString){
        return (Stack) this.ressources.stream()
                .filter( s -> stackString.equals(s.getTag()))
                .findFirst()
                .orElse(null);
    }

    public String display(){
        return handler.display();
    }
}