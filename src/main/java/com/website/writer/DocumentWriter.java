package com.website.writer;

import com.website.entity.Product;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DocumentWriter {


    public void write(List<Product> list) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter("file.csv"))) {
            for (Product currentProduct : list) {
                br.write(getFormatedString(currentProduct));
            }

        } catch (IOException e) {
        }
    }

    private String getFormatedString(Product product) {
        return product.getTitle() + ","
                + product.getType() + ","
                + product.getRegion() + ","
                + product.getBrand() + ", "
                + product.getCountry() + ", "
                + product.getVolume() + ", "
                + product.getSweetness() + ", "
                + product.getAlcoholPercent();

    }
}
