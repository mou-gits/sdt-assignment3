package com.group8.remotecontrol.persistence;

import com.group8.remotecontrol.command.*;
import com.group8.remotecontrol.devices.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommandFactoryTest {

    @Test
    void createShouldReturnCorrectCommandTypes() {
        CommandFactory factory = new CommandFactory(
                new InsideLight(),
                new OutsideLight(),
                new GarageDoor(),
                new AirConditioner()
        );

        assertTrue(factory.create("ToggleInsideLight") instanceof ToggleInsideLightCommand);
        assertTrue(factory.create("ToggleOutsideLight") instanceof ToggleOutsideLightCommand);
        assertTrue(factory.create("ToggleGarageDoor") instanceof ToggleGarageDoorCommand);
        assertTrue(factory.create("ToggleAC") instanceof ToggleACCommand);
        assertTrue(factory.create("ToggleAllLights") instanceof ToggleAllLightsCommand);
        assertTrue(factory.create("Unknown") instanceof NoCommand);
    }
}
