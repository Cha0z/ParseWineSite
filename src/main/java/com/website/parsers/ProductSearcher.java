package com.website.parsers;

import com.website.entity.Product;
import com.website.parsers.webdriwer.WebDriverHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import waiter.Waiter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductSearcher {

    private String url;
    private List<Product> originalProductList;
    private List<Product> productListOnlyWithRewiews;
    private static WebDriver webDriver;
    private Logger logger = LogManager.getLogger(ProductSearcher.class.getSimpleName());
    private Waiter waiter;
    private WebDriverWait wait;
    private Product product;


    public ProductSearcher(List<Product> originalProductList) {
        this.originalProductList = originalProductList;
        productListOnlyWithRewiews = new ArrayList<>();
        this.url = "https://www.vivino.com/";
        webDriver = WebDriverHolder.getInstance().getWebDriver();
        waiter = new Waiter();
        wait = new WebDriverWait(webDriver, 10);
    }


    public List<Product> findAllReviews() {
        goToWebSite();

        logger.info("Старт поиска");
        int currentIndex = getCurrentIndex();

        try {
            for (; currentIndex < originalProductList.size(); currentIndex++) {


                tryToFindNextProduct(originalProductList.get(currentIndex).getTitle());
                waiter.waitForPageLoadComplete(webDriver);
                if (checkIfElementAvailable()) {

                    WebElement productLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.wine-card__image-wrapper > a")));
                    productLink.click();
                    product = originalProductList.get(currentIndex);
                    product.setReview(findReviewAboutProduct());
                    productListOnlyWithRewiews.add(product);
                } else {
                    List<String> emptyList = new ArrayList<>();
                    emptyList.add("No reviews#");
                    originalProductList.get(currentIndex).setReview(emptyList);
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            writeIndexToFile(currentIndex);


            return productListOnlyWithRewiews;
        }
        logger.info("Поиск всего закончен");
        webDriver.close();

        return originalProductList;
    }

    private void tryToFindNextProduct(String title) {


        WebElement searchElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='search-input']")));
        searchElement.click();
        searchElement.sendKeys(title + "\n");
    }

    private void goToWebSite() {
        webDriver.get(url);
    }

    private List<String> findReviewAboutProduct() {
        List<String> reviewList;

        ReviewSearcher searcher = new ReviewSearcher();


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        reviewList = searcher.getAllReviewAboutProduct();

        return reviewList;

    }

    private boolean checkIfElementAvailable() {
        WebElement childWebElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.search-page__content > div")));

        String check = childWebElement.getAttribute("class");
        if (check.equals("alert alert-warning text-center")) {
            return false;
        }
        return true;
    }

    private int getCurrentIndex() {
        int currentIndex = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("Saved Files/index/index.txt")))) {
            currentIndex = Integer.parseInt(reader.readLine());
        } catch (IOException exception) {
        }
        return currentIndex;
    }

    private void writeIndexToFile(Integer currentIndex) {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Saved Files/index/index.txt")))) {
            bw.write(currentIndex.toString());
        } catch (IOException exception) {
        }
    }


}


