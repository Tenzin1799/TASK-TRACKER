package com.example.tasktracker;

public interface Task {
    public String getName();
    public int getFirstHour();
    public int getLastHour();
    public void setName(String name);
    public void setFirstHour(int firstHour);
    public void setLastHour(int lastHour);
    public String getStartAMPM();
    public String getEndAMPM();
}
