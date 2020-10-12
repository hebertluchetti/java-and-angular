package com.hebert.bloodbank.reader;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hebert.bloodbank.model.dto.DonatorJsonDTO;


public class JsonRead extends AbstracrReader {
    public static DonatorJsonDTO readOneFrom(String path) throws IOException {
        String jsonText = readJson(path);

        Type collectionType = new TypeToken<DonatorJsonDTO>(){}.getType();
        return new Gson().fromJson(jsonText,collectionType);
    }

    public static List<DonatorJsonDTO> readListFrom(String path) throws IOException {
        String jsonText = readJson(path);
        Type collectionType = new TypeToken<List<DonatorJsonDTO>>(){}.getType();
        return new Gson().fromJson(jsonText, collectionType);
    }
  

}