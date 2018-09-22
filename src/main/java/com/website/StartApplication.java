package com.website;

import com.website.wraper.Wrapper;

public class StartApplication {

    public static void main(String[] args) {
        String linkOfWebSite = "http://winetime.com.ua";

        Wrapper wrapperComponent = new Wrapper(linkOfWebSite);

        //    List<String> linksOfAllProductFromSection = wrapperComponent.getAllLinkFromSection();

        //   List<Map<String, String>> informationAboutAllProduct = wrapperComponent.getInformationAboutAllProduct();

        //  List<Product> products = wrapperComponent.getProductsList();

        wrapperComponent.putInformationAboutProductToCsv();


    }
}
