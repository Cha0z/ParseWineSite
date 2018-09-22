package com.website.parsers;


import com.website.parsers.document.DocumentCreator;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainPageParser {

    private Document allDataFromPage;

    public MainPageParser(String src) {
        allDataFromPage = new DocumentCreator(src).createDocument();
    }

    private String parseLinks() {
        Element mainElement = allDataFromPage.getElementsByClass("collapse navbar-collapse main-menu_block").first();

        Elements elements = mainElement.select("div > ul > li > a");
        String linkToVineSection = "";
        for (Element currentElement : elements) {
            if (currentElement.text().contains("Вино")) {
                linkToVineSection = (currentElement.attr("abs:href"));
            }
        }
        return linkToVineSection;
    }


    public String getLinkForSection() {
        return parseLinks();
    }


}
