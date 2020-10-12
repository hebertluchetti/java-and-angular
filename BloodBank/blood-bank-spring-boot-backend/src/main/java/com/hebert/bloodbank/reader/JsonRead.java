package com.hebert.bloodbank.reader;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hebert.bloodbank.model.dto.DonorJsonDTO;


public class JsonRead extends AbstracrReader {
    public static DonorJsonDTO readOneFrom(String path) throws IOException {
        String jsonText = readJson(path);

        Type collectionType = new TypeToken<DonorJsonDTO>(){}.getType();
        return new Gson().fromJson(jsonText,collectionType);
    }

    public static List<DonorJsonDTO> readListFrom(String path) throws IOException {
        String jsonText = readJson(path);
        Type collectionType = new TypeToken<List<DonorJsonDTO>>(){}.getType();
        return new Gson().fromJson(jsonText, collectionType);
    }
  

}