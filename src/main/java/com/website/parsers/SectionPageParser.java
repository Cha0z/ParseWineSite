package com.website.parsers;

import com.website.parsers.document.DocumentCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class SectionPageParser {


    private Document allDataFromPage;
    private List<String> productLinks;
    private int numberOfPagesInSections;
    private int currentPageOfSections;
    private Logger logger = LogManager.getLogger(SectionPageParser.class.getSimpleName());

    public SectionPageParser(String url) {
        this.allDataFromPage = new DocumentCreator(url).createDocument();
        this.productLinks = new ArrayList<>();
        this.numberOfPagesInSections = getNumberOfPagesInSection();
        this.currentPageOfSections = 1;
    }


    public List<String> getLinksOfProductFromSection() {
        logger.info("Start searching link to product`s page");
        for (; currentPageOfSections <= numberOfPagesInSections; currentPageOfSections++) {
            Element mainDivElement = allDataFromPage.getElementsByClass("col-xs-10 catalog_right katalog_tovars footer-100").first();
            Elements linksOfProduct = mainDivElement
                    .getElementsByClass("item-block-head_main")
                    .select("div > a");

            for (Element currentElement : linksOfProduct) {
                String linkToProduct = currentElement.attr("abs:href");
                productLinks.add(linkToProduct);
            }

            if (currentPageOfSections < numberOfPagesInSections) {
                allDataFromPage = new DocumentCreator(goToNextPage()).createDocument();
            }
        }
        logger.info("All link`s to product page founded");
        return productLinks;
    }


    private int getNumberOfPagesInSection() {
        Elements linksToNextPages = allDataFromPage.getElementsByClass("pagination pagination_bottom")
                .select("li > a.pag");
        List<Integer> listOfNumberPages = new ArrayList<>();

        for (Element currentElement : linksToNextPages
        ) {
            listOfNumberPages.add(Integer.parseInt(currentElement.text()));
        }

        return listOfNumberPages.stream().max(Integer::compare).get();

    }

    private String goToNextPage() {
        Element linkToNextPage = allDataFromPage.getElementsByClass("pagination pagination_bottom")
                .select("li > a[rel=next]").first();

        return linkToNextPage.attr("abs:href");
    }

}
