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

public class CheesesComparison {
    private int id;
    private String url;
    private float price;
    private String retailer;
    private int cheeseId;
    String imageUrl;
    private String weight;
     private String searchString;
    /**
     * empty constructor
     */
    public CheesesComparison(){}
    /**
     * getter
     * @return cheese_comparison id
     */
    public int getId(){
    return id;
    }
    /**
     * getter
     * @return the string used for the search method
     */
     public String getSearchString(){
    return searchString;
    }
     /**
      * getter
      * @return weight of cheese 
      */
     public String getWeight(){
    return weight;
    }
     /**
      * getter
      * @return url of the cheese
      */
    public String getUrl(){
    return url;
    }
    /**
     * getter
     * @return price of the cheese 
     */
    public float getPrice(){
    return price;
    }
    /**
     * getter
     * @return retailer of the cheese 
     */
    public String getRetailer(){
    return retailer;
    }
    /**
     * getter
     * @return cheeseId, foreign key binding cheeses and cheeses_comparison together 
     */
    public int getCheeseId(){
    return cheeseId;
    }
    /**
     * getter
     * @return the image url 
     */
    public String getImageUrl(){
    return imageUrl;
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
     * @param searchString 
     */
     public void setSearchString(String searchString){
    this.searchString=searchString;
    }
     /**
      * setter
      * @param weight 
      */
    public void setWeight(String weight){
    this.weight=weight;
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
     * @param cheese_id 
     */
    public void setCheeseId(int cheese_id){
    this.cheeseId=cheese_id;
    }
    /**
     * setter
     * @param img 
     */
    public void setImageUrl(String img){
    this.imageUrl=img;
    }
    /**
     * override of toString method in order to correctly display data
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
            return String.valueOf(cheeseId);
        }else
        return String.valueOf(id);
    } 
}
