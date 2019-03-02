package me.raynorjames.Utility;

import java.io.*;

public class Log extends FileManager {
    private File logFile;
    private static final String YELLOW_TEXT = "\u001B[43m";
    private static final String RED_TEXT = "\u001B[31m";
    private static final String GREEN_TEXT = "\u001B[32m";
    private static final String CYAN_TEXT = "\u001B[36m";
    private static final String RESET_TEXT = "\u001B[0m";

    public Log(File root) {
        super(root);
        this.logFile = getPluginFile("log-" + getDate() + ".txt");
    }

    /**
     * Todo: make this display to all ops if they choose,
     * as well as logging it to file if config says
     *
     * @param message
     */
    public void logNormal(String message) {
        System.out.println(GREEN_TEXT + "[MCMan][Normal] " + message + RESET_TEXT);
        try (FileWriter fw = new FileWriter(logFile, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
             out.println("[Normal][" + getDate(false) + "] " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logError(String message) {
        System.out.println(RED_TEXT + "[MCMan][Error] " + message + RESET_TEXT);
        try (FileWriter fw = new FileWriter(logFile, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println("[Error][" + getDate(false) + "] " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logException(Exception ex) {
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        String exceptionAsString = sw.toString();


        System.out.println(RED_TEXT + "[MCMan][Exception] " + ex.getLocalizedMessage() + RESET_TEXT);
        try (FileWriter fw = new FileWriter(logFile, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println("[Exception][" + getDate(false) + "] " + ex.getMessage());
            out.println("[Exception][" + getDate(false) + "] " + exceptionAsString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logWarning(String message) {
        System.out.println(YELLOW_TEXT + "[MCMan][Warning] " + message + RESET_TEXT);
        try (FileWriter fw = new FileWriter(logFile, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
             out.println("[Warning][" + getDate(false) + "] " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logEvent(String message) {
        System.out.println(CYAN_TEXT + "[MCMan][Event] " + message + RESET_TEXT);
        try (FileWriter fw = new FileWriter(logFile, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println("[Event][" + getDate(false) + "] " + message);
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

}
