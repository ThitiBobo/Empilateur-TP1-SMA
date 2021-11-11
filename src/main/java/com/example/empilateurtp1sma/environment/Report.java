package com.example.empilateurtp1sma.environment;

import com.example.empilateurtp1sma.agents.Agent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class Report {

    public static final String folderPath = "./doc/reports";

    private int count;
    private List<String> states;
    private Map<String, List<String>> actionList;
    private Map<String, String> infos;

    protected HashMap<Agent, Boolean> agentIsSatisfied = new HashMap<>();

    public Report(){
        this.count = 0;
        this.states = new ArrayList<>();
        this.actionList = new HashMap<>();
        this.infos = new HashMap<>();
        this.agentIsSatisfied = new HashMap<>();
    }

    public void increment(){
        this.count++;
    }

    public void decrement(){
        this.count--;
    }

    public int getCount() {
        return count;
    }

    public void addState(String state){
        this.states.add(state);
    }

    public void setAgentIsSatisfied(HashMap map){
        this.agentIsSatisfied = map;
    }

    public void addAction(String type, String action){
        if (!this.actionList.containsKey(type)){
            this.actionList.put(type, new ArrayList<>());
        }
        this.actionList.get(type).add(action);
    }

    public void addInfo(String infoName, String info){
        if (!this.infos.containsKey(infoName)){
            this.infos.put(infoName, info);
        }else {
            this.infos.replace(infoName, info);
        }
    }

    public boolean checkEndCondition(){
        return !this.agentIsSatisfied.containsValue(false);
    }

    public String export(){
        Timestamp timestamp = new Timestamp(new Date().getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fileName = "report-" + sdf.format(timestamp) + ".md";

        File directory = new File(folderPath);
        if (! directory.exists()){
            directory.mkdir();
        }

        try {
            File file = new File(folderPath + "/" + fileName);
            file.createNewFile(); // if file already exists will do nothing
            BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsolutePath(), true));
            write(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return folderPath + "/" + fileName;
    }

    private void write(BufferedWriter writer) throws IOException {
        writer.append("# REPORT").append("  \n");
        writer.append("  \n");
        writer.append("## Statistiques").append("  \n");
        writer.append("  \n");
        writer.append("number of movement : ").append(String.valueOf(this.count)).append("\n");

        writer.append("## STATES ").append("  \n");
        writer.append("```").append("  \n");
        this.states.forEach(s ->{
            try {
                writer.append(s).append("  \n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.append("```").append("  \n");


        this.actionList.forEach((key, value) ->{
            try {
                writer.append("## ACTION " + key).append("  \n");
                writer.append("```").append("  \n");
                value.forEach( v -> {
                    try {
                        writer.append(v).append("  \n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                writer.append("```").append("  \n");
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        writer.close();
    }
}
