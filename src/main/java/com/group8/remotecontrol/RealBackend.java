package com.group8.remotecontrol;


import com.group8.remotecontrol.command.Command;
import com.group8.remotecontrol.devices.*;
import com.group8.remotecontrol.invoker.*;
import com.group8.remotecontrol.persistence.*;
import com.group8.remotecontrol.ui.*;

import java.util.Map;

public class RealBackend implements BackendController {

    private RemoteControlGUI gui;
    private RemoteControl remote;

    private InsideLight insideLight;
    private OutsideLight outsideLight;
    private GarageDoor garageDoor;
    private AirConditioner ac;

    private Map<Integer, String> mappings;
    private CommandFactory factory;
    private CommandMappingRepository repo;

    public RealBackend(RemoteControlGUI gui) {
        this.gui = gui;

        insideLight = new InsideLight();
        outsideLight = new OutsideLight();
        garageDoor = new GarageDoor();
        ac = new AirConditioner();

        remote = new RemoteControl();
        repo = new CommandMappingRepository();
        mappings = repo.loadMappings();

        factory = new CommandFactory(insideLight, outsideLight, garageDoor, ac);

        applyMappings();
    }

    private void applyMappings() {
        for (int i = 1; i <= 12; i++) {
            String name = mappings.get(i);
            Command cmd = factory.create(name);
            remote.setCommand(i - 1, cmd);
        }
    }

    @Override
    public void executeCommand(int buttonIndex) {
        remote.pressButton(buttonIndex);
        updateGUI();
    }

    @Override
    public void assignCommand(int buttonIndex, String selectedOption) {
        mappings.put(buttonIndex + 1, selectedOption);
    }

    public void saveMappings() {
        repo.saveMappings(mappings);
        applyMappings();
        gui.refreshButtonLabels();
    }

    @Override
    public String getLabelForButton(int index) {
        String cmd = mappings.get(index + 1); // JSON uses 1–12, GUI uses 0–11

        if (cmd == null || cmd.equals("None"))
            return ""; // blank label for unassigned

        return prettyName(cmd);
    

}

    private String prettyName(String cmd) {
        return switch (cmd) {
            case "ToggleInsideLight" -> "Living Light";
            case "ToggleOutsideLight" -> "Outdoor Light";
            case "ToggleGarageDoor" -> "Garage Door";
            case "ToggleAC" -> "AC";
            case "ToggleAllLights" -> "All Lights";
            case "Undo" -> "Undo";
            case "Reset" -> "Reset";
            default -> "";
        };
    }

    private void updateGUI() {
        FloorPlanPanel fp = gui.getFloorPlanPanel();

        if (ac.isOn()) fp.setACOn(); else fp.setACOff();
        if (garageDoor.isOpen()) fp.setGarageOpen(); else fp.setGarageClosed();
        if (insideLight.isOn()) fp.setLivingRoomOn(); else fp.setLivingRoomOff();
        if (outsideLight.isOn()) fp.setOutdoorOn(); else fp.setOutdoorOff();
    }
}
