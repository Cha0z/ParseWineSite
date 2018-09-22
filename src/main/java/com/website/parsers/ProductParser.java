package com.website.parsers;

import com.website.entity.FeautureContainer;
import com.website.entity.ProductFeature;
import com.website.parsers.document.DocumentCreator;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.website.entity.ProductFeature.*;

public class ProductParser {


    private Document allDataFromPage;
    private Map<String, String> productInformationContainer;


    public ProductParser(String url) {

        this.allDataFromPage = new DocumentCreator(url).createDocument();
        productInformationContainer = new HashMap<>();
    }


    private Map<String, String> getInformationAboutProduct() {



        productInformationContainer.put()

        String title = dataFromDivWithInformation.select("div.product-details-wraper > h1").text().trim();
        String sweetness = getElement(ProductFeature.SWEETNESS);
        String type = getElement(ProductFeature.TYPE);
        String country = getElement(ProductFeature.COUNTRY);
        String region = getElement(ProductFeature.REGION);
        String brand = getElement(ProductFeature.BRAND);
        String volume = getElement(VOLUME);
        String alcoholPercent = getAlcoholPercent(dataFromDivWithInformation);


        return;
    }

    private void fillProductInformaionContainer() {
        Element dataFromDivWithInformation = allDataFromPage.getElementsByClass("container product-details").first();












        List<FeautureContainer> dataList = new ArrayList<>();
        Elements elementsWithData = dataFromDivWithInformation.getElementsByClass("details_about")
                .select("tbody > tr > td > p");


        for (Element currentElement : elementsWithData) {
            if (currentElement.text().contains("Цвет")) {
                continue;
            } else {
                StringBuffer currentLine = new StringBuffer(currentElement.text());

                if (currentLine.toString().contains("Сладость:")) {
                    dataList.add(new FeautureContainer(SWEETNESS, currentLine.substring(currentLine.indexOf(" "))));
                }
                if (currentLine.toString().contains("Тип:")) {
                    dataList.add(new FeautureContainer(TYPE, currentLine.substring(currentLine.indexOf(" "))));
                }
                if (currentLine.toString().contains("Страна:")) {
                    dataList.add(new FeautureContainer(COUNTRY, currentLine.substring(currentLine.indexOf(" "))));
                }
                if (currentLine.toString().contains("Производитель:")) {
                    dataList.add(new FeautureContainer(BRAND, currentLine.substring(currentLine.indexOf(" "))));
                }
                if (currentLine.toString().contains("Объём:")) {
                    dataList.add(new FeautureContainer(VOLUME, currentLine.substring(currentLine.indexOf(" "))));
                }
                if (currentLine.toString().contains("Регион:")) {
                    dataList.add(new FeautureContainer(REGION, currentLine.substring(currentLine.indexOf(" "))));
                }
            }
        }
        return dataList;
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

    private String getElement(ProductFeature feature) {
        String textContainer = " ";
        for (FeautureContainer container : generalDataAboutProduct) {
            if (container.getFeatureType().equals(feature)) {
                textContainer = container.getFeautureName().trim();
            }
        }
        if (textContainer.equals(" ")) {
            textContainer = "Значение отсутствует";
        }
        return textContainer;
    }


}
