package eu.paulcosareanu.project;

import static eu.paulcosareanu.project.StringSplitter.splitBrand;
import static eu.paulcosareanu.project.StringSplitter.splitQuantity;
import static eu.paulcosareanu.project.StringSplitter.splitType;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    /**
     *  tests if the correct type is returned
     */
    public void testTypeOfProduct()
    { 
        
        String strTest="Tesco Semi-Skimmed Milk";
        Assert.assertEquals(splitType(strTest),"Semi Skimmed Milk ");
        strTest="Tesco Whole Milk";
        Assert.assertEquals(splitType(strTest),"Whole Milk ");
        strTest="Tesco Organic Milk";
        Assert.assertEquals(splitType(strTest),"Organic Milk ");
        strTest="Blue Cheese Tesco Sainsbury 550g Asda";
        Assert.assertEquals(splitType(strTest),"Blue Cheese ");
        strTest="Mozzarella Blue Cottage Cheese Milk Whole Skimmed price cat lada";
        Assert.assertEquals(splitType(strTest),"Mozzarella Blue Cottage Cheese Milk Whole Skimmed ");
        
    }
    @Test
    /**
     * tests if the correct brand is returned
     */
   public void testBrandOfProduct()
    { 
        String brand="Tesco";
        String strTest="Tesco Asda Sainsbury Semi-Skimmed Milk";
        assertTrue(splitBrand(strTest,brand));
        brand="Asda";
        assertTrue(splitBrand(strTest,brand));
        brand="Sainsbury";
        assertTrue(splitBrand(strTest,brand));
    }
   
   @Test
   /**
    * tests if the correct quantity is returned
    */
   public void testQuantityOfProduct(){
   String strTest="Tesco Asda Sainsbury Semi-Skimmed Milk 2 Pints";
        Assert.assertEquals(splitQuantity(strTest),"2 Pints");
        strTest="Tesco Asda Sainsbury Semi-Skimmed Milk 550ml";
        Assert.assertEquals(splitQuantity(strTest),"550ml");
        strTest="Tesco Asda Sainsbury Semi-Skimmed Milk 1 Litre";
        Assert.assertEquals(splitQuantity(strTest),"1 Litre");
        strTest="Tesco Asda Sainsbury Semi-Skimmed Milk 55l";
        Assert.assertEquals(splitQuantity(strTest),"55l");
   }
   @Test
/**
 * tests if the connection to the database works
 */
   public void testConnectionToDatabase(){
   databaseOperations hibernateXmlExample = new databaseOperations();
   Assert.assertEquals(hibernateXmlExample.init(),true);
   hibernateXmlExample.shutDown();
   
   }
    @Test
    /**
     * tests if database operations work
     */
    public void addUpdateDatabase(){ //database will be updated if the same test variables will be saved 
        String brand="test";
        String type="test";
        String url="test";
        String imageUrl="test";
        float price=0;
        String retailer="test";
        String milk_id="test";
        String volume="test";
        
        databaseOperations hibernateXmlExample = new databaseOperations();
        hibernateXmlExample.init();
        int idMilk=0;
                            int idMilkComp=0;
                            idMilk=hibernateXmlExample.checkProduct(brand, type);
                            if(idMilk==0){
                            Assert.assertEquals(hibernateXmlExample.addCereal(volume,brand,type,url,imageUrl,price,retailer),true);//Add data
                            }else{
                            idMilkComp=hibernateXmlExample.checkProductCompare(volume,idMilk, retailer);
                            if(idMilkComp!=0){
                                Assert.assertEquals(hibernateXmlExample.updateProductComparison(volume,brand,type,url,imageUrl,price,retailer, idMilkComp,idMilk),true);
                            }else if(idMilkComp==0){
                                Assert.assertEquals(hibernateXmlExample.addProductComparison(brand,type,volume,idMilk,url, imageUrl, price, retailer),true);
                            }
                            }
                            hibernateXmlExample.shutDown();
}
}