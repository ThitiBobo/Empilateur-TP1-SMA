package com.example.empilateurtp1sma;

public class Main {

    public static void main(String[] args) {
        System.out.println("Test");

        EnvironmentPile env = new EnvironmentPile();

        env.addAgent(new AgentBlock("A"));
        env.addAgent(new AgentBlock("B"));
        env.addAgent(new AgentBlock("C"));
        env.addAgent(new AgentBlock("D"));

        env.addResource(new Stack("1"));
        env.addResource(new Stack("2"));
        env.addResource(new Stack("3"));

        env.setAgent("A", "1");
        env.setAgent("B", "1");
        env.setAgent("C", "1");
        env.setAgent("D", "1");

        env.setObjectif("A", "B");
        env.setObjectif("B", "C");
        env.setObjectif("C", "D");
        env.setObjectif("D", null);

        System.out.println(env.display());

        env.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        env.stop();

        System.out.println(env.display());
    }

}
