package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Fan {
    public static void main(String[] args) {
        try {
            Process process = Runtime.getRuntime().exec("wmic path Win32_Fan Get DesiredSpeed");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            System.out.println("Fan Speeds:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Check for errors
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = errorReader.readLine()) != null) {
                System.err.println(line);
            }

            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}



