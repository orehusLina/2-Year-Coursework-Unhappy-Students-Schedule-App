package com.example.firstapp;

import android.annotation.SuppressLint;

import com.example.firstapp.model.Resit;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVLoader {

    public List<Resit> loadCSVData(String filePath) {
        List<Resit> resits = new ArrayList<>();

        try (@SuppressLint({"NewApi", "LocalSuppress"}) CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            reader.readNext(); // пропускаем заголовок
            while ((line = reader.readNext()) != null) {
                Resit resit = new Resit(
                        line[0], line[1], line[2], line[3], line[4],
                        line[5], line[6], line[7], line[8], line[9],
                        line[10], line[11], line[12], line[13], line[14]
                );
                resits.add(resit);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return resits;
    }
}
