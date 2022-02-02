package d01_02;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Zadatak2 {

	public static void main(String[] args) throws InterruptedException {

//	2.Zadatak
//	Napisti program koji:
//	Ucitava stranicu https://videojs.com/city
//	Pusta video klikom na play dugme
//	Cekamo da se video ucita
//	Tako sto proveravamo da li vise to play dugme nije vidljivo


		System.setProperty("webdriver.chrome.driver", "driver-lib/chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.navigate().to("https://videojs.com/city");
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id='vjs_video_3']/button")).click();

	
		boolean videoUcitan = false;
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.attributeContains(By.xpath("//div[@id = 'vjs_video_3']"), "class", "vjs-has-started"));
			videoUcitan = true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			videoUcitan = false;
		}

		if (videoUcitan) {
			System.out.println("Video ucitan.");
		} else {
			System.out.println("Video nije ucitan.");
		}
	}

}
