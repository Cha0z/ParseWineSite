package com.website.wraper;

import com.website.parsers.ProductParser;
import com.website.parsers.SectionPageParser;
import com.website.parsers.MainPageParser;

import java.util.List;

public class Wrapper {
    private String url;
    private MainPageParser mainPageParser;
    private SectionPageParser sectionPageParser;
    private ProductParser productParser;


    public List<String> getAllLinkFromSection(String src){
        mainPageParser = new MainPageParser(src);
        String linkToSection = mainPageParser.getLinkForSection();
        sectionPageParser = new SectionPageParser(linkToSection);
       return sectionPageParser.getLinksOfProductFromSection();
    }





}
