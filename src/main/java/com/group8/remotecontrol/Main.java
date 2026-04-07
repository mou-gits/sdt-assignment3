import com.group8.remotecontrol.RealBackend;
import com.group8.remotecontrol.ui.RemoteControlGUI;
import javax.swing.*;

public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        RemoteControlGUI gui = new RemoteControlGUI();
        RealBackend backend = new RealBackend(gui);
        gui.setBackend(backend);
        gui.refreshButtonLabels();
        gui.setVisible(true);
    });
}


