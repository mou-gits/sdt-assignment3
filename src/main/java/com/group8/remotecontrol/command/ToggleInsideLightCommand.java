package com.group8.remotecontrol.command;

import com.group8.remotecontrol.devices.InsideLight;

public class ToggleInsideLightCommand implements Command {
    private InsideLight light;
    private boolean prevState;

    public ToggleInsideLightCommand(InsideLight light) {
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
