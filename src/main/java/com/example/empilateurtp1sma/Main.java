package com.example.empilateurtp1sma;

import com.example.empilateurtp1sma.agents.AgentBlock;
import com.example.empilateurtp1sma.environment.EnvironmentPile;
import com.example.empilateurtp1sma.environment.Report;
import com.example.empilateurtp1sma.environment.Stack;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static Report run(){
        EnvironmentPile env = new EnvironmentPile();

        env.addAgent(new AgentBlock("A"));
        env.addAgent(new AgentBlock("B"));
        env.addAgent(new AgentBlock("C"));
        env.addAgent(new AgentBlock("D"));

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

        env.getReport().setAgentIsSatisfied(env.getAgentIsSatisfied());
        return env.getReport();
    }

    public static void main(String[] args) {
        Report report = run();

        System.out.println(report.getInitialState());
        System.out.println(report.getFinalState());

        System.out.println("Objectif atteint      : " + report.checkEndCondition());
        System.out.println("Nombre de déplacement : " + report.getCount());
    }

}
