package com.faire.hackaton;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.io.Reader;
import java.util.List;

public class ReadCsv {

    public static List<String[]> readAll(String filename) {
        try (Reader reader = new FileReader( filename)) {
            System.out.println("realAll filename " + filename);
            CSVParserBuilder parserBuilder = new CSVParserBuilder();
            parserBuilder.withSeparator(';');

            CSVReaderBuilder builder = new CSVReaderBuilder(reader);
            builder.withCSVParser(parserBuilder.build());

            CSVReader csvReader = builder.build();

            List<String[]> temp =  csvReader.readAll();
            csvReader.close();

            return temp;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) throws Exception {

        List<String[]> dados = readAll("/tmp/data.miration.csv");

        for (String[] it : dados) {
            for (int i = 0; i< it.length; i++) {
                System.out.println(i + "-" + it[i]);
            }
            System.out.println("--------------------------------------------------");
        }

    }
}
