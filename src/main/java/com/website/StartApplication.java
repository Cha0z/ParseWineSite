package com.website;

import com.website.wraper.Wrapper;

import java.util.List;

public class StartApplication {

    public static void main(String[] args) {
        String linkOfWebSite = "http://winetime.com.ua";

        Wrapper wrapperComponent = new Wrapper(linkOfWebSite);

        List<String> linksOfAllProductFromSection = wrapperComponent.getAllLinkFromSection();

        System.out.println(linksOfAllProductFromSection.size());

        //   List<Map<String, String>> informationAboutAllProduct = wrapperComponent.getInformationAboutAllProduct();

        //  List<Product> products = wrapperComponent.getProductsList();

        //  wrapperComponent.putInformationAboutProductToCsv();


    }
}
