package com.code_help.hydro;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoadIcon extends JFrame {
    public LoadIcon() {
        super("Simple Menu");
        try {
//            System.out.println(System.getProperty("user.dir"));
            Path current = Paths.get("src/resources/public/images/icon.jpg");
            String s = current.toAbsolutePath().toString();
            setIconImage(ImageIO.read(new File(s)));
        }catch (Exception e) {
            e.printStackTrace();
        }
        int input = JOptionPane.showOptionDialog(null, "You Sure You Want Quit?", "Qutting?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        if(input == JOptionPane.OK_OPTION)
        {
            Runtime.getRuntime().exit(1);
        }

        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        add(new JLabel("Hello World!"));
        setVisible(true);

    }

    public static void main(String[] args) {
        LoadIcon menu = new LoadIcon();
    }
}
