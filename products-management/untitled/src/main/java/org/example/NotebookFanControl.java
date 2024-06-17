//package org.example;
//
//import com.sun.jna.Library;
//import com.sun.jna.Native;
//import com.sun.jna.Platform;
//import com.sun.jna.platform.win32.Advapi32Util;
//import com.sun.jna.ptr.IntByReference;
//
//public class NotebookFanControl {
//
//    // Define a native interface for accessing system functions
//    public interface Kernel32 extends Library {
//        Kernel32 INSTANCE = (Kernel32) Native.loadLibrary("kernel32", Kernel32.class);
//        boolean GetSystemPowerStatus(IntByReference lpSystemPowerStatus);
//    }
//
//    // Method to get CPU temperature (Windows specific)
//    public static double getTemperature() {
//        if (Platform.isWindows()) {
//            IntByReference batteryStatus = new IntByReference();
//            if (Kernel32.INSTANCE.GetSystemPowerStatus(batteryStatus)) {
//                // Extract CPU temperature from battery status (just for demonstration)
//                int temperature = (batteryStatus.getValue() >> 16) & 0xFF;
//                return temperature;
//            } else {
//                System.err.println("Failed to retrieve system power status.");
//            }
//        } else {
//            System.err.println("Unsupported platform: " + Platform.getOSType());
//        }
//        return -1; // Error occurred or temperature not found
//    }
//
//    // Method to set fan speed (stub)
//    public static void setFanSpeed(int speed) {
//        try {
//            Advapi32Util.registrySetStringValue(Advapi32Util.HKEY_LOCAL_MACHINE, "SOFTWARE\\NotebookFanControl", "FanSpeed", Integer.toString(speed));
//            System.out.println("Fan speed set to " + speed + "%");
//        } catch (Exception e) {
//            System.err.println("Failed to set fan speed: " + e.getMessage());
//        }
//        // Implement fan speed control logic here
//        System.out.println("Fan speed set to " + speed + "%");
//    }
//
//    public static void main(String[] args) {
//        // Example: Monitor temperature and adjust fan speed accordingly
//        while (true) {
//            double temperature = getTemperature();
//            if (temperature != -1) {
//                System.out.println("Current temperature: " + temperature + "°C");
//                // Example: Adjust fan speed based on temperature threshold
//                if (temperature > 70) {
//                    setFanSpeed(100); // Set fan speed to 100% if temperature exceeds 70°C
//                } else if (temperature > 60) {
//                    setFanSpeed(75); // Set fan speed to 75% if temperature exceeds 60°C
//                } else {
//                    setFanSpeed(50); // Set fan speed to 50% by default
//                }
//            } else {
//                System.out.println("Failed to retrieve temperature.");
//            }
//            try {
//                Thread.sleep(5000); // Wait for 5 seconds before checking temperature again
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
