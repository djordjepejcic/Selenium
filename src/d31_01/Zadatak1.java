package d31_01;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak1 {

	public static void main(String[] args) throws InterruptedException {

//	1.Zadatak
//	Napisati program koji:
//	Ucitava stranicu https://s.bootsnipp.com/iframe/Dq2X
//	Klikce na svaki iks da ugasi obavestenje i proverava da li se nakon klika element obrisao sa stranice i ispisuje odgovarajuce poruke (OVO JE POTREBNO RESITI KORISCENJEM PETLJE)
//	POMOC: Brisite elemente odozdo.
//	(ZA VEZBANJE)Probajte da resite da se elemementi brisu i odozgo

		System.setProperty("webdriver.chrome.driver", "driver-lib/chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.navigate().to("https://s.bootsnipp.com/iframe/Dq2X");

		List<WebElement> listaElemenata = driver.findElements(By.className("close"));
		
		boolean elementPostoji  = false;
		
		for (int i = listaElemenata.size()-1; i >= 0; --i) {
			listaElemenata.get(i).click();
			try {
				WebElement e = driver.findElement(By.className("alert"));
				elementPostoji = true;
			} catch (Exception e2) {
				elementPostoji = false;
			}

			if (elementPostoji) {
				System.out.println("Element postoji.");
			} else {
				System.out.println("Element ne postoji.");
			}
		}
		
		driver.close();
	}

}
