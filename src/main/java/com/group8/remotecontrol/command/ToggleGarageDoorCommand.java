package com.group8.remotecontrol.command;

import com.group8.remotecontrol.devices.GarageDoor;

public class ToggleGarageDoorCommand implements Command {
    private GarageDoor door;
    private boolean prevState;

    public ToggleGarageDoorCommand(GarageDoor door) {
        this.door = door;
    }

    @Override
    public void execute() {
        prevState = door.isOpen();
        door.toggle();
    }

    @Override
    public void undo() {
        door.setState(prevState);
    }
}
