package com.group8.remotecontrol.devices;

public class AllLightsController {
    private InsideLight inside;
    private OutsideLight outside;

    public AllLightsController(InsideLight inside, OutsideLight outside) {
        this.inside = inside;
        this.outside = outside;
    }

    public void toggleGroup() {
        boolean target = !(inside.isOn() && outside.isOn());
        inside.setState(target);
        outside.setState(target);
        System.out.println("All Lights toggled to " + (target ? "ON" : "OFF"));
    }

    public boolean[] getStates() {
        return new boolean[]{inside.isOn(), outside.isOn()};
    }

    public void restoreStates(boolean insideState, boolean outsideState) {
        inside.setState(insideState);
        outside.setState(outsideState);
    }
}

