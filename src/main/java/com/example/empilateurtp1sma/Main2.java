package com.example.empilateurtp1sma;

import com.example.empilateurtp1sma.agents.AgentBlock1;
import com.example.empilateurtp1sma.environment.EnvironmentPile;
import com.example.empilateurtp1sma.environment.Report;
import com.example.empilateurtp1sma.environment.Stack;

import java.util.ArrayList;
import java.util.List;

public class Main2 {
    public static Report run() {

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

        env.start(2000);
        env.stop();
        String fileName = env.getReport().export();

        return env.getReport();
    }

    public static void main(String[] args) {
        run();

    }
}
