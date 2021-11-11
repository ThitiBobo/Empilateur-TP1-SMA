package com.example.empilateurtp1sma;

import com.example.empilateurtp1sma.agents.AgentBlock2;
import com.example.empilateurtp1sma.environment.EnvironmentPile;
import com.example.empilateurtp1sma.environment.Report;
import com.example.empilateurtp1sma.environment.Stack;

import java.util.ArrayList;
import java.util.List;

public class Main3 {
    public static Report run() {

        EnvironmentPile env = new EnvironmentPile();

        env.addAgent(new AgentBlock2("A"));
        env.addAgent(new AgentBlock2("B"));
        env.addAgent(new AgentBlock2("C"));
        env.addAgent(new AgentBlock2("D"));

        env.addResource(new Stack("1"));
        env.addResource(new Stack("2"));
        env.addResource(new Stack("3"));

        env.setAgent("B", "1");
        env.setAgent("D", "1");
        env.setAgent("A", "1");
        env.setAgent("C", "1");

        env.setObjectif("D", "C");
        env.setObjectif("C", "B");
        env.setObjectif("B", "A");
        env.setObjectif("A", null);

        env.start(2000);
        env.stop();
        String fileName = env.getReport().export();


//        System.out.println("");
//        System.out.println("Objectif achieved: " + env.checkEndCondition());
//        System.out.println("number of movement: " + env.getReport().getCount());
//        System.out.println(env.display());
//        System.out.println("see more info in " + fileName);
//        env.getReport().setAgentIsSatisfied(env.getAgentIsSatisfied());
        return env.getReport();
    }

    public static void main(String[] args) {
        run();
    }
}
