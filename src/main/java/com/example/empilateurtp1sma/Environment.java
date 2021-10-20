package com.example.empilateurtp1sma;

import java.util.ArrayList;
import java.util.List;

public interface Environment {

    @SuppressWarnings("rawtypes")
    List<Agent> agents = new ArrayList();

    Agent pickAgent();
    
    void awakeAgent(Agent agent);

    void initialiseEnvironment();
}
