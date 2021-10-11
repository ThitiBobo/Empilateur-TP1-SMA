package com.example.empilateurtp1sma;

import java.util.ArrayList;
import java.util.List;

public interface Environement {

    @SuppressWarnings("rawtypes")
    List<Agent> agents = new ArrayList();

    Agent pickAgent();
    
    void awakeAgent(Agent agent);

    void initialiseEnvironement();
}
