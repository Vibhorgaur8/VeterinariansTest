package com.veterinarians.com;

import org.testng.annotations.Test;

import com.pageobjects.com.VeterinariansApp;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

/**
* The VeterinariansAppTests contains test cases for testing
* Veterinarians application.
*
* @author  Vibhor Gaur
* @version 1.0
* @since   14-11-2017
*/
public class VeterinariansAppTests {

	Hashtable<String,String> Owner = new Hashtable<String, String>();
	Hashtable<String,String> Pet = new Hashtable<String, String>();
	VeterinariansApp veterinariansApp;
	
	String applicationName = "PetClinic :: a Spring Framework demonstration";
	String imageBorderOutset = "0";
	String imageBorderRepeat = "stretch stretch";
	String imageBorderSlice = "100%";
	String tabNameFindOwners = "FIND OWNERS";
	String tabNameVeterinarians = "VETERINARIANS";

	@BeforeClass
	public void setUp() throws IOException{
		veterinariansApp = new VeterinariansApp();
		Assert.assertEquals(applicationName, veterinariansApp.driver.getTitle());
	}

	@AfterClass
    public void tearDown() {
    	veterinariansApp.tearDown();
    }

	/*
	 * Verify image on home page.
	 */
    @Test(enabled=true,priority=0)
    public void testIfImagePresentOnHomePage(){
    	Hashtable<String,String> imgAttributes = veterinariansApp.getHomePageImageAttributes();
    	String imageSrc = veterinariansApp.url + "resources/images/pets.png";
    	Assert.assertEquals(imageSrc, imgAttributes.get("src"));
    	Assert.assertEquals(imageBorderOutset, imgAttributes.get("border-image-outset"));
    	Assert.assertEquals(imageBorderRepeat, imgAttributes.get("border-image-repeat"));
    	Assert.assertEquals(imageBorderSlice, imgAttributes.get("border-image-slice"));
    }

    /*
     * Find all the veterinarians which are added in the application.
     * And verify that veterinarian name is a non empty string.
     */
    @Test(enabled=true,priority=1)
    public void testAllVeterinariansInApp() {
    	String tabName = veterinariansApp.openVeterinariansTab();
    	Assert.assertEquals(tabNameVeterinarians, tabName);
    	List<String> allNames = veterinariansApp.getAllVeterinariansNames();
    	for (String names : allNames){
    		Assert.assertTrue(names != "", "One of the name is an empty string.");
    	}
    }

    /*
     * Find all the existing owners which are added in application.
     * And verify that owner name is a non an empty string.
     */
    @Test(enabled=true,priority=2)
    public void testAllOwnersInApp() {
    	String tabName = veterinariansApp.openOwnersTab();
    	Assert.assertEquals(tabNameFindOwners, tabName);
    	List<String> allNames = veterinariansApp.getAllOwnersNames();
    	for (String names : allNames){
    		Assert.assertTrue(names != "", "One of the name is an empty string.");
    	}
    }

    /*
     * Add a new owner, add pet for that owner.
     */
    @Test(enabled=true,priority=3)
    public void testAddNewOwnerAndPet() {
    	Owner.put("firstName", "Quality");
    	Owner.put("lastName", "Analyst");
    	Owner.put("address", "Sweet Home");
    	Owner.put("city", "Gurugram");
    	Owner.put("telephone", "123456789");
    	veterinariansApp.addOwner(Owner);
    	Pet.put("name", "Buldu");
    	Pet.put("birthdate", "2013-06-07");
    	Pet.put("type", "dog");
    	veterinariansApp.addPet(Pet);
    }

    /*
     * Check all the information added for the newly created owner and pet is correct
     */
    @Test(enabled=true,dependsOnMethods = { "testAddNewOwnerAndPet" },priority=4)
    public void testOwnerAndPetAddedDetails(){
    	List<String> ownerInformation = veterinariansApp.getRecentlyAddedOwnerDetails();
    String ownerName = Owner.get("firstName") + " " + Owner.get("lastName");
    	Assert.assertEquals(ownerName, ownerInformation.get(0));
    	Assert.assertEquals(Owner.get("address"), ownerInformation.get(1));
    	Assert.assertEquals(Owner.get("city"), ownerInformation.get(2));
    	Assert.assertEquals(Owner.get("telephone"), ownerInformation.get(3));
    	Assert.assertEquals(Pet.get("name"), ownerInformation.get(4));
    	Assert.assertEquals(Pet.get("birthdate"), ownerInformation.get(5));
    	Assert.assertEquals(Pet.get("type"), ownerInformation.get(6));
    }
}
