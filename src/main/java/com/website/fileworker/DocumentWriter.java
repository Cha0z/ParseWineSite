package com.website.fileworker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.website.entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class DocumentWriter {

    private Logger logger = LogManager.getLogger(DocumentWriter.class.getSimpleName());

    public void writeToCsv(List<Product> list) {
        try (BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Saved Files/file.csv"), StandardCharsets.UTF_8))) {
            for (Product currentProduct : list) {
                br.write(getFormattedString(currentProduct));
            }
        } catch (IOException unused) {
        }
        logger.info("The file is saved in the 'Saved Files' folder");
    }

    public void writeToJson(List<Product> list) {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
        String json = gson.toJson(list);

        try (BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getJsonFileNameWithFinalIndex()), StandardCharsets.UTF_8))) {
            br.write(json);
        } catch (IOException unused) {
        }

    }

    private String getJsonFileNameWithFinalIndex() {
        String name = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(new File("Saved Files/index/index.txt")))) {
            name = "Saved Files/" + reader.readLine() + ".json";
        } catch (IOException ignored) {
        }
        return name;
    }


    private String getFormattedString(Product product) {
        return product.getTitle() + ";"
                + product.getType() + ";"
                + product.getColor() + ";"
                + product.getRegion() + ";"
                + product.getBrand() + ";"
                + product.getCountry() + ";"
                + product.getVolume() + ";"
                + product.getSweetness() + ";"
                + product.getAlcoholPercent() + ";"
                + product.getDescription() + "\n";

    }
}
