package com.group8.remotecontrol.persistence;

import com.group8.remotecontrol.command.*;
import com.group8.remotecontrol.devices.*;

public class CommandFactory {

    private InsideLight insideLight;
    private OutsideLight outsideLight;
    private GarageDoor garageDoor;
    private AirConditioner ac;
    private AllLightsController allLights;

    public CommandFactory(InsideLight insideLight,
                          OutsideLight outsideLight,
                          GarageDoor garageDoor,
                          AirConditioner ac) {

        this.insideLight = insideLight;
        this.outsideLight = outsideLight;
        this.garageDoor = garageDoor;
        this.ac = ac;
        this.allLights = new AllLightsController(insideLight, outsideLight);
    }

    public Command create(String name) {
        switch (name) {
            case "ToggleInsideLight":
                return new ToggleInsideLightCommand(insideLight);
            case "ToggleOutsideLight":
                return new ToggleOutsideLightCommand(outsideLight);
            case "ToggleGarageDoor":
                return new ToggleGarageDoorCommand(garageDoor);
            case "ToggleAC":
                return new ToggleACCommand(ac);
            case "ToggleAllLights":
                return new ToggleAllLightsCommand(allLights);
            default:
                return new NoCommand();
        }
    }
}

