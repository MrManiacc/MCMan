package me.raynorjames.Utility;

import me.raynorjames.MCMan;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public abstract class FileManager {
    private File root;

    protected FileManager(File root) {
        this.root = root;
        if (!setup()) System.err.println("Failed create MCMan plugin folder!");
    }

    private boolean setup() {
        if (!root.exists()) return root.mkdir();
        return true;
    }

    protected File getPluginRoot() {
        return root;
    }

    protected File getPluginFile(String fileName) {
        System.err.println(root.getAbsolutePath() + File.separator + fileName);
        File file = new File(root.getAbsolutePath() + File.separator + fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                MCMan.getLog().logException(e);
            }
        }
        return file;
    }


    protected void writeCombo(File file, String key, Object value) {
        try (FileWriter fw = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
             out.println(key + ":" + value.toString());
        } catch (IOException e) {
            MCMan.getLog().logException(e);
        }
    }

    protected void removeLine(File f,  String key) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(f));
            File temp = new File("temp.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
            String currentLine;
            while((currentLine = br.readLine()) != null){
                if(!currentLine.contains(key))
                    bw.write(currentLine + System.getProperty("line.separator"));
            }
            bw.close();
            br.close();
            f.delete();
            temp.renameTo(f);
        } catch (FileNotFoundException e) {
            MCMan.getLog().logException(e);
        } catch (IOException e) {
            MCMan.getLog().logException(e);
        }
    }

    protected String getString(File file, String key) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                if(line.startsWith(key))
                {
                    String[] combo = line.split(":");
                    return combo[1];
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            MCMan.getLog().logException(e);
        }
        return "NOT_FOUND";
    }


    protected int getInt(File file, String key) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                if(line.startsWith(key))
                {
                    String[] combo = line.split(":");
                    return Integer.parseInt(combo[1]);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            MCMan.getLog().logException(e);
        }
        return -1;
    }

    protected double getDouble(File file, String key) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                if(line.startsWith(key))
                {
                    String[] combo = line.split(":");
                    return Double.parseDouble(combo[1]);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            MCMan.getLog().logException(e);
        }
        return -1.0;
    }

    protected boolean getBoolean(File file, String key) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                if(line.startsWith(key))
                {
                    String[] combo = line.split(":");
                    return Boolean.parseBoolean(combo[1]);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            MCMan.getLog().logException(e);
        }
        return false;
    }

    protected List<String> getArray(File file, String key){
        BufferedReader reader;
        List<String> values = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                if(line.startsWith(key))
                {
                    System.out.println(line);
                    String[] combo = line.split(":");
                    values.add(combo[1]);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            MCMan.getLog().logException(e);
        }
        return values;
    }


    protected String getDate(boolean simple) {
        return new SimpleDateFormat(simple ? "dd-MM-yyyy" : "dd/MM/yyyy-HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    protected String getDate() {
        return getDate(true);
    }

}
