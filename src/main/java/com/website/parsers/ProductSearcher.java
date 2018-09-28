package com.website.parsers;

import com.website.entity.Product;
import com.website.parsers.webdriwer.WebDriverHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProductSearcher {

    private String url;
    private List<Product> productList;
    private static WebDriver webDriver;
    private Logger logger = LogManager.getLogger(ProductSearcher.class.getSimpleName());
    Actions actions;

    public ProductSearcher(List<Product> productList) {
        this.productList = productList;
        this.url = "https://www.vivino.com/";
        webDriver = WebDriverHolder.getInstance().getWebDriver();
        actions = new Actions(webDriver);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }


    public List<Product> findAllReviews() {
        goToWebSite();


        logger.info("Старт поиска");
        for (Product currentProduct : productList) {

            tryToFindNextProduct(currentProduct.getTitle());
            System.out.println(currentProduct.getTitle());
            if (checkIfElementAvailable()) {
                WebElement productLink = webDriver.findElement(By.cssSelector("div.wine-card__image-wrapper > a"));
                productLink.click();
               /* Actions actions = new Actions(webDriver);
                try {
                    actions.wait(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

                currentProduct.setReview(findReviewAboutProduct());
            } else {
                List<String> emptyList = new ArrayList<>();
                emptyList.add("No reviews");
                currentProduct.setReview(emptyList);
            }


        }
        logger.info("Поиск всего закончен");
        webDriver.close();

        return productList;

    }

    private void tryToFindNextProduct(String title) {


        WebElement searchElement = webDriver.findElement(By.xpath("//input[@id='search-input']"));
        searchElement.click();
        searchElement.sendKeys(title + "\n");


    }

    private void goToWebSite() {
        webDriver.get(url);
    }

    private List<String> findReviewAboutProduct() {
        List<String> reviewList;


        ReviewSearcher searcher = new ReviewSearcher();
        reviewList = searcher.getAllReviewAboutProduct();

        return reviewList;

    }

    private boolean checkIfElementAvailable() {
        WebElement webElement = webDriver.findElement(By.cssSelector(".search-page__container"));
        WebElement childWebElement = webElement.findElement(By.cssSelector("div.search-page__content > div"));
        String check = childWebElement.getAttribute("class");
        if (check.equals("alert alert-warning text-center")) {
            return false;
        }
        return true;
    }

    /*public static void main(String[] args) {
        ProductSearcher ps = new ProductSearcher();
        ps.findAllReviews();
    }*/
}
