package com.group8.remotecontrol.devices;

public class OutsideLight {
    private boolean isOn = false;

    public void toggle() {
        isOn = !isOn;
        System.out.println("Outside Light is now " + (isOn ? "ON" : "OFF"));
    }

    public boolean isOn() {
        return isOn;
    }

    public void setState(boolean state) {
        isOn = state;
        System.out.println("Outside Light set to " + (isOn ? "ON" : "OFF"));
    }
}
