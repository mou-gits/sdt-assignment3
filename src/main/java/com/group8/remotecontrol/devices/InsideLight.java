package com.group8.remotecontrol.devices;

public class InsideLight {
    private boolean isOn = false;

    public void toggle() {
        isOn = !isOn;
        System.out.println("Inside Light is now " + (isOn ? "ON" : "OFF"));
    }

    public boolean isOn() {
        return isOn;
    }

    public void setState(boolean state) {
        isOn = state;
        System.out.println("Inside Light set to " + (isOn ? "ON" : "OFF"));
    }
}
