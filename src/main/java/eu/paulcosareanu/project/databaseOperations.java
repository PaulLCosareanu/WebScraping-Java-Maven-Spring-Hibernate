/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.paulcosareanu.project;

import java.util.List;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import java.util.ArrayList;
import java.util.*;

/**
 *
 * @author cosar
 */
public class databaseOperations {

    /**
     * Simple Hibernate example that uses XML files to specify the mapping
     * between a Cereal object and the cereals table in the price_comparison
     * database.
     */
    //Use this class to create new Sessions to interact with the database 
    private SessionFactory sessionFactory;

    /**
     * Empty constructor
     */
    databaseOperations() {
    }

    /**
     * Sets up the session factory. Call this method first.
     */
    public boolean init() {
        boolean built=false;
        try {
            //Create a builder for the standard service registry
            StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();

            //Load configuration from hibernate configuration file
            standardServiceRegistryBuilder.configure("main/resources/hibernate.cfg.xml");

            //Create the registry that will be used to build the session factory
            StandardServiceRegistry registry = standardServiceRegistryBuilder.build();

            try {
                //Create the session factory - this is the goal of the init method.
                sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            } catch (Exception e) {
                /* The registry would be destroyed by the SessionFactory, 
                 but we had trouble building the SessionFactory, so destroy it manually */
                System.err.println("Session Factory build failed.");
                e.printStackTrace();
                StandardServiceRegistryBuilder.destroy(registry);
            }

            //Ouput result
            System.out.println("Session factory built.");
            built=true;

        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("SessionFactory creation failed." + ex);
        }
        return built;
    }

