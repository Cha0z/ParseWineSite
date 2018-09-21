package com.website.parsers;


import com.website.parsers.document.DocumentCreator;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class MainPageParser {


    private int numberOfSections;
    private Document allDataFromPage;
    private List<String> listOfSectionsLink;


    public MainPageParser(String src, int numberOfSections) {
        this.numberOfSections = numberOfSections;
        allDataFromPage = new DocumentCreator(src).createDocument();
        listOfSectionsLink = new ArrayList<>();
    }

    public void parseLinks() {
        Element mainElement = allDataFromPage.getElementsByClass("collapse navbar-collapse main-menu_block").first();

        Elements elements = mainElement.select("div > ul > li > a");

        for (Element currentElement : elements) {
            String s = (currentElement.attr("abs:href")) + "?size=30";
            listOfSectionsLink.add(s);
        }


        //Not all menu item need. Bellow we delete links that we don`t need. Number of links
        //we have to choose at the StartApplication.class

        listOfSectionsLink = listOfSectionsLink.subList(0, numberOfSections);

        startParsingSections();

    }

    private void startParsingSections() {
        CreatorSectionsParser creatorSectionsParser = new CreatorSectionsParser(listOfSectionsLink);
        creatorSectionsParser.callSectionsParser();
    }


    public List<String> getListOfSectionsLink() {
        return listOfSectionsLink;
    }


}
