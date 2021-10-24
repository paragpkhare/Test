package StepDefinition;		

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Page.HomePage;
import Page.ViewCartPage;
import Page.WishListPage;
import cucumber.api.java.en.Given;		
import cucumber.api.java.en.Then;		
import cucumber.api.java.en.When;		

public class Steps {				

    WebDriver driver;
    List<WebElement> wishListItemRows;
    int minPriceRowNum;
    Float minPriceItem;
    
    HomePage objHomePage;
    WishListPage objWishListPage;
    ViewCartPage objViewCartPage;
    		
    @Given("^I add four different products to my wish list$")					
    public void open_chrome_and_launch_website() throws Throwable							
    {		
       System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");					
       driver= new ChromeDriver();					
       driver.manage().window().maximize();			
       driver.get("https://testscriptdemo.com/");
       
       objHomePage = new HomePage(driver);
       objHomePage.clickShopTab();
       objHomePage.clickItemByIndex("1");
       //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       Thread.sleep(1000);
       objHomePage.clickItemByIndex("3");
       //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       Thread.sleep(1000);
       objHomePage.clickItemByIndex("4");
       //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       Thread.sleep(1000);
       objHomePage.clickItemByIndex("6");
       //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       Thread.sleep(1000);
    }		

    @When("^I view my wishlist table$")				
    public void view_wish_list() throws Throwable 							
    {		
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("window.scrollBy(0,-350)", "");
    	objHomePage.clickWishListButton();
    	Thread.sleep(1000);
    }		


    @Then("^I find total of four selected items in my Wishlist$")					
    public void check_items_in_wishlist() throws Throwable 							
    {		
    	objWishListPage = new WishListPage(driver);
    	wishListItemRows= objWishListPage.getWishListTableRows();
        assertEquals(4,wishListItemRows.size());
    }
    

	@When("^I search for lowest price product$")
	public void search_for_lowest_price_product() throws Throwable {
		
		ArrayList<Float> prices = new ArrayList<>();
		
		String price = "";  
    	for (int i = 0;i<wishListItemRows.size();i++) {
    		
    		WebElement row = wishListItemRows.get(i);
    		boolean rowexists = row.findElements(By.xpath("td[4]/span/bdi")).size() != 0;
    		boolean delrowexists = row.findElements( By.xpath("td[4]/ins/span/bdi")).size() != 0;
    		
    		boolean instockElementExists = row.findElements( By.xpath("td[5]/span")).size() != 0;
    		boolean inStock=false;
    		if(instockElementExists) {
    			String getInStockElementText = row.findElement(By.xpath("td[5]/span")).getText();
    			if(! getInStockElementText.contains("In Stock")) {
    				wishListItemRows.remove(i);
    				continue;
    			}
    		}
    		if(rowexists) {
    			price = row.findElement(By.xpath("td[4]/span/bdi")).getText();
	    		prices.add(Float.parseFloat(price.replaceAll("£", "")));
    		}
    		if(delrowexists) {
	    		price = row.findElement(By.xpath("td[4]/ins/span/bdi")).getText();
	    		prices.add(Float.parseFloat(price.replaceAll("£", "")));
    		}
    	}
    	if(prices.size() > 0) {
    		minPriceRowNum=prices.indexOf(Collections.min(prices));
    		minPriceItem = Collections.min(prices);
    		System.out.println("Minimum priced item is : £" + Collections.min(prices));
    	}
    	else { 
    		minPriceRowNum = -1;
    		System.out.print("None of the items are in stock");
    	}
	}
	
	@When("^I am able to add the lowest price item to my cart$")
	public void add_the_lowest_price_item_to_my_cart() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		if(minPriceRowNum>0) {
			objWishListPage.clickOnAddtoCartButtonForTableRow(wishListItemRows.get(minPriceRowNum));
			Thread.sleep(1000);
		}else System.out.print("None of the items are in stock");
	    
	}
	
	@Then("^I am able to verify the item in my cart$")
	public void verify_the_item_in_my_cart() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		objViewCartPage = new ViewCartPage(driver);
		if(minPriceRowNum>0) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
	    	js.executeScript("window.scrollBy(0,-350)", "");
	    	objHomePage.clickViewCartButton();
	    
	    	if(objViewCartPage.checkCartPageTableExists()) {
	    		Float cartItemPriceF = Float.parseFloat(objViewCartPage.getCartItemPrice().replaceAll("£","")) ;
	    		System.out.println("Minimum value item from Wishlist : " + minPriceItem );
	    		System.out.println("Cart Item Price : " + cartItemPriceF);
	    		assertEquals(minPriceItem, cartItemPriceF);
	    	}
		}else System.out.print("None of the items are in stock");
	}
    
}		
