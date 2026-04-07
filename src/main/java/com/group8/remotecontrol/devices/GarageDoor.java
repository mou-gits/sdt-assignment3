package com.group8.remotecontrol.devices;

public class GarageDoor {
    private boolean isOpen = false;

    public void toggle() {
        isOpen = !isOpen;
        System.out.println("Garage Door is now " + (isOpen ? "OPEN" : "CLOSED"));
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setState(boolean state) {
        isOpen = state;
        System.out.println("Garage Door set to " + (isOpen ? "OPEN" : "CLOSED"));
    }
}
