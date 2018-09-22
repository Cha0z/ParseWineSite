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
                br.write(currentProduct.getTitle()+","+currentProduct.getType()+","+currentProduct.getRegion()+","+currentProduct.getBrand()+"\n");
            }

        } catch (IOException e) {
        }

    }

}
