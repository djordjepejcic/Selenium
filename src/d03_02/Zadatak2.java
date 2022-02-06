package d03_02;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak2 {

	public static void main(String[] args) throws InterruptedException {

//		2. Zadatak
//		Napisati program koji:
//		Ucitava stanicu https://www.wikipedia.org/
//		Sa stranice sakuplja sve linkove (jezike) i svaki link otvara u novom prozoru pretrazivaca
//		Svaki link potrebno je otvoriti u novom tabu.
//		Skripta: window.open(arguments[0]);

		System.setProperty("webdriver.chrome.driver", "driver-lib/chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		JavascriptExecutor js = (JavascriptExecutor) driver;

		driver.navigate().to("https://www.wikipedia.org/");
		driver.manage().window().maximize();
		
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		List<WebElement> languageLinks = driver.findElements(By.xpath("//div[(@class = 'central-featured')]/div/a"));
	
		for (int i = 0; i < languageLinks.size(); i++) {
			js.executeScript("window.open(arguments[0])", languageLinks.get(i));
		}
		
		Thread.sleep(10000);
		driver.quit();
		
	}

}
