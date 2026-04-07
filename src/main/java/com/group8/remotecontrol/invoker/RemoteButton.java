package com.group8.remotecontrol.invoker;

import com.group8.remotecontrol.command.Command;

public class RemoteButton {
    private Command command;

    public RemoteButton(Command command) {
        this.command = command;
    }

    public void press() {
        command.execute();
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
