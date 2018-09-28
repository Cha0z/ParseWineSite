package com.website.fileworker;

import com.website.entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class DocumentWriter {

    private Logger logger = LogManager.getLogger(DocumentWriter.class.getSimpleName());

    public void write(List<Product> list) {
        try (BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Saved Files/file.csv"), StandardCharsets.UTF_8))) {
            for (Product currentProduct : list) {
                br.write(getFormattedString(currentProduct));
            }
        } catch (IOException unused) {
        }
        logger.info("The file is saved in the 'Saved Files' folder");
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
