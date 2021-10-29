package com.example.empilateurtp1sma;

/**
 * Represents a stack that can contain Agents
 */
public class Stack extends Resource{

    /**
     * Agent stack
     */
    private java.util.Stack<Agent> stack;

    public Stack(String tag){
        super(tag);
        this.stack = new java.util.Stack<>();
    }

    /**
     * Pushes an {@code Agent} onto the top of this stack.
     * @param agent, an {@code Agent} to pushes
     */
    public void push(Agent agent){
        this.stack.push(agent);
    }

    /**
     * Removes the Agent at the top of this stack, checks if the input agent is at the top of the stack
     * @param agent to check
     * @throws Exception
     */
    public void pop(Agent agent) throws Exception {
        if (this.stack.peek() != agent)
            throw new Exception("The moving agent is not at the top of the stack");
        this.stack.pop();

    }

    /**
     * Returns true if this stack contains the specified Agent.
     * More formally, returns true if and only if this Stack contains at least
     * one Agent a such that Objects.equals(agent, a)
     * @param agent agent whose presence in this Stack is to be tested
     * @return a boolean
     */
    public boolean contains(Agent agent){
        return this.stack.contains(agent);
    }

    /**
     * Used to return the agent above the specified agent
     * Mote precisely, return the agent above or null if the specified agent is already at the top of the stack
     * @param agent
     * @return agent above
     * @throws Exception, throw exception if the specified agent is not in the stack
     */
    public Agent getAgentBelow(Agent agent) throws Exception {
        if (!contains(agent)) throw new Exception("the Agent is not in the Stack");
        int index = stack.indexOf(agent);
        try{
            return stack.elementAt(index - 1);
        }catch (IndexOutOfBoundsException e){
            return null;
        }
    }

    /**
     * Used to return the agent below the specified agent
     * Mote precisely, return the agent below or null if the specified agent is already at the bottom of the stack
     * @param agent
     * @return agent below
     * @throws Exception, throw exception if the specified agent is not in the stack
     */
    public Agent getAgentAbove(Agent agent) throws Exception {
        if (!contains(agent)) throw new Exception("the Agent is not in the Stack");
        int index = stack.indexOf(agent);
        try{
            return stack.elementAt(index + 1);
        }catch (IndexOutOfBoundsException e){
            return null;
        }
    }

    @Override
    public String toString() {
        return "Stack{" +
                "tag='" + tag + '\'' +
                ", stack=" + stack +
                '}';
    }

    /**
     * returns a String, representation of the stack with a neat display
     * @return a String
     */
    public String display(){
        StringBuilder builder = new StringBuilder();
        builder.append("stack:" + this.tag);
        builder.append(" [ ");
        this.stack.forEach( agent -> builder.append(agent.getTag() + " "));
        builder.append("]");
        return builder.toString();
    }
}

