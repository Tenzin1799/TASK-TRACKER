package com.example.tasktracker;

import java.util.Comparator;

public abstract class Event implements Task {
    private String name;
    private int firstHour;
    private int lastHour;
    private String startAMPM;
    private String endAMPM;

    public Event(String name, int firstHour, int lastHour, String startAMPM, String endAMPM){
        this.name = name;
        this.firstHour = firstHour;
        this.lastHour = lastHour;
        this.startAMPM = startAMPM;
        this.endAMPM = endAMPM;
    }

    @Override
    public String toString() {
        return name + " (" + firstHour + startAMPM + "-" + lastHour + endAMPM + ")";
    }
}

// Comparator class to use Colelctions.sort()
class EventComparator implements Comparator<Event> {
    @Override
    public int compare(Event n1, Event n2){
        int n1FirstHour = n1.getFirstHour();
        int n2FirstHour = n2.getFirstHour();
        if (n1.getStartAMPM().equals("PM")){
            n1FirstHour += 12;
        }
        if (n2.getStartAMPM().equals("PM")){
            n2FirstHour += 12;
        }
        if (n1FirstHour == n2FirstHour){
            return 0;
        }
        if (n1FirstHour > n2FirstHour){
            return 1;
        }
        else {
            return -1;
        }
    }
}