package com.example.empilateurtp1sma;

import org.springframework.cglib.core.Block;

public class Bloc extends Agent {

    private int id;
    private Block objectif;

    public Bloc(int id){
        this.id = id;
    }

    public void setObjectif(Block objectif){
        this.objectif = objectif;
    }

    public void move(){
        return;
    }

    public void push(){
        return;
    }
}
