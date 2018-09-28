package com.website.objectcreator;

import com.website.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductCreator {


    private List<Product> productList;


    public ProductCreator() {

        this.productList = new ArrayList<>();
    }

    public List<Product> transformDataToObjects(List<Map<String, String>> allProductInformation) {

        for (Map<String, String> infoAboutOneProduct : allProductInformation) {
            productList.add(createProductObject(infoAboutOneProduct));
        }
        return productList;
    }


    public List<Product> transformArrayToObjects(List<String[]> productsInfo) {
        List<Product> productList = new ArrayList<>();
        for (String[] currentProductInfo : productsInfo) {

            productList.add(createProductFromArray(currentProductInfo));
        }
        return productList;
    }


    private Product createProductObject(Map<String, String> information) {
        Product product = new Product();

        product.setRegion(information.get("region"));
        product.setAlcoholPercent(information.get("alcoholPercent"));
        product.setColor(information.get("color"));
        product.setCountry(information.get("country"));
        product.setVolume(information.get("volume"));
        product.setSweetness(information.get("sweetness"));
        product.setBrand(information.get("brand"));
        product.setTitle(information.get("title"));
        product.setType(information.get("type"));
        product.setDescription(" ");

        return product;

    }

    private Product createProductFromArray(String[] currentProductInfo) {
        Product product = new Product();


        product.setTitle(currentProductInfo[0].replaceAll("«","").replaceAll("»",""));
        product.setType(currentProductInfo[1]);
        product.setColor(currentProductInfo[2]);
        product.setRegion(currentProductInfo[3]);
        product.setBrand(currentProductInfo[4]);
        product.setCountry(currentProductInfo[5]);
        product.setVolume(currentProductInfo[6]);
        product.setSweetness(currentProductInfo[7]);
        product.setAlcoholPercent(currentProductInfo[8]);
        product.setDescription(currentProductInfo[9]);

        return product;
    }


}