    /**
     * Closes Hibernate down and stops its threads from running
     */
    public void shutDown() {
        sessionFactory.close();
    }

    
    
    
    /**
     * Adds a new milk product to the database
     */
    public boolean addCereal(String volume, String brand, String type, String url, String imgUrl, float price, String retailer) {
        //Get a new Session instance from the SessionFactory
        Session session = sessionFactory.getCurrentSession();
        boolean added=false;
        //Start transaction
        session.beginTransaction();

        Milk milk = new Milk();
        MilkComparison mk = new MilkComparison();
        mk.setVolume(volume);
        if(brand.contains("Sainsbury")||brand.contains("Asda")||brand.contains("Tesco")||brand.contains("ASDA")){
            milk.setBrand("Own Brand");
        }else{
            milk.setBrand(brand);
        }
        
        milk.setType(type);
        mk.setPrice(price);
        mk.setRetailer(retailer);
        mk.setUrl(url);
        mk.setImageUrl(imgUrl);
        mk.setSearchString(volume + " " + brand + " " + type);

        session.save(milk);
        mk.setMilkId(milk.getId());
        session.save(mk);

        session.getTransaction().commit();
        session.close();
        System.out.println("Cereal added to database with ID: " + milk.getId());
        System.out.println("Cereal added to database with ID: " + mk.getId());
        added=true;
        return added;

    }
/**
 * checks if milk is existent within the database
 * @param brand used as a parameter to compare within the db
 * @param type used as a parameter to compare within the db
 * @return 
 */
    public int checkProduct(String brand, String type) {
        int number = 0;
        List<Milk> milkList;
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        if(brand.contains("Sainsbury")||brand.contains("Asda")||brand.contains("Tesco")||brand.contains("ASDA")){
        milkList = session.createQuery("from Milk where brand=" + "'Own Brand'" + " and type=" + "'" + type + "'").getResultList();
        }else{
            milkList = session.createQuery("from Milk where brand=" + "'" + brand + "'" + " and type=" + "'" + type + "'").getResultList();
        }
        if (milkList.size() != 0) {
            for (Milk milk : milkList) {
                number = milk.getId();
            }
        } else {
            number = 0;
        }
        session.close();
        return number;

    }
/**
 * checks if the products has the data intended to be added in the db
 * @param volume used as a parameter to compare within the db
 * @param number used as a parameter to compare within the db (id of the milk, foreign  key within milk_comparison)
 * @param retailer used as a parameter to compare within the db 
 * @return 
 */
    public int checkProductCompare(String volume, int number, String retailer) {
        int num = 0;
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<MilkComparison> mkList = session.createQuery("from MilkComparison where milkId=" + "'" + number + "'" + " and retailer=" + "'" + retailer +"' and volume='"+volume+ "'").getResultList();
        if (mkList.size() != 0) {
            for (MilkComparison mc : mkList) {
                num = mc.getId();
            }
        } else {
            num = 0;
        }
        session.close();
        return num;
    }
/**
 * updates the product in milk_comparison if already existent
 * @param volume
 * @param brand
 * @param type
 * @param url
 * @param imgUrl
 * @param price
 * @param retailer
 * @param mkId
 * @param milkId
 * @return 
 */
    public boolean updateProductComparison(String volume, String brand, String type, String url, String imgUrl, float price, String retailer, int mkId, int milkId) {
        boolean updated=false;
        Session session = sessionFactory.getCurrentSession();
        MilkComparison mk = new MilkComparison();
        System.out.println("1");
        //Set ID of cereal class - this controls the row in the table that we want to update

        mk.setId(mkId);
        System.out.println("2");

        //Set new values of Cereal class that we want to add
        mk.setPrice(price);
        mk.setVolume(volume);
        System.out.println("4");
        mk.setRetailer(retailer);
        System.out.println("5 " + url);
        mk.setUrl(url);
        System.out.println("6");
        mk.setImageUrl(imgUrl);
        System.out.println("7");
        mk.setMilkId(milkId);
        mk.setSearchString(volume + " " + brand + " " + type);

        //Start transaction
        session.beginTransaction();
        //Update database to match class 
        session.update(mk);
        System.out.println("8");
        session.getTransaction().commit();
        System.out.println("9");

        //Close the session and release database connection
        session.close();
        System.out.println("10");
        System.out.println("Cereal updated in database. ID: " + mk.getId());
        updated=true;
        return updated;
    }
/**
 * if the product scraped is in milk table but not in milk_comparison then a new entry is added
 * @param brand
 * @param type
 * @param volume
 * @param idMilk
 * @param url
 * @param imgUrl
 * @param price
 * @param retailer
 * @return 
 */
    public boolean addProductComparison(String brand,String type,String volume,int idMilk, String url, String imgUrl, float price, String retailer) {
        Session session = sessionFactory.getCurrentSession();
        boolean added=false;
        session.beginTransaction();

        MilkComparison mk = new MilkComparison();
        mk.setPrice(price);
        mk.setVolume(volume);
        mk.setRetailer(retailer);
        mk.setUrl(url);
        mk.setImageUrl(imgUrl);
        mk.setMilkId(idMilk);
        mk.setSearchString(volume + " " + brand + " " + type);
        session.save(mk);
        session.getTransaction().commit();
        session.close();
        System.out.println("Cereal added to database with ID: " + mk.getId());
        added=true;
        return added;
    }





//cheese
 /**
     * Adds a new cheese product to the database
     */

public void addCheese(String volume, String brand, String type, String url, String imgUrl, float price, String retailer) {
        //Get a new Session instance from the SessionFactory
        Session session = sessionFactory.getCurrentSession();

        //Start transaction
        session.beginTransaction();

        Cheeses cheese = new Cheeses();
        CheesesComparison ck = new CheesesComparison();
        if(brand.contains("Sainsbury")||brand.contains("Asda")||brand.contains("Tesco")||brand.contains("ASDA")){
            cheese.setBrand("Own Brand");
        }else{
            cheese.setBrand(brand);
        }
        cheese.setType(type);
        ck.setPrice(price);
        ck.setRetailer(retailer);
        ck.setUrl(url);
        ck.setImageUrl(imgUrl);
        ck.setSearchString(volume + " " + brand + " " + type);

        session.save(cheese);
        ck.setCheeseId(cheese.getId());
        session.save(ck);

        session.getTransaction().commit();
        session.close();
        System.out.println("Cereal added to database with ID: " + cheese.getId());
        System.out.println("Cereal added to database with ID: " + ck.getId());

    }
/**
 *  checks if cheese exists in the db
 * @param volume
 * @param brand
 * @param type
 * @return 
 */
    public int checkProductCheese(String volume, String brand, String type) {
        int number = 0;
        List<Cheeses> cheeseList;
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        if(brand.contains("Sainsbury")||brand.contains("Asda")||brand.contains("Tesco")||brand.contains("ASDA")){
        cheeseList = session.createQuery("from Cheeses where brand=" + "'Own Brand'" + " and type=" + "'" + type + "'").getResultList();
        }else{
        cheeseList = session.createQuery("from Cheeses where brand=" + "'" + brand + "'" + " and type=" + "'" + type + "'").getResultList();
        }
        if (cheeseList.size() != 0) {
            for (Cheeses cheese : cheeseList) {
                number = cheese.getId();
            }
        } else {
            number = 0;
        }
        session.close();
        return number;

    }
/**
 * checks if cheeses_compare has the data gathered from the scraper
 * @param volume
 * @param number
 * @param retailer
 * @return 
 */
    public int checkProductCompareCheese(String volume,int number, String retailer) {
        int num = 0;
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<CheesesComparison> ckList = session.createQuery("from CheesesComparison where cheeseId=" + "'" + number + "'" + " and retailer=" + "'" + retailer + "' and weight=" + "'" + volume + "'").getResultList();
        if (ckList.size() != 0) {
            for (CheesesComparison cc : ckList) {
                num = cc.getId();
            }
        } else {
            num = 0;
        }
        session.close();
        return num;
    }
/**
 * if data found within cheeses_comparison then that row is updated
 * @param volume
 * @param brand
 * @param type
 * @param url
 * @param imgUrl
 * @param price
 * @param retailer
 * @param mkId
 * @param milkId 
 */
    public void updateProductComparisonCheese(String volume, String brand, String type, String url, String imgUrl, float price, String retailer, int mkId, int milkId) {

        Session session = sessionFactory.getCurrentSession();
        CheesesComparison ck = new CheesesComparison();
        System.out.println("1");
        //Set ID of cereal class - this controls the row in the table that we want to update

        ck.setId(mkId);
        System.out.println("2");

        //Set new values of Cereal class that we want to add
        ck.setPrice(price);
        ck.setWeight(volume);
        System.out.println("4");
        ck.setRetailer(retailer);
        System.out.println("5 " + url);
        ck.setUrl(url);
        System.out.println("6");
        ck.setImageUrl(imgUrl);
        System.out.println("7");
        ck.setCheeseId(milkId);
        ck.setSearchString(volume + " " + brand + " " + type);

        //Start transaction
        session.beginTransaction();
        //Update database to match class 
        session.update(ck);
        System.out.println("8");
        session.getTransaction().commit();
        System.out.println("9");

        //Close the session and release database connection
        session.close();
        System.out.println("10");
        System.out.println("Cereal updated in database. ID: " + ck.getId());
    }
/**
 * if no row found in cheeses_comparison then a new row is added
 * @param brand
 * @param type
 * @param volume
 * @param idMilk
 * @param url
 * @param imgUrl
 * @param price
 * @param retailer 
 */
    public void addProductComparisonCheese(String brand,String type,String volume,int idMilk, String url, String imgUrl, float price, String retailer) {
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        CheesesComparison ck = new CheesesComparison();
        ck.setPrice(price);
        ck.setWeight(volume);
        ck.setRetailer(retailer);
        ck.setUrl(url);
        ck.setImageUrl(imgUrl);
        ck.setCheeseId(idMilk);
        ck.setSearchString(volume + " " + brand + " " + type);
        session.save(ck);
        session.getTransaction().commit();
        session.close();
        System.out.println("Cereal added to database with ID: " + ck.getId());
    }

}
