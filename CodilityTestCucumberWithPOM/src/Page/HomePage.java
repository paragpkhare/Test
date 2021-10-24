package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	
    WebDriver driver;
    
    WebDriverWait wait;
    
    By shopTab = By.xpath("//*[@id=\"menu-item-310\"]/a[text()=\"Shop\"]");
    
    String itemXPath = "//*[@id=\"site-content\"]/div/div/article/ul/li[INDEX]/div/div[2]/div/div/a/i";
    
    By wishListButton = By.cssSelector("div.header-wishlist");
    
    By viewCartButton = By.cssSelector("div.header-cart");
    
    public HomePage(WebDriver driver){

        this.driver = driver;
        wait = new WebDriverWait(driver,30);

    }
    
    public void clickShopTab(){
    	//driver.findElement(shopTab).click();
        wait.until(ExpectedConditions.elementToBeClickable(shopTab)).click();
    }
    
    public void clickItemByIndex(String index){
    	
        //driver.findElement(By.xpath(itemXPath.replaceAll("INDEX", index))).click();
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(itemXPath.replaceAll("INDEX", index)))).click();
    }
    
    public void clickWishListButton(){
    	wait.until(ExpectedConditions.elementToBeClickable(wishListButton)).click();
    	System.out.println("Clicked on Wish List button..");
    }
    
    public void clickViewCartButton(){
    	wait.until(ExpectedConditions.elementToBeClickable(viewCartButton)).click();
    	System.out.println("Clicked on View Cart button..");
    }

}
