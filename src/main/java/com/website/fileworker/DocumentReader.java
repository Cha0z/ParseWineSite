package com.website.fileworker;

import com.website.entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DocumentReader {

    private Logger logger = LogManager.getLogger(DocumentWriter.class.getSimpleName());

    public List<String[]> read() {
        List<String[]> productsInfo = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Saved Files/file.csv"), StandardCharsets.UTF_8))) {
            String line;
            String[] product;
            String cvsSplitBy = ";";
            while ((line = br.readLine()) != null) {
                productsInfo.add(line.split(cvsSplitBy));
            }
        } catch (IOException unused) {
        }
        logger.info("The file was red from 'Saved Files' folder");
         return productsInfo;
    }

}
