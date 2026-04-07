package com.group8.remotecontrol.ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RemoteControlGUI extends JFrame {

    private BackendController backend;
    private List<JButton> buttonList = new ArrayList<>();

    public void setBackend(BackendController backend) {
        this.backend = backend;
    }
    private final FloorPlanPanel floorPlanPanel;

    private static final String[] OPTIONS = {
            "Air Conditioner",
            "Garage Door",
            "Living Room",
            "Outdoor",
            "Undo",
            "Reset"
    };

    public RemoteControlGUI() {

        setTitle("Programmable Remote Control System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1150, 620);
        setLocationRelativeTo(null);

        floorPlanPanel = new FloorPlanPanel();

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // LEFT PANEL
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("House Floor Plan"));
        leftPanel.setPreferredSize(new Dimension(540, 560));
        leftPanel.add(floorPlanPanel, BorderLayout.CENTER);

        // CENTER PANEL
        JPanel centerPanel = new JPanel(new GridLayout(14, 1, 8, 8));
        centerPanel.setBorder(BorderFactory.createTitledBorder("Remote Control"));
        centerPanel.setPreferredSize(new Dimension(180, 560));

        // Create buttons with TEMP labels (backend not set yet)
        for (int i = 0; i < 14; i++) {
            int index = i;
            JButton button = new JButton("");   // label will be set later
            button.addActionListener(e -> {
                button.setBackground(new Color(180, 220, 255));
                backend.executeCommand(index);
                SwingUtilities.invokeLater(() -> button.setBackground(null));
            });
            centerPanel.add(button);
            buttonList.add(button);
        }

        // RIGHT PANEL
        JPanel rightPanel = new JPanel(new GridLayout(15, 2, 8, 8));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Button Assignments"));
        rightPanel.setPreferredSize(new Dimension(340, 560));

        // Add Save Mapping button FIRST
        JButton saveButton = new JButton("Save Mapping");
        saveButton.addActionListener(e -> backend.saveMappings());
        rightPanel.add(saveButton);
        rightPanel.add(new JLabel()); // filler

        // Add dropdowns
        for (int i = 0; i < 14; i++) {
            int index = i;

            JLabel label = new JLabel("Button " + (i + 1) + ":");
            JComboBox<String> dropdown = new JComboBox<>(OPTIONS);
            dropdown.setSelectedIndex(-1);

            dropdown.addActionListener(e -> {
                String selected = (String) dropdown.getSelectedItem();
                if (selected != null) {
                    backend.assignCommand(index, selected);
                }
            });

            rightPanel.add(label);
            rightPanel.add(dropdown);
        }

        JPanel centerWrapper = new JPanel();
        centerWrapper.setLayout(new BoxLayout(centerWrapper, BoxLayout.X_AXIS));
        centerWrapper.add(leftPanel);
        centerWrapper.add(Box.createHorizontalStrut(15));
        centerWrapper.add(centerPanel);
        centerWrapper.add(Box.createHorizontalStrut(15));
        centerWrapper.add(rightPanel);

        mainPanel.add(centerWrapper, BorderLayout.CENTER);
        add(mainPanel);
    }

    public void refreshButtonLabels() {
        for (int i = 0; i < buttonList.size(); i++) {
            JButton b = buttonList.get(i);
            b.setText(backend.getLabelForButton(i));
        }
    }

    public FloorPlanPanel getFloorPlanPanel() {
        return floorPlanPanel;
    }
}