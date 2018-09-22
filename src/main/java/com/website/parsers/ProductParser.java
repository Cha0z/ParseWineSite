package com.website.parsers;

import com.website.parsers.document.DocumentCreator;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductParser {


    private Document allDataFromPage;

    private List<String> linkContainer;
    private List<Map<String,String>> allInformationAboutProduct;


    public ProductParser(List<String> productsLinks) {
        this.allInformationAboutProduct = new ArrayList<>();
        this.linkContainer = productsLinks;


    }


    public List<Map<String, String>> getInformationAboutProducts() {

        for(String url:linkContainer){
            goToNextProduct(url);
            allInformationAboutProduct.add(getInformationAboutCurrentProduct());

        }

        return allInformationAboutProduct;
    }

    private Map<String, String> getInformationAboutCurrentProduct() {
        Map<String, String> productInformationContainer = new HashMap<>();

        Element dataFromDivWithInformation = allDataFromPage.getElementsByClass("container product-details").first();
        Elements elementsWithData = dataFromDivWithInformation.getElementsByClass("details_about")
                .select("tbody > tr > td > p");

        productInformationContainer.put("title", getTitleField(dataFromDivWithInformation));
        productInformationContainer.put("alcoholPercent", getAlcoholPercent(dataFromDivWithInformation));


        for (Element currentElement : elementsWithData) {
            if (currentElement.text().contains("Цвет")) {
                continue;
            } else {
                StringBuffer currentLine = new StringBuffer(currentElement.text());

                if (currentLine.toString().contains("Сладость:")) {
                    productInformationContainer.put("sweetness", currentLine.substring(currentLine.indexOf(" ")));
                }
                if (currentLine.toString().contains("Тип:")) {
                    productInformationContainer.put("type", currentLine.substring(currentLine.indexOf(" ")));
                }
                if (currentLine.toString().contains("Страна:")) {
                    productInformationContainer.put("country", currentLine.substring(currentLine.indexOf(" ")));
                }
                if (currentLine.toString().contains("Производитель:")) {
                    productInformationContainer.put("brand", currentLine.substring(currentLine.indexOf(" ")));
                }
                if (currentLine.toString().contains("Объём:")) {
                    productInformationContainer.put("volume", currentLine.substring(currentLine.indexOf(" ")));
                }
                if (currentLine.toString().contains("Регион:")) {
                    productInformationContainer.put("region", currentLine.substring(currentLine.indexOf(" ")));
                }
            }
        }

        if (!productInformationContainer.containsKey("region")){
            productInformationContainer.put("region","unknown");
        }

        System.out.println(productInformationContainer);
        return productInformationContainer;
    }


    private String getAlcoholPercent(Element dataFromDivWithInformation) {
        String alcoholPercent = "Не указано";
        Elements elementsWithData = dataFromDivWithInformation.getElementsByClass("harakter_tovar")
                .select("p");

        for (Element currentElement : elementsWithData) {
            if (currentElement.text().contains("Алкоголь:")) {
                StringBuilder currentLine = new StringBuilder(currentElement.text());
                alcoholPercent = currentLine.substring(currentLine.indexOf(" ")).trim();
            }
        }

        return alcoholPercent;
    }


    private String getTitleField(Element elementWithData) {

        return elementWithData.select("div.product-details-wraper > h1").text().trim();
    }

    private void goToNextProduct(String url) {
        this.allDataFromPage = new DocumentCreator(url).createDocument();
    }
}
