/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.paulcosareanu.project;
//imports
import static eu.paulcosareanu.project.StringSplitter.*;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.JavascriptExecutor;

/**
 *
 * @author cosar
 */
public class ScraperCrawler extends Thread {
    //private variables
    private ReentrantLock lock=new ReentrantLock();
    private String XpathName;
    private String XpathLink;
    private String XpathPrice;
    private String XpathImageUrl;
    private String XpathPagination;
    private String[] Links;
    private String seller;
    private String XpathBrand;
    private String categoryButton;
    private String volumeXpath;
//empty constructor
    /**
     * empty constructor used for beans, beans is used to parse data into this scraper algorithm
     */
    public ScraperCrawler() {
        XpathName = "";
        XpathLink = "";
        XpathPrice = "";
        XpathImageUrl = "";
        XpathPagination = "";
        XpathBrand="";
        Links = null;
        seller = "";
    }
//setters
    /**
     * setter for beans
     * @param Name  holds the xpath for the name of the product
     */
    public void setXpathName(String Name) {
        this.XpathName = Name;
    }
/**
 * setter for link
 * @param Link xpath link for the url of the product
 */
    public void setXpathLink(String Link) {
        this.XpathLink = Link;
    }
/**
 * xpath for the price of the product
 * @param Price 
 */
    public void setXpathPrice(String Price) {
        this.XpathPrice = Price;
    }
/**
 * xpath for the image of the product
 * @param ImageUrl 
 */
    public void setXpathImageUrl(String ImageUrl) {
        this.XpathImageUrl = ImageUrl;
    }
/**
 * xpath for the buttons from the page which hold the pagination
 * @param Pagination 
 */
    public void setXpathPagination(String Pagination) {
        this.XpathPagination = Pagination;
    }
/**
 * lists of links to scrape
 * @param scrapingLinks 
 */
    public void setLinks(String[] scrapingLinks) {
        this.Links = scrapingLinks;
    }
/**
 * seller details (each scraper has a seller which sells all the products, in this case, according to the scraper, the seller is parsed here from beans)
 * @param Seller 
 */
    public void setSeller(String Seller) {
        this.seller = Seller;
    }
    /**
     * xpath for product brand(producer of the product)
     * @param brand 
     */
    public void setXpathBrand(String brand){
        this.XpathBrand=brand;
    }
    /**
     * xpath to the button from the pages where category is, the button needs to be pressed whilst scraping, otherwise data won't scrape correctly, thus this xpath is used within a webcrawler
     * @param button 
     */
    public void setCategoryButton(String button){
        this.categoryButton=button;
    }
    /**
     *  xpath for the quantity or volume of the product
     * @param vol 
     */
    public void setVolumeXpath(String vol){
        this.volumeXpath=vol;
    }
    /**
     *  main scraper function
     */
    public void scrapeCrawlMain() {
        /**
         * for each link in the beans file
         */
        for (int i = 0; i < Links.length; i++) {
            boolean nextPage = true;
            int counterPages = 0;
            int crawlDelay = 1;
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(false);
            //Create instance of web driver - this must be on the path.
            WebDriver driver = new ChromeDriver(options);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            driver.manage().window().maximize();
            String xPathCookie="//button[@id='onetrust-accept-btn-handler']";
            
            //Navigate Chrome to page.
            driver.get(Links[i]);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ScraperCrawler.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Wait for page to load
            while (nextPage == true) {
                try {
                    Thread.sleep(3000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                int count = 0;
                int np = 0;
                String quantity="";
                try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ScraperCrawler.class.getName()).log(Level.SEVERE, null, ex);
            }
               /**
                * if the website scraped belongs to Sainsbury then cookies are accepted by implementing driver.click() function on xpath
                */
            if(seller.contains("Sainsbury")){
                       driver.findElement(By.xpath(xPathCookie)).click();
                       }
            /**
             * get lists of all elements within the page
             */
                List<WebElement> productNameList = driver.findElements(By.xpath(XpathName));
                List<WebElement> productLinkList = driver.findElements(By.xpath(XpathLink));
                List<WebElement> productPriceList = driver.findElements(By.xpath(XpathPrice));
                List<WebElement> productImageUrlList = driver.findElements(By.xpath(XpathImageUrl));
                List<WebElement>productBrand=driver.findElements(By.xpath(XpathBrand));
                List<WebElement>productVolume=driver.findElements(By.xpath(volumeXpath));
                /**
                 * extract brand 
                 */
                try {
                    driver.findElement(By.xpath(categoryButton)).click();
                    for (WebElement product : productNameList) {
                        String brand="";
                        for(WebElement Brand:productBrand){
                        if(splitBrand(product.getText(),Brand.getText())==true){
                           String pattern="";
                            if(seller.contains("Asda")){
                              pattern = "((\\w+)(\\s+))+";
//                          int price=0;
                            }else{
                             pattern="(\\w+)";
                            }

        // Create a Pattern object
                            
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(Brand.getText());
        if (m.find()) {
            brand = m.group();

        } else {
            System.out.println("NO MATCH");
        }

                            
                           
                            System.out.println(brand);
//                            System.out.println(Brand.getText());
                        }
                        }
                        /**
                         * extract quantity
                         */
                        String type=splitType(product.getText());
                        if(seller.contains("Asda")){
                        quantity=productVolume.get(count).getText();
                       
                        }else{
                        quantity=splitQuantity(product.getText());
                         
                        }
                        /**
                         * display all data scraped thread.sleep is required as some of the websites won't scrape unless each product is loaded up, thus scrolling the page and waiting ensures that data is gathered
                         */
                        System.out.println("this is the element title: " + product.getText());
                        Thread.sleep(500);
                        System.out.println("The link is: " + productLinkList.get(count).getAttribute("href"));
                        Thread.sleep(500);
                        System.out.println("this is the element price: " + productPriceList.get(np).getText());
                        Thread.sleep(500);
                        System.out.println("The link for the image is: " + productImageUrlList.get(count).getAttribute("src"));
                        Thread.sleep(500);
//                        System.out.println(productVolume.get(count).getText());
                        
                        /**
                         * if the websites scraped are tesco and sainsbury then the webpage scrolls 150 px after every product scraped, this ensures that correct data is scraped(if not done, some data may be received as bit64 data)
                         */
                       if(seller.contains("Tesco")||seller.contains("Sainsbury")){
                        js.executeScript("window.scrollBy(0,150)");
                     }
                       
//                        if(seller=="Tesco"){
//                        js.executeScript("document.getElementByClassName('button-text filter-select filter-options-open').setAttribute('aria-expanded', true);");
//                        }
                        
                        System.out.println("The category is: " + seller);
                       /**
                        * the databased functions are locked, if a thread starts adding data, the function gets locked, other threads having to wait for the current lock to unlock.
                        */
                        lock.lock();
                        //Example operations
                        /**
                         * database operations start
                         */
                        databaseOperations hibernateXmlExample = new databaseOperations();
                          String pattern = "(\\d+)(.\\d+)";
                          float price=0;

      // Create a Pattern object
      Pattern r = Pattern.compile(pattern);

      // Now create matcher object.
      Matcher m = r.matcher(productPriceList.get(np).getText());
      if (m.find( )) {
         System.out.println("Found value: " + m.group() );
         price=Float.parseFloat(m.group());
      }else {
         System.out.println("NO MATCH");
      }
                        //Set up the SessionFactory
                        hibernateXmlExample.init();
                        //add data if milk
                        /**
                         * add data if milk
                         */
                        if(type.contains("milk")||type.contains("Milk")){
                            int idMilk=0;
                            int idMilkComp=0;
                            idMilk=hibernateXmlExample.checkProduct(brand, type);
                            if(idMilk==0){
                            hibernateXmlExample.addCereal(quantity,brand,type,productLinkList.get(count).getAttribute("href"),productImageUrlList.get(count).getAttribute("src"),price,seller);//Add data
                            }else{
                            idMilkComp=hibernateXmlExample.checkProductCompare(quantity,idMilk, seller);
                            if(idMilkComp!=0){
                                hibernateXmlExample.updateProductComparison(quantity,brand,type,productLinkList.get(count).getAttribute("href"),productImageUrlList.get(count).getAttribute("src"),price,seller, idMilkComp,idMilk);
                            }else if(idMilkComp==0){
                                hibernateXmlExample.addProductComparison(brand,type,quantity,idMilk, productLinkList.get(count).getAttribute("href"), productImageUrlList.get(count).getAttribute("src"), price, seller);
                            }
                            }
                            /**
                             * add data if cheese
                             */
                        } else if(type.contains("cheese")||type.contains("Cheese")||type.contains("Mozzarella")||type.contains("mozzarella")||type.contains("stilton")||type.contains("Stilton")||type.contains("blue")||type.contains("Blue")||type.contains("cottage")||type.contains("Cottage")){//add data if cheese
                            int idCheese=0;
                            int idCheeseComp=0;
                            idCheese=hibernateXmlExample.checkProductCheese(quantity, brand, type);
                            if(idCheese==0){
                            hibernateXmlExample.addCheese(quantity,brand,type,productLinkList.get(count).getAttribute("href"),productImageUrlList.get(count).getAttribute("src"),price,seller);//Add data
                            }else{
                            idCheeseComp=hibernateXmlExample.checkProductCompareCheese(quantity,idCheese, seller);
                            if(idCheeseComp!=0){
                                hibernateXmlExample.updateProductComparisonCheese(quantity,brand,type,productLinkList.get(count).getAttribute("href"),productImageUrlList.get(count).getAttribute("src"),price,seller, idCheeseComp,idCheese);
                            }else if(idCheeseComp==0){
                                hibernateXmlExample.addProductComparisonCheese(brand,type,quantity,idCheese, productLinkList.get(count).getAttribute("href"), productImageUrlList.get(count).getAttribute("src"), price, seller);
                            }
                            }
                        }
//                            }else{
//                        hibernateXmlExample.addCheese(quantity,brand,type,productLinkList.get(count).getAttribute("href"),productImageUrlList.get(count).getAttribute("src"),1,"Tesco");
//                        }
//                       hibernateXmlExample.checkMilkExists(product.getText(),productLinkList.get(count).getAttribute("href"),"Tesco");//
//                       hibernateXmlExample.addSeller();
//                       hibernateXmlExample.searchCereals();
                        hibernateXmlExample.shutDown();
//                      Thread.sleep(5000);
//                      databaseOperations hibernateXmlExample2 = new databaseOperations();
//                       hibernateXmlExample2.init();
////                       hibernateXmlExample.addCereal(productLinkList.get(count).getAttribute("href"),product.getText());//Add data
//                       hibernateXmlExample2.searchCereals();
//                      hibernateXmlExample2.shutDown();
                        count++;
                        np++;
                        lock.unlock();
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }

                System.out.println("Number of Products:" + count);
                //Exit driver and close Chrome
                counterPages++;
                /**
                 * if there is any pagination available, the crawler will click the next button
                 */
                try {
                    driver.findElement(By.xpath(XpathPagination)).click();
                } catch (Exception e) {
                    System.out.println("No page found within the pagination");
                    nextPage = false;
                }

            }
            driver.quit();
            try {
                Thread.sleep(5000 * crawlDelay);
            } catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
}
