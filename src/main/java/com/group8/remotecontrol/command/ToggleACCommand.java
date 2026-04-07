package com.group8.remotecontrol.command;

import com.group8.remotecontrol.devices.AirConditioner;

public class ToggleACCommand implements Command {
    private AirConditioner ac;
    private boolean prevState;

    public ToggleACCommand(AirConditioner ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        prevState = ac.isOn();
        ac.toggle();
    }

    @Override
    public void undo() {
        ac.setState(prevState);
    }
}

