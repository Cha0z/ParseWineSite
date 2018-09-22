package com.website.writer;

import com.website.entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DocumentWriter {

    private Logger logger = LogManager.getLogger(DocumentWriter.class.getSimpleName());

    public void write(List<Product> list) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter("Saved Files/file.csv"))) {
            for (Product currentProduct : list) {
                br.write(getFormattedString(currentProduct));
            }
        } catch (IOException e) {
        }
        logger.info("The file is saved in the 'Saved Files' folder");
    }

    private String getFormattedString(Product product) {
        return product.getTitle() + "."
                + product.getType() + "."
                + product.getRegion() + "."
                + product.getBrand() + "."
                + product.getCountry() + "."
                + product.getVolume() + "."
                + product.getSweetness() + "."
                + product.getAlcoholPercent() + "."
                + " " + "\n";

    }
}
