package com.group8.remotecontrol.ui;

/* Interface for communication between GUI and backend.*/

public interface BackendController {
    void executeCommand(int buttonIndex);
    void assignCommand(int buttonIndex, String selectedOption);
    void saveMappings();
    String getLabelForButton(int buttonIndex);
}