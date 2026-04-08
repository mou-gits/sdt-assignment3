package com.group8.remotecontrol.command;

import com.group8.remotecontrol.devices.AirConditioner;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ToggleACCommandTest {

    @Test
    void executeAndUndoShouldRestorePreviousState() {
        AirConditioner ac = new AirConditioner();
        ToggleACCommand cmd = new ToggleACCommand(ac);

        assertFalse(ac.isOn());

        cmd.execute();
        assertTrue(ac.isOn());

        cmd.undo();
        assertFalse(ac.isOn());
    }
}
