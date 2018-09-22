package com.website.objectcreator;

import com.website.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductCreator {

    private List<Map<String, String>> allProductInformation;
    private List<Product> productList;


    public ProductCreator(List<Map<String, String>> allProductInformation) {
        this.allProductInformation = allProductInformation;
        this.productList = new ArrayList<>();
    }

    public List<Product> transformDataToObjects() {

        for (Map<String, String> infoAboutOneProduct : allProductInformation) {
            productList.add(createProductObject(infoAboutOneProduct));
        }
        return productList;
    }

    private Product createProductObject(Map<String, String> information) {
        Product product = new Product();

        product.setRegion(information.get("region"));
        product.setAlcoholPercent(information.get("alcoholPercent"));
        product.setCountry(information.get("country"));
        product.setVolume(information.get("volume"));
        product.setSweetness(information.get("sweetness"));
        product.setBrand(information.get("brand"));
        product.setTitle(information.get("title"));
        product.setType(information.get("type"));

        return product;

    }
}
