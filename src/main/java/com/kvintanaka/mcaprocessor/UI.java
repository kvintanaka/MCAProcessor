package com.kvintanaka.mcaprocessor;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import com.kvintanaka.mcaprocessor.converter.BookToJSONConverter;
import com.kvintanaka.mcaprocessor.filter.BookFilter;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

/**
 * User Interface class
 * 
 * @author kvintanaka <github.com/kvintanaka>
 */
public class UI {

    /**
     * Main Menu
     * 
     * @throws URISyntaxException
     * @throws IOException
     */
    public void menu() throws URISyntaxException, IOException {
        Scanner sc = new Scanner(System.in);
        char choice;

        do {
            System.out.print("(c) Choose world file, (h) help or (x) exit? ");
            choice = (sc.nextLine().toLowerCase() + " ").charAt(0);

            switch (choice) {
                case 'c':
                    // Choose region directory
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File(System.getenv("APPDATA") + "/.minecraft/saves"));
                    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    fileChooser.setDialogTitle("Choose region directory");
                    fileChooser.setAcceptAllFileFilterUsed(false);

                    if (fileChooser.showDialog(new JFrame(), "Choose World") == JFileChooser.APPROVE_OPTION) {
                        MCAProcessor processor = new MCAProcessor(fileChooser.getSelectedFile().toPath());
                        processor.process(new BookFilter(), new BookToJSONConverter());
                        System.out.println("Outputted to Output directory");
                    } else {
                        System.out.println("Invalid region directory");
                    }
                    break;

                case 'h':
                    String url = "https://github.com/kvintanaka/MCAProcessor";
                    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                        Desktop.getDesktop().browse(new URI(url));

                    } else {
                        System.out.println("Go to: " + url);
                    }
                    break;

                case 'x':
                    break;

                default:
                    break;
            }
        } while (choice != 'x');
    }
}