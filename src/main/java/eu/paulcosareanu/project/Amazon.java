/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.paulcosareanu.project;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author cosar
 */
/**
 * cancelled scraper due to website change
 * @author cosar
 */
public class Amazon extends Thread {

  private int crawlDelay = 1;
    volatile private boolean runThread = false;
   // String[] links;
//    ScraperCrawler xpaths;
    
//          String[] links = {"
//        };
    /**
     * Demonstrates use of ChromeDriver with Selenium
     */
    @Override
    /**
     * runs the thread
     */
    public void run() {
        runThread = true;
        while (runThread) {
           /**
            * gets data from beans and creates scraper
            */
                ApplicationContext context= new ClassPathXmlApplicationContext("Beans.xml");
                ScraperCrawler scraperCrawler = (ScraperCrawler) context.getBean("AmazonScrape");   //getBean("tescoScrape");
                scraperCrawler.scrapeCrawlMain();
                try {
                    Thread.sleep(5000 * crawlDelay);
                } catch (InterruptedException ex) {
                    System.err.println(ex.getMessage());
                }
            

        }
    }
/**
 * stops the thread
 */
    public void stopThread() {
        runThread = false;
    }
}


