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
public class MilkTags {
    private int id;
    private int milkIdFk;
    private int tagIdFk;
    
    public MilkTags(){}
    
    public int getId(){
    return id;
    }
    public int getMilkIdFk(){
    return milkIdFk;
    }
    public int getTagIdFk(){
    return tagIdFk;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setMilkIdFk(int milkIdFk){
    this.milkIdFk=milkIdFk;
    }
    public void setTagIdFk(int tagIdFk){
    this.tagIdFk=tagIdFk;
    }
}
