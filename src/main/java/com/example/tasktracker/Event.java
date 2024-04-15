package com.example.tasktracker;


public class Event {
    private String day;
    private String name;
    private int startTime;
    private int endTime;
    private String color;
    private boolean isCompleted;

    public Event(String day, String name, String color, int firstHour, int lastHour, boolean isCompleted){
        this.day = day;
        this.name = name;
        this.color = color;
        this.startTime = firstHour;
        this.endTime = lastHour;
        this.isCompleted = isCompleted;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int firstHour) {
        this.startTime = firstHour;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int lastHour) {
        this.endTime = lastHour;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}