package d28_01;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak1 {

//	1. Zadatak 
//	Maksimizirati prozor
//	Ucitati stranicu https://s.bootsnipp.com/iframe/WaXlr
//	Od korisnika ucitati broj na koju zvezdicu je potrebno kliknuti (u rasponu od 1 do 5)
//	I izvrsite akciju klik na odgovarajuci element
//	Na kraju programa ugasite pretrazivac.

	public static void main(String[] args) throws InterruptedException {

		Scanner s = new Scanner(System.in);

		System.setProperty("webdriver.chrome.driver", "driver-lib/chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.navigate().to("https://s.bootsnipp.com/iframe/WaXlr");

		System.out.println("Unesite redni broj zvezdice za klik: ");
		int x = s.nextInt();

		if (x == 1) {
			driver.findElement(By.xpath("//button[(@id = 'rating-star-1')]")).click();
		} else if (x == 2) {
			driver.findElement(By.xpath("//button[(@id = 'rating-star-2')]")).click();
		} else if (x == 3) {
			driver.findElement(By.xpath("//button[(@id = 'rating-star-3')]")).click();
		} else if (x == 4) {
			driver.findElement(By.xpath("//button[(@id = 'rating-star-4')]")).click();
		} else {
			driver.findElement(By.xpath("//button[(@id = 'rating-star-5')]")).click();
		}

		Thread.sleep(1500);

		driver.close();
	}

}
