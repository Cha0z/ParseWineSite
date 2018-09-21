package com.website.parsers.document;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class DocumentCreator {

    private String url;

    public DocumentCreator(String url) {
        this.url = url;
    }

    public Document createDocument() {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            System.out.println("Не удалось подключиться к сайту");
        }
        return doc;
    }

}
