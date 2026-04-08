package com.group8.remotecontrol.devices;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AirConditionerTest {

    @Test
    void toggleShouldFlipState() {
        AirConditioner ac = new AirConditioner();
        assertFalse(ac.isOn());

        ac.toggle();
        assertTrue(ac.isOn());

        ac.toggle();
        assertFalse(ac.isOn());
    }

    @Test
    void setStateShouldPersist() {
        AirConditioner ac = new AirConditioner();
        ac.setState(true);
        assertTrue(ac.isOn());

        ac.setState(false);
        assertFalse(ac.isOn());
    }
}
