package com.group8.remotecontrol.command;

import com.group8.remotecontrol.devices.AllLightsController;

public class ToggleAllLightsCommand implements Command {
    private AllLightsController controller;
    private boolean prevInside;
    private boolean prevOutside;

    public ToggleAllLightsCommand(AllLightsController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        boolean[] states = controller.getStates();
        prevInside = states[0];
        prevOutside = states[1];
        controller.toggleGroup();
    }

    @Override
    public void undo() {
        controller.restoreStates(prevInside, prevOutside);
    }
}

