package com.example.empilateurtp1sma;

public class EnvironmentPile extends Environment {

    private StackHandler handler = new StackHandler();

    public StackHandler getHandler() {
        return handler;
    }

    @Override
    protected void addResource(Resource resource){
        super.addResource(resource);
        if (resource instanceof Stack){
            handler.add((Stack)resource);
        }
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

    public void setAgent(String a, String s){
        AgentBlock agent = findAgent(a);
        Stack stack = findRessource(s);
        stack.push(agent);
    }

    public void setObjectif(String sourceString, String targetString){
        AgentBlock agent = findAgent(sourceString);
        agent.setObjectif(targetString);
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


    public String display(){
        return handler.display();
    }

    @Override
    public Observation observe(Agent agent) {
        AgentBlock agentBeneath = (AgentBlock) stackHandler.getAgentBeneath(agent);
        ObservationPile observation = new ObservationPile(agentBeneath.getId());
        observation.setPushed(agentBeneath.isWaitingForBlockToMove());
        return observation;
    }

    protected Stack getRandomStack(){
        return stackHandler.getRandomStack();
    }

    protected boolean moveTo(Agent agent, Stack destination){
        return stackHandler.move(agent, destination);
    }

    protected void agentPush(Agent agent){
        ((AgentBlock) stackHandler.getAgentAbove(agent)).quitSleepMode();
    }

    // TODO
    public AgentBlock getAgentBelow(AgentBlock agentBlock){
        return null;
    }

    // TODO
    public AgentBlock getAgentAbove(AgentBlock agentBlock){
        return null;
    }
}