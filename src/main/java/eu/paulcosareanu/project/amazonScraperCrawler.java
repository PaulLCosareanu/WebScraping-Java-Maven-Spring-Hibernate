/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.paulcosareanu.project;
import static eu.paulcosareanu.project.StringSplitter.*;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 *
 * @author cosar
 */
public class amazonScraperCrawler extends Thread {

    private String XpathName;
    private String XpathLink;
    private String XpathPrice;
    private String XpathImageUrl;
    private String XpathPagination;
    private String[] Links;
    private String[] category;

    public amazonScraperCrawler() {
        XpathName = "";
        XpathLink = "";
        XpathPrice = "";
        XpathImageUrl = "";
        XpathPagination = "";
        Links = null;
        category = null;
    }

    public void setXpathName(String Name) {
        this.XpathName = Name;
    }

    public void setXpathLink(String Link) {
        this.XpathLink = Link;
    }

    public void setXpathPrice(String Price) {
        this.XpathPrice = Price;
    }

    public void setXpathImageUrl(String ImageUrl) {
        this.XpathImageUrl = ImageUrl;
    }

    public void setXpathPagination(String Pagination) {
        this.XpathPagination = Pagination;
    }

    public void setLinks(String[] scrapingLinks) {
        this.Links = scrapingLinks;
    }

    public void setCategory(String[] linkCategory) {
        this.category = linkCategory;
    }
/**
 *
 * @author cosar
 */


    public void scrapeCrawlAmazon(String link,String xpathName,String xpathLink,String xpathPrice,String xpathImageUrl,String xpathPagination) {
        //We need an options class to run headless - not needed if we want default options

        boolean nextPage = true;
        int counterPages = 0;
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.addArguments("--window-size=1920,1200");

        //Create instance of web driver - this must be on the path.
        WebDriver driver = new ChromeDriver(options);

        //Navigate Chrome to page.
        driver.get(link);

        //Wait for page to load
        while (nextPage == true) {
            try {
                Thread.sleep(3000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            int count = 0;
            int np = 0;

            List<WebElement> productNameList = driver.findElements(By.xpath(xpathName));
            List<WebElement> productLinkList = driver.findElements(By.xpath(xpathLink));
            List<WebElement> productPriceList = driver.findElements(By.xpath(xpathPrice));
            List<WebElement> productImageUrlList = driver.findElements(By.xpath(xpathImageUrl));
            try {

                for (WebElement product : productNameList) {
                    System.out.println("this is the element title: " + product.getText());
                    Thread.sleep(500);
                    System.out.println("The link is: " + productLinkList.get(count).getAttribute("href"));
                    Thread.sleep(500);
                    System.out.println("this is the element price: " + productPriceList.get(np).getText() + productPriceList.get(np + 1).getText() + "." + productPriceList.get(np + 2).getText());
                    Thread.sleep(500);
                    System.out.println("The link for the image is: " + productImageUrlList.get(count).getAttribute("src"));
                    Thread.sleep(500);
                    count++;
                    np = np + 3;
                }

            } catch (Exception e) {
                System.out.println("error");
            }

            System.out.println("Number of Products:" + count);
            //Exit driver and close Chrome
            counterPages++;

            try {
                driver.findElement(By.xpath(xpathPagination)).click();
            } catch (Exception e) {
                System.out.println("No page found within the pagination");
                nextPage = false;
            }

        }
        driver.quit();
    }
}
