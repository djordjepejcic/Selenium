package d04_02;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Zadatak1 {

	WebDriver driver;
	Actions actions;

	@BeforeMethod
	public void before() {
		System.setProperty("webdriver.chrome.driver", "driver-lib/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://www.kupujemprodajem.com/");
		actions = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	}

	@Test
	public void test() {
		driver.findElement(By.className("kpBoxCloseButton")).click();
		driver.findElement(By.name("data[keywords]")).sendKeys("iphone");
		driver.findElement(By.name("data[keywords]")).sendKeys(Keys.ENTER);
		driver.findElement(By.id("priceSecondSelection")).click();
		driver.findElement(By.xpath("//input[(@value = 'eur')]")).click();
		driver.findElement(By.xpath("//div[(@id = 'priceSelection')]//input[(@type = 'button')][1]")).click();
		driver.findElement(By.xpath("//*[(@id = 'orderSecondSelection')]//div[(@class = 'choiceLabel')]")).click();
		driver.findElement(By.xpath(
				"/html/body/div[2]/div/div[1]/div[1]/div[1]/form[3]/div[3]/div/div/div[4]/div/div/div/div[10]/div/div[2]/div/div[2]/div/div[1]"))
				.click();
		driver.findElement(By.className("secondarySearchButton")).click();

		List<WebElement> listOfElements = driver.findElements(By.xpath("//span[@class='adPrice ']"));

		String[] listOfStrings = new String[30];
		for (int i = 0; i < listOfElements.size(); i++) {
			listOfStrings[i] = listOfElements.get(i).getText();
			listOfStrings[i] = listOfStrings[i].replace(",", ".");
		}

		double[] prices = new double[listOfStrings.length];
		String[] currency = new String[listOfStrings.length];

		for (int i = 0; i < listOfStrings.length; i++) {
			String string = listOfStrings[i];
			String[] parts = string.split(" ");
			prices[i] = Double.parseDouble(parts[0]);
			currency[i] = parts[1];
		}

		for (int i = 0; i < listOfStrings.length; i++) {
			if (!currency[i].equals("din")) {
				prices[i] = prices[i] * 120;
			}
		}

		boolean isItSorted = false;
		for (int i = 0; i < prices.length - 1; i++) {
			if (prices[i] <= prices[i + 1]) {
				isItSorted = true;
			} else {
				isItSorted = false;
			}
			Assert.assertEquals(isItSorted, true, "Nije sortirao,");
		}
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}
