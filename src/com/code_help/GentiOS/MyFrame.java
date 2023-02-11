package com.code_help.GentiOS;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MyFrame extends JFrame {
    JButton button;
    Color RGB;

    MyFrame() {
        button = new JButton();
        button.setBounds(150, 100, 130, 50);
        button.setText("ElHamidullah");
        button.setFocusable(false);
        button.addActionListener(e -> {
            Colors();
            this.getContentPane().setBackground(RGB);
        });
        this.add(button);
        this.setLayout(null);

        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Gui gui gui");
        this.setSize(500, 500);
        this.setVisible(true);
    }

    public Color Colors() {
        Random randomGenerator = new Random();
        int red = randomGenerator.nextInt(256);
        int green = randomGenerator.nextInt(256);
        int blue = randomGenerator.nextInt(256);
        RGB = new Color(red, green, blue);
        return RGB;
    }
}
