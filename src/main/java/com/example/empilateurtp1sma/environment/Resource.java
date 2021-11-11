package com.example.empilateurtp1sma.environment;

public abstract class Resource {

    protected String tag;

    public String getTag(){
        return tag;
    }

    public Resource(String tag){
        this.tag = tag;
    }
}
