package com.example.tasktracker;

public class SocialEvent extends Event {
    private String name;
    private int firstHour;
    private int lastHour;
    private String startAMPM;
    private String endAMPM;

    public SocialEvent(String name, int firstHour, int lastHour, String startAMPM, String endAMPM) {
        super(name, firstHour, lastHour, startAMPM, endAMPM);
        this.name = name;
        this.firstHour = firstHour;
        this.lastHour = lastHour;
        this.startAMPM = startAMPM;
        this.endAMPM = endAMPM;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getFirstHour() {
        return firstHour;
    }

    @Override
    public int getLastHour() {
        return lastHour;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setFirstHour(int firstHour) {

    }

    @Override
    public void setLastHour(int lastHour) {

    }

    @Override
    public String getStartAMPM() {
        return startAMPM;
    }

    @Override
    public String getEndAMPM() {
        return endAMPM;
    }

}