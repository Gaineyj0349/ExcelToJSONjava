package com.gainwise.iloveny.main;

import java.io.*;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {

    public static Logger LOGGER;

    public static void initLogger(){
       LOGGER = Logger.getLogger("ILoveNYJSONProgram");
        FileHandler fileHandler;
        try {
            fileHandler = new FileHandler("assets/LOGDATA.txt");
            LOGGER.addHandler(fileHandler);
            LOGGER.setLevel(Level.ALL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logError(String message){
        LOGGER.log(Level.SEVERE, message);
    }
    public static void logInfo(String message){
        LOGGER.log(Level.INFO, message);
    }

    public static void saveJSONfileWithName(String jsonString, String fileName) {
        FileOutputStream fileOutputStream = null;
        BufferedWriter bufferedWriter = null;
        OutputStreamWriter outputStreamWriter = null;
        try {
            File file = new File("assets/"+fileName);
            if(!file.exists()){
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(jsonString);
            bufferedWriter.flush();
            bufferedWriter.close();
        }  catch (IOException e) {
            e.printStackTrace();
            logError("Issue with creating .json file/streams/writers"+
                    "\n" + e.getMessage() + "\n" + e.toString());
        }
    }
}
