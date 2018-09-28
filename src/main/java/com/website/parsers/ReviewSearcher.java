package com.website.parsers;

import com.website.parsers.webdriwer.WebDriverHolder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ReviewSearcher {

    List<String> reviewsAboutProduct;
    private static WebDriver webDriver;

    public ReviewSearcher() {
        this.reviewsAboutProduct = new ArrayList<>();
        webDriver = WebDriverHolder.getInstance().getWebDriver();
    }

    public List<String> getAllReviewAboutProduct() {


        int indexOfSelectedElement = getPositionOfReviewsDiv();


        WebElement reviewsElement = webDriver.findElement(By.cssSelector("div.pageSection__pageSection--xZPv4:nth-child(" + indexOfSelectedElement + ")"));

        List<WebElement> reviewsElements = reviewsElement.findElements(By.cssSelector("div > div.communityReview__review--3LluN > div "));
        int counter = 0;
        for (
                WebElement currentElement : reviewsElements) {
            if (counter % 2 != 0) {
                counter++;
                continue;
            }
            reviewsAboutProduct.add(currentElement.getText());
            counter++;
        }


        return reviewsAboutProduct;
    }

    private int getPositionOfReviewsDiv() {
        int indexOfSelectedElement;
        WebElement mainElement = webDriver.findElement(By.cssSelector("div.wrap:nth-child(2) div:nth-child(3) div:nth-child(1) div.masterWinePage__layout--Mrspb.layout__outer--S05yQ > div.layout__inner--3JC-x"));
        List<WebElement> pageSectionsElement = mainElement.findElements(By.cssSelector("div.pageSection__pageSection--xZPv4"));
        if ((pageSectionsElement.size() == 6) || (pageSectionsElement.size() == 5)) {
            indexOfSelectedElement = 3;
        } else indexOfSelectedElement = 2;
        return indexOfSelectedElement;
    }


}
