package com.example.tasktracker;

import java.util.ArrayList;

public class Day{
    // Day comment
    private String name;
    private ArrayList<Event> events = new ArrayList<>();

    public Day(String name){
        this.name = name;
    }

    public ArrayList<Event> getEvents(){
        return events;
    }
    public String getName(){
        return name;
    }
}
