package com.example.firstapp.utils;

import android.annotation.SuppressLint;

import com.example.firstapp.model.ResitRequest;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVLoader {

    public List<ResitRequest> loadCSVData(String filePath) {
        List<ResitRequest> resitRequests = new ArrayList<>();

        try (@SuppressLint({"NewApi", "LocalSuppress"}) CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            reader.readNext(); // пропускаем заголовок
            while ((line = reader.readNext()) != null) {
                ResitRequest resitRequest = new ResitRequest(
                        line[0], line[1], line[2], line[3], line[4],
                        line[5], line[6], line[7], line[8], line[9],
                        line[10], line[11], line[12], line[13], line[14]
                );
                resitRequests.add(resitRequest);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return resitRequests;
    }
}
