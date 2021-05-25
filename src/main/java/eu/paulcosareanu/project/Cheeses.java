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
public class Cheeses {
    private int id;
    
    private String type;
    private String brand;
   
    /**
     * empty constructor
     */
    public Cheeses(){}
    
   /**
    * getter
    * @return id of cheese 
    */
       public int getId(){
    return id;
    }
   /**
    * getter
    * @return brand of milk
    */
    public String getBrand(){
    return brand;
    }
    /**
     * getter
     * @return type of milk
     */
    public String getType(){
    return type;
    }
    /**
     * setter
     * @param id set milk id
     */
    public void setId(int id){
        this.id=id;
    }
    /**
     * setter
     * @param brand  set brand
     */
    public void setBrand(String brand){
    this.brand=brand;
    }
    /**
     * setter
     * @param type  sets the type of milk
     */
    public void setType(String type){
        this.type=type;
    }
    /**
     * override of method toString, if no override, data cannot be taken out with toString method
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
