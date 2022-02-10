package d07_02_Tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import d07_02_Pages.Katalon_Cart_Page;
import d07_02_Pages.Katalon_Homepage;
import d07_02_Pages.Katalon_Shop_Page;
import d07_02_Pages.Katalon_Wanted_Product_Page;

public class KatalonTest {

	private WebDriver driver;
	private Katalon_Homepage homePage;
	private Katalon_Cart_Page cartPage;
	private Katalon_Shop_Page shopPage;
	private Katalon_Wanted_Product_Page productPage;

	@BeforeMethod
	public void before() {
		System.setProperty("webdriver.chrome.driver", "driver-lib/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		homePage = new Katalon_Homepage(driver);
		cartPage = new Katalon_Cart_Page(driver);
		shopPage = new Katalon_Shop_Page(driver);
		productPage = new Katalon_Wanted_Product_Page(driver);
	}

	@Test
	public void AddRemoveProductsFromTheCart() throws InterruptedException {
		driver.navigate().to("http://cms.demo.katalon.com/");
		homePage.getShopButton().click();
		shopPage.getProduct(0).click();
		productPage.clearInsertInputQuantity("2");
		productPage.getAddToCart().click();
		Assert.assertTrue(productPage.getMessage().contains("have been added to your cart"),
				"No added items!!!");

		productPage.getViewCartButton().click();

		Assert.assertEquals(cartPage.numberOfProducts().size(), 1);

		Assert.assertEquals(cartPage.getQuantityInfo(), "2", "Doesn't have proper number of items!");

		cartPage.getRemoveButton().click();

		Assert.assertEquals(cartPage.getRemoveMessage(), "Your cart is currently empty.", "Cart is not empty!");
	}

	@AfterMethod
	public void after() {
		driver.quit();
	}
}
