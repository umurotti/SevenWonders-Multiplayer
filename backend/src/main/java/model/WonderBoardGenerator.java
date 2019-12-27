/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.mycompany.cs319deneme3.JaxRsActivator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author umur
 */
public class WonderBoardGenerator {
    private final String WONDERBOARD_PATH_IDENTIFIER = "wonderboardPath";
    private final String WONDERBOARD_FILE_NAME = "Wonderboards";
    private final String WONDERBOARD_EXTENSION = ".json";
    private final String WONDERBOARD_NAME_ATTRIBUTE = "wonderName";
    
    private String wonderboardPath;
    private ObjectMapper mapper;
    private File fl;
    
    public WonderBoardGenerator() {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(JaxRsActivator.PROPERTY_PATH));
        } catch (Exception ex) {
            Logger.getLogger(DeckGenerator.class.getName()).log(Level.SEVERE, "Error loading /etc/7wonders.properties", ex);
        }
        
            wonderboardPath = props.getProperty(WONDERBOARD_PATH_IDENTIFIER, "/home/ebedek");
            mapper = new ObjectMapper();
    }
    
    public HashMap<String,WonderBoard> generateWonderBoards() throws FileNotFoundException {
        JsonArray jArray = (JsonArray) new JsonParser().parse(new FileReader(wonderboardPath + WONDERBOARD_FILE_NAME + WONDERBOARD_EXTENSION));
        Gson gson = new Gson();
        HashMap<String, WonderBoard> wonderboardObjects = new HashMap<>();
        
        for(int i = 0; i < jArray.size(); i++) {
            wonderboardObjects.put(jArray.get(i).getAsJsonObject().get(WONDERBOARD_NAME_ATTRIBUTE).toString().replaceAll("\"", ""), gson.fromJson(jArray.get(i), WonderBoard.class));
        }
        
        return wonderboardObjects;
    }
}
