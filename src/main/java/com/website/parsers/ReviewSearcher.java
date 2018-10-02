package com.website.parsers;

import com.website.parsers.webdriwer.WebDriverHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import waiter.Waiter;

import java.util.ArrayList;
import java.util.List;

public class ReviewSearcher {

    private List<String> reviewsAboutProduct;
    private static WebDriver webDriver;

    private Logger logger = LogManager.getLogger(ReviewSearcher.class.getSimpleName());
    private Waiter waiter = new Waiter();
    private WebDriverWait wait;

    public ReviewSearcher() {
        this.reviewsAboutProduct = new ArrayList<>();
        webDriver = WebDriverHolder.getInstance().getWebDriver();
        wait = new WebDriverWait(webDriver, 10);
    }

    public List<String> getAllReviewAboutProduct() {


        clickOnVintageList();

        clickOnShowMoreReviewsButton();


        List<WebElement> reviewsElements = getReviewsElements();

        reviewsAboutProduct = transformWebElementToString(reviewsElements);


        logger.info(reviewsAboutProduct.size());

        return reviewsAboutProduct;

    }


    private void clickOnShowMoreReviewsButton() {
        waiter.waitForPageLoadComplete(webDriver);
        WebElement moreReviewsElement;

        for (int counter = 0; counter < 5; counter++) {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (webDriver.findElement(By.xpath("//body")).getText().contains("Show more reviews")) {
                moreReviewsElement = wait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.xpath("//a[text()='Show more reviews']"))));
                if (moreReviewsElement != null) {
                    moreReviewsElement.click();
                    waiter.waitForPageLoadComplete(webDriver);

                } else break;
            }

        }
    }


    private void clickOnVintageList() {
        waiter.waitForPageLoadComplete(webDriver);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (webDriver.findElement(By.xpath("//body")).getText().contains("Jump to vintage")) {
            WebElement listWithVintage = wait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.xpath("//span[text()='Jump to vintage']"))));
            listWithVintage.click();

            WebElement nvElementInList = wait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.xpath("//div[@class='responsiveDropdownMenu__menu--3yx7j']/a[1]"))));
            nvElementInList.click();
        }

    }

    private List<WebElement> getReviewsElements() {
        List<WebElement> elements = new ArrayList<>();
        waiter.waitForPageLoadComplete(webDriver);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (webDriver.findElement(By.xpath("//body")).getText().contains("Reviews")) {
            elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[text()='Reviews']//following-sibling::div/div/div/div/div[@class='vintage-review-item__content']/div/div")));
        }
        return elements;

    }

    private List<String> transformWebElementToString(List<WebElement> reviewsWebElement) {
        List<String> reviews = new ArrayList<>();
        int counter = 0;
        for (WebElement currentElement : reviewsWebElement) {

            if (counter % 2 == 0) {
                if(currentElement.getText().contains("- Rated the")){
                    continue;
                }
                reviews.add(currentElement.getText());
            }
            counter++;

        }
        if (reviews.size() == 0) {
            reviews.add("No reviews");
        }

        return reviews;
    }
}
