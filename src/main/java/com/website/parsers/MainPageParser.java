package com.website.parsers;


import com.website.parsers.document.DocumentCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainPageParser {
    private Logger logger = LogManager.getLogger(MainPageParser.class.getSimpleName());
    private Document allDataFromPage;

    public MainPageParser(String src) {
        allDataFromPage = new DocumentCreator(src).createDocument();
    }

    private String parseLinks() {
        Element elementThatContainsMenu = allDataFromPage.getElementsByClass("collapse navbar-collapse main-menu_block").first();

        Elements elementsLink = elementThatContainsMenu.select("a");
        String linkToWineSection = "";
        for (Element currentElement : elementsLink) {
            if (currentElement.text().contains("Вино")) {
                linkToWineSection = (currentElement.attr("abs:href"));
            }
        }
        logger.info("Link to the section received");
        return linkToWineSection;
    }


    public String getLinkForSection() {
        return parseLinks();
    }


}
