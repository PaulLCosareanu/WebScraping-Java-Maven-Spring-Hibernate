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
public class MilkSellers {
     private int id;
    private int milkIdFk;
    private int sellerIdFk;
    
    public MilkSellers(){}
    
    public int getId(){
    return id;
    }
    public int getMilkIdFk(){
    return milkIdFk;
    }
    public int getSellerIdFk(){
    return sellerIdFk;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setMilkIdFk(int milkIdFk){
    this.milkIdFk=milkIdFk;
    }
    public void setSellerIdFk(int sellerIdFk){
    this.sellerIdFk=sellerIdFk;
    }

}
