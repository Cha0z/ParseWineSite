package com.website.parsers;

import com.website.entity.FeautureContainer;
import com.website.entity.Product;
import com.website.entity.ProductFeature;
import com.website.parsers.document.DocumentCreator;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import static com.website.entity.ProductFeature.*;

public class PageParser implements Parser {

    private String url;

    private Document allDataFromPage;
    private Product product;
    private List<FeautureContainer> generalDataAboutProduct;


    public PageParser(String url) {
        this.url = url;
        this.allDataFromPage = new DocumentCreator(url).createDocument();
        generalDataAboutProduct = new ArrayList<>();
    }

    @Override
    public void parse() {
        product = fillDataToObj();

    }

    public Product getProduct() {
        parse();
        return product;
    }

    private Product fillDataToObj() {
        Element dataFromDivWithInformation = allDataFromPage.getElementsByClass("container product-details").first();
        generalDataAboutProduct = getData(dataFromDivWithInformation);

        //! Если будет время придумать как правильно отстортировать данные чтоб избавиться от индексов


        String title = dataFromDivWithInformation.select("div.product-details-wraper > h1").text().trim();
        String sweetness = getElement(ProductFeature.SWEETNESS);
        String type = getElement(ProductFeature.TYPE);
        String country = getElement(ProductFeature.COUNTRY);
        String region = getElement(ProductFeature.REGION);
        String brand = getElement(ProductFeature.BRAND);
        String volume = getElement(VOLUME);
        String alcoholPercent = getAlcoholPercent(dataFromDivWithInformation);


        return new Product(title, type, region, brand, country, volume, sweetness, alcoholPercent);
    }

    private List<FeautureContainer> getData(Element dataFromDivWithInformation) {
        List<FeautureContainer> dataList = new ArrayList<>();
        Elements elementsWithData = dataFromDivWithInformation.getElementsByClass("details_about")
                .select("tbody > tr > td > p");


        for (Element currentElement : elementsWithData) {
            if (currentElement.text().contains("Цвет")) {
                continue;
            } else {
                StringBuffer currentLine = new StringBuffer(currentElement.text());

                if (currentLine.toString().contains("Сладость")) {
                    dataList.add(new FeautureContainer(SWEETNESS, currentLine.substring(currentLine.indexOf(" "))));
                }
                if (currentLine.toString().contains("Тип")) {
                    dataList.add(new FeautureContainer(TYPE, currentLine.substring(currentLine.indexOf(" "))));
                }
                if (currentLine.toString().contains("Страна")) {
                    dataList.add(new FeautureContainer(COUNTRY, currentLine.substring(currentLine.indexOf(" "))));
                }
                if (currentLine.toString().contains("Производитель")) {
                    dataList.add(new FeautureContainer(BRAND, currentLine.substring(currentLine.indexOf(" "))));
                }
                if (currentLine.toString().contains("Объём")) {
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
                StringBuffer currentLine = new StringBuffer(currentElement.text());
                alcoholPercent = currentLine.substring(currentLine.indexOf(" ")).trim();
            }
        }

        return alcoholPercent;
    }

    private String getElement(ProductFeature feature) {
        String textContainer = " ";
        for (FeautureContainer container :generalDataAboutProduct){
            if(container.getFeatureType().equals(feature)){
                textContainer = container.getFeautureName().trim();
            }
        }
        if(textContainer.equals(" ")){
            textContainer="Значение отсутствует";
        }
        return textContainer;
    }


}
