package com.website.wraper;

import com.website.creator.ProductCreator;
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
    private DocumentWriter documentWriter;

    public Wrapper(String url) {
        this.url = url;
    }

    public List<String> getAllLinkFromSection() {
        mainPageParser = new MainPageParser(url);
        String linkToSection = mainPageParser.getLinkForSection();
        sectionPageParser = new SectionPageParser(linkToSection);
        return sectionPageParser.getLinksOfProductFromSection();
    }

    public List<Map<String, String>> getInformationAboutAllProduct() {
        productParser = new ProductParser(getAllLinkFromSection());
        return productParser.getInformationAboutProducts();
    }

    public List<Product> getProductList() {
        productCreator = new ProductCreator(getInformationAboutAllProduct());
        return productCreator.transfornDataToObjects();
    }

    public void putInformationAboutProductToCsv() {
        documentWriter = new DocumentWriter();
        documentWriter.write(getProductList());
    }


}
