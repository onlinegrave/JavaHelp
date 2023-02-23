package com.code_help.paul_plays;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ShortLister extends JFrame {
    public static void main(String[] args) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        ShortWordFilter filter = new ShortWordFilter();
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            try (final Scanner scanner = new Scanner(new FileReader(fileChooser.getSelectedFile()))) {
                StringBuilder stringBuilder = new StringBuilder();
                while (scanner.hasNext()) {
                    final String str = scanner.next();
                    if (filter.accept(str)) {
                        stringBuilder.append(str).append("\n");
                    }
                }
                System.out.println(stringBuilder);
            }
        }

    }
}
