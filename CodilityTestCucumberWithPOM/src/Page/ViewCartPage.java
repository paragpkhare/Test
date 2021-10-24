package Page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ViewCartPage {
	
    WebDriver driver;
    
    WebDriverWait wait;
    
    By cartPageTable = By.xpath("//*[@id=\"site-content\"]/div/div/article/div/div/div[1]/div/form/table/tbody");
    By cartItemPrice = By.xpath("//*[@id=\"site-content\"]/div/div/article/div/div/div[1]/div/form/table/tbody/tr[1]/td[4]/span/bdi");
    
   
    
    public ViewCartPage(WebDriver driver){

        this.driver = driver;
        wait = new WebDriverWait(driver,30);
    }
    
    public boolean checkCartPageTableExists(){
    	
    	System.out.println("Checking if Cart Page Table exists..");
    	return driver.findElements(cartPageTable).size() != 0; 	
        //driver.findElement(By.xpath(itemXPath.replaceAll("INDEX", index))).click();
    	//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(itemXPath.replaceAll("INDEX", index)))).click();
    }
    
   
    public String getCartItemPrice(){
    	System.out.println("Getting Cart Item Price..");
    	return driver.findElement(cartItemPrice).getText();
    }

}
