package com.group8.remotecontrol;

import com.group8.remotecontrol.command.Command;
import com.group8.remotecontrol.devices.*;
import com.group8.remotecontrol.invoker.RemoteControl;
import com.group8.remotecontrol.persistence.CommandFactory;
import com.group8.remotecontrol.persistence.CommandMappingRepository;

import java.util.HashMap;
import java.util.Map;

public class App___old {

    public static void main(String[] args) {

        System.out.println("=== Remote Control Backend Test ===");

        // 1. Create devices
        InsideLight inside = new InsideLight();
        OutsideLight outside = new OutsideLight();
        GarageDoor door = new GarageDoor();
        AirConditioner ac = new AirConditioner();

        // 2. Create remote
        RemoteControl remote = new RemoteControl();

        // 3. Load JSON mappings
        CommandMappingRepository repo = new CommandMappingRepository();
        Map<Integer, String> mappings = repo.loadMappings();

        // 4. Create factory
        CommandFactory factory = new CommandFactory(inside, outside, door, ac);

        // 5. Assign commands to buttons
        for (int i = 1; i <= 12; i++) {
            String commandName = mappings.get(i);
            Command cmd = factory.create(commandName);
            remote.setCommand(i - 1, cmd); // convert 1–12 to 0–11
        }

        // 6. Test sequences
        System.out.println("\n--- Test Sequence 1: Press button 1, 2, undo twice ---");
        remote.pressButton(0); // button1
        remote.pressButton(1); // button2
        remote.undo();
        remote.undo();

        System.out.println("\n--- Test Sequence 2: Press button 3, 4, 5, undo ---");
        remote.pressButton(2); // button3
        remote.pressButton(3); // button4
        remote.pressButton(4); // button5
        remote.undo();

        System.out.println("\n--- Test Sequence 3: Press All Lights twice ---");
        remote.pressButton(4); // All Lights ON
        remote.pressButton(4); // All Lights OFF

        System.out.println("\n--- Test Sequence 4: Undo All Lights twice ---");
        remote.undo();
        remote.undo();

        System.out.println("\n--- Test Sequence 5: Press unassigned button ---");
        remote.pressButton(7); // button8 (None)

        System.out.println("\n--- Test Sequence 6: Reset all commands ---");
        remote.resetAll();
        remote.pressButton(0); // should do nothing
    }
}
