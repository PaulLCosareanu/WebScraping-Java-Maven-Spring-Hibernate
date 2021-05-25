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
public class SellersCheeses {
    private int id;
    private int sellerIdFk;
    private int cheeseIdFk;
    
    public SellersCheeses(){}
    
    public int getId(){
    return id;
    }
    public int getSellerIdFk(){
    return sellerIdFk;
    }
    public int getCheeseIdFk(){
    return cheeseIdFk;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setSellerIdFk(int sellerIdFk){
    this.sellerIdFk=sellerIdFk;
    }
    public void setCheeseIdFk(int cheeseIdFk){
    this.cheeseIdFk=cheeseIdFk;
    }
}
