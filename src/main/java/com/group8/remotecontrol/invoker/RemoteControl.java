package com.group8.remotecontrol.invoker;

import com.group8.remotecontrol.command.Command;
import com.group8.remotecontrol.command.NoCommand;

import java.util.Stack;

public class RemoteControl {
    private RemoteButton[] buttons = new RemoteButton[12];
    private Stack<Command> history = new Stack<>();

    public RemoteControl() {
        for (int i = 0; i < 12; i++) {
            buttons[i] = new RemoteButton(new NoCommand());
        }
    }

    public void setCommand(int slot, Command command) {
        buttons[slot].setCommand(new Command() {
                @Override
                public void execute() {
                    command.execute();
                    history.push(command);
                }

                @Override
                public void undo() {
                    command.undo();
                }
            });
    }

    public void pressButton(int slot) {
        buttons[slot].press();
    }

    public void undo() {
        if (!history.isEmpty()) {
            history.pop().undo();
        } else {
            System.out.println("Nothing to undo.");
        }
    }

    public void resetAll() {
        for (int i = 0; i < 12; i++) {
            buttons[i].setCommand(new NoCommand());
        }
        history.clear();
        System.out.println("All commands reset.");
    }

}
