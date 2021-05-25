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
public class Sellers {
     private int id;
    private String sellerName;
  
    
    public Sellers(){}
    
    public int getId(){
    return id;
    }
    public String getSellerName(){
    return sellerName;
    }
    
    
    public void setId(int id){
        this.id=id;
    }
    public void setSellerName(String sellerName){
    this.sellerName=sellerName;
    }
    
}
