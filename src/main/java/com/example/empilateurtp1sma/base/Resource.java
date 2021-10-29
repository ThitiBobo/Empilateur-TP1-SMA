package com.example.empilateurtp1sma.base;

import java.util.concurrent.Semaphore;

public abstract class Resource {

    protected String tag;

    public String getTag(){
        return tag;
    }

    public Resource(String tag){
        this.tag = tag;
    }
}
