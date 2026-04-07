package com.group8.remotecontrol.command;

import com.group8.remotecontrol.devices.OutsideLight;

public class ToggleOutsideLightCommand implements Command {
    private OutsideLight light;
    private boolean prevState;

    public ToggleOutsideLightCommand(OutsideLight light) {
        this.light = light;
    }

    @Override
    public void execute() {
        prevState = light.isOn();
        light.toggle();
    }

    @Override
    public void undo() {
        light.setState(prevState);
    }
}
