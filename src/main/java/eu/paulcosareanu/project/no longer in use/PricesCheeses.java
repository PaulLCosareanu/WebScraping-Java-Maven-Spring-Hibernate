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
public class PricesCheeses {
    private int id;
    private int priceIdFk;
    private int cheeseIdFk;
    
    public PricesCheeses(){}
    
    public int getId(){
    return id;
    }
    public int getPriceIdFk(){
    return priceIdFk;
    }
    public int getCheeseIdFk(){
    return cheeseIdFk;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setPriceIdFk(int sellerIdFk){
    this.priceIdFk=sellerIdFk;
    }
    public void setCheeseIdFk(int cheeseIdFk){
    this.cheeseIdFk=cheeseIdFk;
    }
}
