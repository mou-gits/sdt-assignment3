package com.group8.remotecontrol;


import com.group8.remotecontrol.command.Command;
import com.group8.remotecontrol.devices.*;
import com.group8.remotecontrol.invoker.*;
import com.group8.remotecontrol.persistence.*;
import com.group8.remotecontrol.persistence.mapping.CommandMappingRepository;
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

        // Buttons 1–12 (0–11): normal commands
        if (buttonIndex < 12) {
            remote.pressButton(buttonIndex);
            updateGUI();
            return;
        }

        // Button 13 (index 12): Undo
        if (buttonIndex == 12) {
            remote.undo();
            updateGUI();
            return;
        }

        // Button 14 (index 13): Reset
        if (buttonIndex == 13) {
            // Load factory defaults
            mappings = repo.loadFactoryMappings();

            // Apply them to the remote
            applyMappings();

            // Update GUI
            gui.refreshButtonLabels();
            gui.refreshDropdowns();
            updateGUI();
        }
    }

    @Override
    public void assignCommand(int buttonIndex, String selectedOption) {

        if (selectedOption == null || selectedOption.equals("None")) {
            mappings.put(buttonIndex + 1, "None");
            return;
        }

        String key;
        switch (selectedOption) {
            case "Living Room Light":
                key = "ToggleInsideLight";
                break;
            case "Outdoor Light":
                key = "ToggleOutsideLight";
                break;
            case "Garage Door":
                key = "ToggleGarageDoor";
                break;
            case "Air Conditioner":
                key = "ToggleAC";
                break;
            case "All Lights":
                key = "ToggleAllLights";
                break;
            default:
                key = "None";
        }

        mappings.put(buttonIndex + 1, key);
    }



    public void saveMappings() {
        repo.saveMappings(mappings);
        applyMappings();
        gui.refreshButtonLabels();
        gui.refreshDropdowns();
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
            case "ToggleInsideLight" -> "Living Room Light";
            case "ToggleOutsideLight" -> "Outdoor Light";
            case "ToggleGarageDoor" -> "Garage Door";
            case "ToggleAC" -> "Air Conditioner";
            case "ToggleAllLights" -> "All Lights";
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
