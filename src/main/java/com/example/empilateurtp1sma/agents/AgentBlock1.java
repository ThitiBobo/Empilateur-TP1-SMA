package com.example.empilateurtp1sma.agents;

/**
 * this agent block behaves like a simple {@code AgentBlock}, but proposes as an additional strategy,
 * to check after its movements if it is satisfied as well as to verify if all the agents below are satisfied
 */
public class AgentBlock1 extends AgentBlock{

    /**
     * instantiate a block agent with the specified tag
     * @param tag, tag
     */
    public AgentBlock1(String tag) {
        super(tag);
    }

    /**
     * check if the agent is satisfied
     * @return true if the agent is satsfied, else return false
     */
    @Override
    protected boolean checkObjective(){
        boolean objectif = super.checkObjective();
        AgentBlock agent = this.handler.getAgentBelow(this);
        if (agent == null)
            return objectif;
        return objectif && agent.checkObjective();
    }

}
