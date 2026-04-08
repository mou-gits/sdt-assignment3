package com.group8.remotecontrol.invoker;

import com.group8.remotecontrol.command.Command;
import com.group8.remotecontrol.command.ToggleInsideLightCommand;
import com.group8.remotecontrol.devices.InsideLight;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RemoteControlTest {

    @Test
    void pressButtonAndUndoShouldAffectDevice() {
        RemoteControl remote = new RemoteControl();
        InsideLight light = new InsideLight();
        Command cmd = new ToggleInsideLightCommand(light);

        remote.setCommand(0, cmd);

        assertFalse(light.isOn());
        remote.pressButton(0);
        assertTrue(light.isOn());

        remote.undo();
        assertFalse(light.isOn());
    }
}
