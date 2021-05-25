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
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 *
 * @author cosar
 */



/**
 *
 * @author cosar
 */
public class Tesco extends Thread {

    private int crawlDelay = 1;
    volatile private boolean runThread = false;
  
    @Override
    /**
     * run thread
     */
    public void run() {
        runThread = true;
        while (runThread) {
           
                ApplicationContext context= new ClassPathXmlApplicationContext("Beans.xml");
                ScraperCrawler scraperCrawler = (ScraperCrawler) context.getBean("tescoScrape");   //getBean("tescoScrape");
                scraperCrawler.scrapeCrawlMain();
                try {
                    Thread.sleep(5000 * crawlDelay);
                } catch (InterruptedException ex) {
                    System.err.println(ex.getMessage());
                }
            

        }
    }
/**
 * stop thread
 */
    public void stopThread() {
        runThread = false;
    }
}


