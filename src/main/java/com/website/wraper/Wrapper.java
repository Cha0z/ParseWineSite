package com.website.wraper;

import com.website.entity.Product;
import com.website.fileworker.DocumentReader;
import com.website.fileworker.DocumentWriter;
import com.website.objectcreator.ProductCreator;
import com.website.parsers.MainPageParser;
import com.website.parsers.ProductPageParser;
import com.website.parsers.ProductSearcher;
import com.website.parsers.SectionPageParser;

import java.util.List;
import java.util.Map;

public class Wrapper {
    private String url;

    private MainPageParser mainPageParser;
    private SectionPageParser sectionPageParser;
    private ProductPageParser productPageParser;
    private ProductCreator productCreator;
    private DocumentReader documentReader;
    private ProductSearcher productSearcher;

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
        productPageParser = new ProductPageParser(getAllLinkFromSection());
        return productPageParser.getInformationAboutProducts();
    }


    public List<Product> getProductsList(List outerList) {
        List<Product> productList = null;
        if (outerList.get(1) instanceof Map) {
            productCreator = new ProductCreator();
            productList = productCreator.transformDataToObjects(outerList);
        } else if (outerList.get(1) instanceof String[]) {
            productCreator = new ProductCreator();
            productList = productCreator.transformArrayToObjects(outerList);
        }
        return productList;
    }


    public void putInformationAboutProductToCsv() {
        DocumentWriter documentWriter = new DocumentWriter();
        documentWriter.write(getProductsList(getInformationAboutAllProduct()));
    }

    public List<String[]> getInformationAboutProductFromCsv() {

        documentReader = new DocumentReader();
        return documentReader.read();
    }

    public List<Product> getReviews() {
        List<Product> products;
        productSearcher = new ProductSearcher(getProductsList(getInformationAboutProductFromCsv()));
        return  products = productSearcher.findAllReviews();
    }

}
