package com.example.empilateurtp1sma;

public class Block extends Agent {

    private int id;
    private Block objectif;

    public Block(int id){
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
