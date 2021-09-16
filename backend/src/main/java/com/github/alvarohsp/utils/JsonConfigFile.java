package com.github.alvarohsp.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonConfigFile {
    @SuppressWarnings("unchecked")
    public static String getSecretKey(){
        JSONParser jsonParser = new JSONParser();
        String sKey = null;

        try {
            FileReader reader = new FileReader("config.json");

            Object obj = jsonParser.parse(reader);

            JSONObject fileObj = (JSONObject) obj;

            sKey = (String) fileObj.get("secretKey");


        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return sKey;
    }
}
