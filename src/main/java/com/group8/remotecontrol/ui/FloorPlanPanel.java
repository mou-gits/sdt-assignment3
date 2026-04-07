package com.group8.remotecontrol.ui;

/*Implemented using Swing*/
import javax.swing.*;
import java.awt.*;

public class FloorPlanPanel extends JPanel {

    private boolean acOn;
    private boolean garageOpen;
    private boolean livingRoomOn;
    private boolean outdoorOn;

    public FloorPlanPanel() {
        setPreferredSize(new Dimension(520, 520));
        setBackground(Color.WHITE);
    }

    public void setACOn() {
        acOn = true;
        repaint();
    }

    public void setACOff() {
        acOn = false;
        repaint();
    }

    public void setGarageOpen() {
        garageOpen = true;
        repaint();
    }

    public void setGarageClosed() {
        garageOpen = false;
        repaint();
    }

    public void setLivingRoomOn() {
        livingRoomOn = true;
        repaint();
    }

    public void setLivingRoomOff() {
        livingRoomOn = false;
        repaint();
    }

    public void setOutdoorOn() {
        outdoorOn = true;
        repaint();
    }

    public void setOutdoorOff() {
        outdoorOn = false;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // smoother drawing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // main floor background
        g2.setColor(new Color(238, 238, 238));
        g2.fillRect(20, 20, 460, 460);

        g2.setColor(Color.BLACK);
        g2.drawRect(20, 20, 460, 460);

        // room rectangles
        Rectangle acRoom = new Rectangle(45, 45, 180, 150);
        Rectangle livingRoom = new Rectangle(255, 45, 180, 150);
        Rectangle garageRoom = new Rectangle(45, 240, 180, 150);
        Rectangle outdoorRoom = new Rectangle(255, 240, 180, 150);

        g2.draw(acRoom);
        g2.draw(livingRoom);
        g2.draw(garageRoom);
        g2.draw(outdoorRoom);

        // room titles
        g2.setFont(new Font("Arial", Font.BOLD, 16));
        g2.drawString("AC Room", 100, 70);
        g2.drawString("Living Room", 295, 70);
        g2.drawString("Garage", 105, 265);
        g2.drawString("Outdoor", 315, 265);

        // ---------------- AC ----------------
        int acX = 110;
        int acY = 110;
        int acSize = 40;

        g2.setColor(acOn ? Color.GREEN : Color.WHITE);
        g2.fillOval(acX, acY, acSize, acSize);

        g2.setColor(Color.BLACK);
        g2.drawOval(acX, acY, acSize, acSize);

        g2.drawString("AC", acX + 10, acY + 60);


        // ---------------- Living Room Light ----------------
        int lrX = 330;
        int lrY = 110;
        int lrSize = 40;

        g2.setColor(livingRoomOn ? Color.GREEN : Color.WHITE);
        g2.fillOval(lrX, lrY, lrSize, lrSize);

        g2.setColor(Color.BLACK);
        g2.drawOval(lrX, lrY, lrSize, lrSize);

        g2.drawString("Living", lrX + 5, lrY + 60);

        // ---------------- Garage Door ----------------
        int gX = 110;
        int gY = 310;
        int gSize = 40;

        g2.setColor(garageOpen ? Color.GREEN : Color.WHITE);
        g2.fillOval(gX, gY, gSize, gSize);

        g2.setColor(Color.BLACK);
        g2.drawOval(gX, gY, gSize, gSize);

        g2.drawString(garageOpen ? "Open" : "Closed", gX - 5, gY + 60);

        // ---------------- Outdoor Light ----------------
        int oX = 330;
        int oY = 310;
        int oSize = 40;

        g2.setColor(outdoorOn ? Color.GREEN : Color.WHITE);
        g2.fillOval(oX, oY, oSize, oSize);

        g2.setColor(Color.BLACK);
        g2.drawOval(oX, oY, oSize, oSize);

        g2.drawString("Outdoor", oX - 5, oY + 60);
    }
    }