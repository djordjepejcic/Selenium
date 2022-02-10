package d07_02_Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Katalon_Wanted_Product_Page {

	private WebDriver driver;

	public Katalon_Wanted_Product_Page(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getInputQuantity() {
		return driver.findElement(By.xpath("//div[@class='quantity']/input"));
	}

	public void clearInsertInputQuantity(String number) {
		getInputQuantity().click();
		getInputQuantity().clear();
		getInputQuantity().sendKeys(number);
	}

	public WebElement getAddToCart() {
		return driver.findElement(By.xpath("//*[@class='single_add_to_cart_button button alt']"));
	}

	public String getMessage() {
		return driver.findElement(By.xpath("//*[@role='alert']")).getText();
	}

	public WebElement getViewCartButton() {
		return driver.findElement(By.xpath("//*[@class='button wc-forward']"));
	}
}
