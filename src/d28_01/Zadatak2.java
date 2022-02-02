package d28_01;

import java.time.Duration;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Zadatak2 {

	public static void main(String[] args) throws InterruptedException {

//	2. Zadatak
//	Napisati program koji vrsi dodavanje 5 reda u tabelu. 
//	Maksimizirati prozor
//	Ucitati stranicu https://www.tutorialrepublic.com/snippets/bootstrap/table-with-add-and-delete-row-feature.php
//	Dodati red podataka - jedan red u jednoj iteraciji 
//	Kliknite na dugme Add New
//	Unesite name,departmant i phone (mogu da budu uvek iste vrednost)
//	Kliknite na zeleno Add dugme
//	Na kraju programa ugasite pretrazivac.

		System.setProperty("webdriver.chrome.driver", "driver-lib/chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.navigate()
				.to("https://www.tutorialrepublic.com/snippets/bootstrap/table-with-add-and-delete-row-feature.php");

		for (int i = 0; i < 5; i++) {
			driver.findElement(By.xpath("//div[(@class = 'col-sm-4')]/button")).click();
			driver.findElement(By.xpath("//input[last()][(@id = 'name')]")).sendKeys("Djordje Pejcic");
			driver.findElement(By.xpath("//input[last()][(@id = 'department')]")).sendKeys("Administration");
			driver.findElement(By.xpath("//input[last()][(@id = 'phone')]")).sendKeys("0187894562");
			driver.findElement(By.xpath("//tr[last()]//a[@class = 'add']")).click();
		}

		driver.close();

	}

}
