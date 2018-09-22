package com.website;

import com.website.wraper.Wrapper;

import java.util.List;
import java.util.Map;

public class StartApplication {

    public static void main(String[] args) {
        String linkOfWebSite = "http://winetime.com.ua";

        Wrapper wrapperComponent = new Wrapper(linkOfWebSite);

    //    List<String> linksOfAllProductFromSection = wrapperComponent.getAllLinkFromSection();

        List<Map<String,String>> informationAboutAllProduct = wrapperComponent.getInformationAboutAllProduct();

        System.out.println(informationAboutAllProduct.size());





    }
}
