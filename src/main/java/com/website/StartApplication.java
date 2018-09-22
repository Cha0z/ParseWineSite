package com.website;

import com.website.wraper.Wrapper;

import java.util.List;

public class StartApplication {

    public static void main(String[] args) {
        String linkOfWebSite = "http://winetime.com.ua";

        Wrapper wrapperComponent = new Wrapper();
        List<String> linksOfAllProductFromSection = wrapperComponent.getAllLinkFromSection(linkOfWebSite);


        System.out.println(linksOfAllProductFromSection.size());


    }
}
