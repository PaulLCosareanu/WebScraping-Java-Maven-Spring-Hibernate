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
public class Milk {
    private int id;
    
    private String brand;
    private String type;
   
    /**
     * empty constructor
     */
    public Milk(){
   
    }
    
    /**
     * getter
     * @return milk id 
     */
    public int getId(){
    return id;
    }
   /**
    * getter
    * @return  brand 
    */
    public String getBrand(){
    return brand;
    }
    /**
     * getter
     * @return  type 
     */
    public String getType(){
    return type;
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
    * @param brand 
    */
    public void setBrand(String brand){
    this.brand=brand;
    }
    /**
     * setter
     * @param type 
     */
    public void setType(String type){
        this.type=type;
    }
    /**
     * ovverrides tostring method to properly display products to string
     * @param variable
     * @return 
     */
    public String toString(String variable){
    
       if(variable=="brand"){
        return brand;
        }else if(variable=="type"){
        return type;
        }else
        return String.valueOf(id);
    }
}
