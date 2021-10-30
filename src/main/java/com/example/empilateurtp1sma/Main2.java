package com.example.empilateurtp1sma;

import com.example.empilateurtp1sma.agents.AgentBlock1;
import com.example.empilateurtp1sma.environment.EnvironmentPile;
import com.example.empilateurtp1sma.environment.Stack;

public class Main2 {
    public static void main(String[] args) {

        EnvironmentPile env = new EnvironmentPile();

        env.addAgent(new AgentBlock1("A"));
        env.addAgent(new AgentBlock1("B"));
        env.addAgent(new AgentBlock1("C"));
        env.addAgent(new AgentBlock1("D"));

        env.addResource(new Stack("1"));
        env.addResource(new Stack("2"));
        env.addResource(new Stack("3"));

        env.setAgent("A", "1");
        env.setAgent("B", "1");
        env.setAgent("C", "1");
        env.setAgent("D", "1");

        env.setObjectif("D", "A");
        env.setObjectif("A", "C");
        env.setObjectif("C", "B");
        env.setObjectif("B", null);

        System.out.println(env.display());

        env.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        env.stop();
        String fileName = env.getReport().export();


        System.out.println("");
        System.out.println("Objectif achieved: " + env.checkEndCondition());
        System.out.println("number of movement: " + env.getReport().getCount());
        System.out.println(env.display());
        System.out.println("see more info in " + fileName);
    }
}
