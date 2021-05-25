/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.paulcosareanu.project;

/**
 *
 * @author cosar
 */
public class MilkComparison {
    private int id;
    private String url;
    private float price;
    private String retailer;
    private int milkId;
    private String imageUrl;
    private String volume;
     private String searchString;
    /**
     * empty constructor
     */
    public MilkComparison(){}
    /**
     * getter
     * @return serchString used in search querrys 
     */
    public String getSearchString(){
    return searchString;
    }
    /**
     * getter
     * @return milk_coparison id
     */
    public int getId(){
    return id;
    }
   /**
    * getter
    * @return volume 
    */
     public String getVolume(){
    return volume;
    }
     /**
      * getter
      * @return url
      */
    public String getUrl(){
    return url;
    }
    /**
     * getter
     * @return price
     */
    public float getPrice(){
    return price;
    }
    /**
     * getter
     * @return  retailer
     */
    public String getRetailer(){
    return retailer;
    }
    /**
     * getter
     * @return milk id (foreign key which binds milk and milk_comparison) 
     */
    public int getMilkId(){
    return milkId;
    }
    /**
     * getter
     * @return  image url
     */
    public String getImageUrl(){
    return imageUrl;
    }
    /**
     * setter
     * @param volume 
     */
     public void setVolume(String volume){
    this.volume=volume;
    }
     /**
      * setter
      * @param searchString 
      */
     public void setSearchString(String searchString){
    this.searchString=searchString;
    }
     /**
      * setter
      * @param id 
      */
    public void setId(int id){
    this.id=id;
    }
    /**
     * setter
     * @param Url 
     */
    public void setUrl(String Url){
    this.url=Url;
    }
    /**
     * setter
     * @param price 
     */
    public void setPrice(float price){
    this.price=price;
    }
    /**
     * setter
     * @param retailer 
     */
    public void setRetailer(String retailer){
    this.retailer=retailer;
    }
    /**
     * setter
     * @param milk_id 
     */
    public void setMilkId(int milk_id){
    this.milkId=milk_id;
    }
    /**
     * setter
     * @param img 
     */
    public void setImageUrl(String img){
    this.imageUrl=img;
    }
    /**
     * ovverrides tostring method to properly display products
     * @param variable
     * @return 
     */
    public String toString(String variable){
    
        if(variable=="url"){
        return url;
        }else if(variable=="Price"){
        return String.valueOf(price);
        }else if(variable=="retailer"){
        return retailer;
        }else if(variable=="milk_id"){
            return String.valueOf(milkId);
        }else
        return String.valueOf(id);
    }
}

