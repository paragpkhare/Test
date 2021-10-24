package Page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WishListPage {
	
    WebDriver driver;
    
    WebDriverWait wait;
    
    By wishListPageTableRows = By.xpath("//*[@id=\"yith-wcwl-form\"]/table/tbody/tr");
    By addtoCart = By.xpath("td[6]/a");
    
    String addtoCartXPath = "//*[@id=\"site-content\"]/div/div/article/ul/li[INDEX]/div/div[2]/div/div/a/i";
    
    public WishListPage(WebDriver driver){

        this.driver = driver;
        wait = new WebDriverWait(driver,30);
    }
    
    public List<WebElement> getWishListTableRows(){
    	System.out.println("Getting Wish list Page Rows..");
    	return driver.findElements((wishListPageTableRows));
    }
    
    public void clickOnAddtoCartButtonForTableRow(WebElement row){
    	
    	wait.until(ExpectedConditions.elementToBeClickable(row.findElement(addtoCart))).click();
    	System.out.println("Clicked on add to cart button for the specified row..");
        //driver.findElement(By.xpath(itemXPath.replaceAll("INDEX", index))).click();
    	//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(itemXPath.replaceAll("INDEX", index)))).click();
    }

}
