package com.kvintanaka.mcaprocessor;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Main Class
 * 
 * @author kvintanaka <github.com/kvintanaka>
 */
public class App {
    public static void main(String[] args) {
        UI ui = new UI();
        try {
            ui.menu();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}
