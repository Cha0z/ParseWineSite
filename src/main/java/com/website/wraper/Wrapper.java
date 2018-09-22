package com.website.wraper;

import com.website.objectcreator.ProductCreator;
import com.website.entity.Product;
import com.website.parsers.MainPageParser;
import com.website.parsers.ProductParser;
import com.website.parsers.SectionPageParser;
import com.website.writer.DocumentWriter;

import java.util.List;
import java.util.Map;

public class Wrapper {
    private String url;
    private MainPageParser mainPageParser;
    private SectionPageParser sectionPageParser;
    private ProductParser productParser;
    private ProductCreator productCreator;

    public Wrapper(String url) {
        this.url = url;
    }


    private String getLinkToSection() {
        mainPageParser = new MainPageParser(url);
        return mainPageParser.getLinkForSection();
    }

    public List<String> getAllLinkFromSection() {

        sectionPageParser = new SectionPageParser(getLinkToSection());
        return sectionPageParser.getLinksOfProductFromSection();
    }


    public List<Map<String, String>> getInformationAboutAllProduct() {
        productParser = new ProductParser(getAllLinkFromSection());
        return productParser.getInformationAboutProducts();
    }


    public List<Product> getProductsList() {
        productCreator = new ProductCreator(getInformationAboutAllProduct());
        return productCreator.transformDataToObjects();
    }


    public void putInformationAboutProductToCsv() {
        DocumentWriter documentWriter = new DocumentWriter();
        documentWriter.write(getProductsList());
    }

}
