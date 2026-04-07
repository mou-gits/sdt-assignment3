package com.group8.remotecontrol.devices;

public class AirConditioner {
    private boolean isOn = false;

    public void toggle() {
        isOn = !isOn;
        System.out.println("Air Conditioner is now " + (isOn ? "ON" : "OFF"));
    }

    public boolean isOn() {
        return isOn;
    }

    public void setState(boolean state) {
        isOn = state;
        System.out.println("Air Conditioner set to " + (isOn ? "ON" : "OFF"));
    }
}