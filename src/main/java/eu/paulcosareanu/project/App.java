package eu.paulcosareanu.project;

import java.util.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author cosar
 */
public class App {
/**
 * main application
 * @param args 
 */
    public static void main(String[] args) {
        
        
        /**
         * 1st method of creating scrapers
         */
//        Amazon AmazonScrape = new Amazon();
//        AmazonScrape.start();
//        These are syncronised, the method for saving data is sincronisez =, forcing the scrapers to save once at a time
//        Tesco TescoScrape=new Tesco();
//        Sainsbury SainsburyScrape=new Sainsbury();
//        TescoScrape.start();
//        SainsburyScrape.start();
//        Asda asdaScrape=new Asda();
//        asdaScrape.start();
        
//        Scanner scanner = new Scanner(System.in);
//        String userInput = scanner.nextLine();
//        while (!userInput.equals("stop")) {
//            userInput = scanner.nextLine();
//        }
        //        TescoScrape.stopThread();
//        AmazonScrape.stopThread();
//        asdaScrape.stopThread();
//        SainsburyScrape.stopThread();
        
        /**
         * second method of creating scrapers
         */
        ApplicationContext context= new ClassPathXmlApplicationContext("Beans.xml");
        website scraper = (website) context.getBean("Sainsbury");   //getBean("tescoScrape");
        website scraper2 = (website) context.getBean("Tesco");
        website scraper3 = (website) context.getBean("Asda");
        scraper.start();
        scraper2.start();
        scraper3.start();
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (!userInput.equals("stop")) {
            userInput = scanner.nextLine();
        }
        scraper.stopThread();
        scraper2.stopThread();
        scraper3.stopThread();

        
        System.out.println("!!!!!!!!!The Web Scraper is running its last loop!!!!!!!!!The Web Scraper is running its last loop!!!!!!!!!The Web Scraper is running its last loop!!!!!!!!!The Web Scraper is running its last loop!!!!!!!!!");
        System.out.println("!!!!!!!!!If you want to break the last loop, press control c (CTRL C) !!!!!!!!!If you want to break the last loop, press control c (CTRL C)!!!!!!!!!If you want to break the last loop, press control c (CTRL C)!!!!!!!!!");
    }
}
