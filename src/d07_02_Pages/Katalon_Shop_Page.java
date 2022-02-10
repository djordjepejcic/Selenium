package d07_02_Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Katalon_Shop_Page {

	private WebDriver driver;

	public Katalon_Shop_Page(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getFirstProduct() {
		return driver.findElement(
				By.xpath("//li[contains(@class,'post-54')]/div/a[contains(@class,'woocommerce-loop-product__link')]"));
	}

	public WebElement getProduct(int redniBrojProizvoda) {
		List <WebElement> products = driver.findElements(By.xpath("//*[contains(@class,'products columns-3')]/li/a"));
		for (int i = 1; i <= products.size(); i++) {
			if (i == redniBrojProizvoda) {
				products.get(i);
			}
		}
		return products.get(redniBrojProizvoda);
	}
}
