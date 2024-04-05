package com.example.tasktracker;

public final class TestDataSingleton {
    private String day;
    private String eventName;
    private String color;
    private int startHour;
    private int lastHour;
    private final static TestDataSingleton INSTANCE = new TestDataSingleton();

    private TestDataSingleton() {}

    public static TestDataSingleton getInstance(){
        return INSTANCE;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getLastHour() {
        return lastHour;
    }

    public void setLastHour(int lastHour) {
        this.lastHour = lastHour;
    }

    public void setDay(String day){
        this.day = day;
    }

    public String getDay(){
        return this.day;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

