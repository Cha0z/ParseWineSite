package com.website;

import com.website.parsers.MainPageParser;

public class StartApplication {

    public static void main(String[] args) {
        String linkOfWebSite = "http://winetime.com.ua";

        MainPageParser mainPageParser = new MainPageParser(linkOfWebSite,7);
        mainPageParser.parseLinks();


     /*   MainPageParser mainPageParser = new MainPageParser(linkOfWebSite,7);
        List<Section> sections = mainPageParser.parseLinks();*/
    }
}
