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
public class website extends Thread{
    private String scraper;
    private int crawlDelay = 1;
    volatile private boolean runThread = false;
    /**
     * empty constructor
     */
    public website(){
    }
    /**
     * setter
     * @param scraper (parameter which points which bean should be used within beans xml
     */
    public void setScraper(String scraper){
    this.scraper=scraper;
    }
 
    @Override
    /**
     * start thread method 2
     */
    public void run() {
        runThread = true;
        while (runThread) {
           
                ApplicationContext context= new ClassPathXmlApplicationContext("Beans.xml");
                ScraperCrawler scraperCrawler = (ScraperCrawler) context.getBean(scraper);   //getBean("tescoScrape");
                scraperCrawler.scrapeCrawlMain();
                try {
                    Thread.sleep(5000 * crawlDelay);
                } catch (InterruptedException ex) {
                    System.err.println(ex.getMessage());
                }
            

        }
    }
/**
 * stop thread method 2
 */
    public void stopThread() {
        runThread = false;
    }
}