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
public class Prices {
      private int id;
    private float value;
  
    
    public Prices(){}
    
    public int getId(){
    return id;
    }
    public float getValue(){
    return value;
    }
    
    
    public void setId(int id){
        this.id=id;
    }
    public void setSellerName(float value){
    this.value=value;
    }
    
}
