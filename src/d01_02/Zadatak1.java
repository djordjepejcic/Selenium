package d01_02;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Zadatak1 {

	public static void main(String[] args) {

//	1.Zadatak
//	Napisati program koji :
//	Ucitava stranicu https://www.udemy.com/courses/search/?src=ukw&q=slksd
//	Klikce na dugme za jezik i proverava da li se prikazuje dijalog za promenu jezika
//	Stampa u konzoli tekst “Dijalog se prikazao”

		System.setProperty("webdriver.chrome.driver", "driver-lib/chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		JavascriptExecutor js = (JavascriptExecutor) driver;

		driver.manage().window().maximize();

		driver.navigate().to("https://www.udemy.com/courses/search/?src=ukw&q=slksd");

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		js.executeScript("window.scrollBy(0,750)");

		boolean elementPostoji = false;

		driver.findElement(By.xpath("//div[contains(@class, 'language-selector-container')]//button")).click();

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.attributeContains(By.xpath("//div[@class = 'main-content-wrapper']"),
					"aria-hidden", "true"));
			elementPostoji = true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			elementPostoji = false;
		}

		if (elementPostoji) {
			System.out.println("Element postoji.");
		} else {
			System.out.println("Element ne postoji.");
		}
	}

}
