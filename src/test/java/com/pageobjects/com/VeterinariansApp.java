package com.pageobjects.com;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
* The VeterinariansApp is a page object that contains
* Convenience methods for automating test scenarios of
* Veterinarians web application.
*
* @author  Vibhor Gaur
* @version 1.0
* @since   14-11-2017 
*/
public class VeterinariansApp {

	// All the constants shall be declared on the top.
	public WebDriver driver;
	int MAXTIMEOUT = 30;
	public String url = "http://localhost:8080/";

	// All the WebElements.
	@FindBy(className = "img-responsive")
	private WebElement homePageImage;
	@FindBy(xpath = "//a[@title='veterinarians']")
	private WebElement veterinariansTab;
	@FindBy(xpath = "//table[@id='vets']//td[1]")
	private List<WebElement> allVeterinariansNames;
	@FindBy(xpath = "//a[@title='find owners']")
	private WebElement findOwnersTab;
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement submitBtn;
	@FindBy(xpath = "//table[@id='vets']//a[1]")
	private List<WebElement> allOwnerNames;
	@FindBy(xpath = "//a[@href='/owners/new']")
	private WebElement addOwnerBtn;
	@FindBy(id = "firstName")
	private WebElement addOwnerFormFirstName;
	@FindBy(id = "lastName")
	private WebElement addOwnerFormLastName;
	@FindBy(id = "address")
	private WebElement addOwnerFormAddress;
	@FindBy(id = "city")
	private WebElement addOwnerFormCity;
	@FindBy(id = "telephone")
	private WebElement addOwnerFormTelephone;
	@FindBy(xpath = "//a[contains(@href,'/pets/new')]")
	private WebElement addPetBtn;
	@FindBy(id = "name")
	private WebElement addPetFormName;
	@FindBy(id = "birthDate")
	private WebElement addPetFormBirthDate;
	@FindBy(id = "type")
	private WebElement addPetFormType;
	@FindBy(xpath = "//select[@id='type']/option")
	private List<WebElement> addPetSelectType;
	@FindBy(xpath = "//td")
	private List<WebElement> ownerInfoPage;
	@FindBy(xpath = "//dd")
	private List<WebElement> petInfoPage;

	/*
	 * The constructor initializes WebDriver, sets implicit timeout,
	 * maximizes the browser window, opens given URL and initializes
	 * WebElements on application.
	 */
	public VeterinariansApp(){
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(MAXTIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);
		PageFactory.initElements(driver, this);
	}

	/*
	 * Convenience method to quit browser window. 
	 */
	public void tearDown() {
		driver.quit();
	}

	/*
	 * Convenience method to get attributes of the image on application home page. 
	 * @return Hashtable<String,String> This returns all attributes relevant to image.
	 */
	public Hashtable<String,String> getHomePageImageAttributes(){
		Hashtable<String,String> imgAttr = new Hashtable<String, String>();
		imgAttr.put("src", homePageImage.getAttribute("src").toString());
		imgAttr.put("border-image-outset", homePageImage.getCssValue("border-image-outset").toString());
		imgAttr.put("border-image-repeat", homePageImage.getCssValue("border-image-repeat").toString());
		imgAttr.put("border-image-slice", homePageImage.getCssValue("border-image-slice").toString());
		return imgAttr;
	}

	/*
	 * Convenience method to open veterinarians tab.
	 * @return String This returns name of the tab opened.
	 */
	public String openVeterinariansTab() {
		veterinariansTab.click();
		return veterinariansTab.getText();
	}

	/*
	 * Convenience method to get names of all veterinarians.
	 * @return List<String> This returns names of all veterinarians.
	 */
	public List<String> getAllVeterinariansNames(){
		List<String> allVeterinariansNameText = new ArrayList<String>();
		for (WebElement veterinarianName : allVeterinariansNames) {
			allVeterinariansNameText.add(veterinarianName.getText());
		}
		return allVeterinariansNameText;
	}

	/*
	 * Convenience method to open owners tab.
	 * @return String This returns name of the tab opened.
	 */
	public String openOwnersTab() {
		findOwnersTab.click();
		return findOwnersTab.getText();
	}

	/*
	 * Convenience method to get names of all owners.
	 * @return List<String> This returns names of all owners.
	 */
	public List<String> getAllOwnersNames(){
		List<String> allOwnerNameText = new ArrayList<String>();
		submitBtn.click();
		for (WebElement ownerName : allOwnerNames) {
			allOwnerNameText.add(ownerName.getText());
		}
		return allOwnerNameText;
	}

	/*
	 * Internal method to clear field and type given data.
	 * @param element This is the WebElement in which data is to be entered.
	 * @param data This is the data which is to be entered in WebElement.
	 */
	private void fillField(WebElement element, String data) {
		element.clear();
		element.click();
		element.sendKeys(data);
	}

	/*
	 * Convenience method to enter details of an owner after
	 * clicking add owner button on owner tab. 
	 * @param Owner This is the data which is to be entered for creating owner.
	 */
	public void addOwner(Hashtable<String,String> Owner) {
		openOwnersTab();
		addOwnerBtn.click();
		fillField(addOwnerFormFirstName, Owner.get("firstName"));
		fillField(addOwnerFormLastName, Owner.get("lastName"));
		fillField(addOwnerFormAddress, Owner.get("address"));
		fillField(addOwnerFormCity, Owner.get("city"));
		fillField(addOwnerFormTelephone, Owner.get("telephone"));
		submitBtn.click();
	}

	/*
	 * Convenience method to enter details of a pet for a newly created owner.
	 * @param Owner This is the data which is to be entered for creating owner.
	 */
	public void addPet(Hashtable<String,String> Pet) {
		addPetBtn.click();
		fillField(addPetFormName, Pet.get("name"));
		fillField(addPetFormBirthDate, Pet.get("birthdate"));
		addPetFormType.click();
		for (WebElement selectPetType: addPetSelectType) {
			if (selectPetType.getText().equals(Pet.get("type"))) {
				selectPetType.click();
				break;
			}
		}
		submitBtn.click();
	}

	/*
	 * Convenience method to get the most recently created owner
	 * from owners tab.
	 * @return List<String> This returns details of owner and its pet.
	 */
	public List<String> getRecentlyAddedOwnerDetails() {
		List<String> ownerInformation = new ArrayList<String>();
		openOwnersTab();
		submitBtn.click();
		int lengthOfAllOwners = allOwnerNames.size();
		allOwnerNames.get(lengthOfAllOwners-1).click();
		for(int i=0; i<4; i++) {
			ownerInformation.add(ownerInfoPage.get(i).getText().toString());
		}
		for(int i=0; i<3; i++) {
			ownerInformation.add(petInfoPage.get(i).getText().toString());
		}
		return ownerInformation;
	}
}
