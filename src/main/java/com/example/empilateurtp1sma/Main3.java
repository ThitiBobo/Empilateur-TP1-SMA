package com.example.empilateurtp1sma;

import com.example.empilateurtp1sma.agents.AgentBlock2;
import com.example.empilateurtp1sma.environment.EnvironmentPile;
import com.example.empilateurtp1sma.environment.Stack;

import java.util.ArrayList;
import java.util.List;

public class Main3 {
    public static int run() {

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

        return env.getReport().getCount();
    }

    public static void main(String[] args) {

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < 100; i++){
            System.out.println("RUN: " + i);
            res.add(run());
        }

        res.forEach(r -> System.out.println(r));

    }
}
