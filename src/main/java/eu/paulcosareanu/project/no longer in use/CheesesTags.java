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
public class CheesesTags {
     private int id;
    private int tagIdFk;
    private int cheeseIdFk;
    
    public CheesesTags(){}
    
    public int getId(){
    return id;
    }
    public int getTagIdFk(){
    return tagIdFk;
    }
    public int getCheeseIdFk(){
    return cheeseIdFk;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setTagIdFk(int tagIdFk){
    this.tagIdFk=tagIdFk;
    }
    public void setCheeseIdFk(int cheeseIdFk){
    this.cheeseIdFk=cheeseIdFk;
    }
}
