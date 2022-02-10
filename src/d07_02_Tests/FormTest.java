package d07_02_Tests;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import d07_02_Pages.FormPage;

public class FormTest {
	
//	1. Zadatak
//	Skinite form.html stranicu (iz ovog foldera za domaci) na svom racunaru i otvorite je u pretrazivacu, iskopirajte url stranice
//	Od klasa je potrebno:
//	FormPage koja ima:
//	getere za sve inpute
//	geter za radio - //*[@name='gender'][@value='"+ radioValue +"'] - kao parametar prima value (male/female/middle) tog radio dugmeta
//	geter za checkbox inpute - //*[@type='checkbox'][@value='"+ checkboxValue +"']  - kao parametar prima value (read_books, online_courses, opensource, tech_cons, tech_blogs, via_delivery - jednu od ovde navedenih) tog checkbox inputa
//	getter koji hvata dugme treba da ceka da element postane klikabilan pre nego sto vrati element. Koristite waiter za ovaj slucaj.
//	metodu koja vraca da li su podaci uspesno sacuvani, tako sto proverava da li element koji nosi poruku za atribut style ima vrednost "visibility: visible"
//
//	FormTest
//	Ucitava stranicu
//	Popunite formu proizvoljnim podacima
//	Submitujte formu 
//	Proverite da li su podaci uspesno sacuvani. (imate metodu iz page-a koja vam vraca da li je uspesno sacuvano, iskorisite je)
//	U AfterClass metodi zatvaramo stranicu

	private WebDriver driver;
	private FormPage fp;

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

		fp = new FormPage(driver);
		File file_1 = new File("files/form.html");
		driver.manage().window().maximize();
		driver.navigate().to(file_1.getAbsolutePath());
	}

	@Test
	public void checkForm() {
		fp.getFullNameInput().sendKeys("Djordje Pejcic");
		fp.getGender("male").click();
		fp.getDateOfBirth().sendKeys("05.10.1990.");
		fp.getEmail().sendKeys("djodjo1234@gmail.com");
		fp.getRole().selectByValue("QA");
		fp.getWaysOfDevelopment("read_books").click();
		fp.getWaysOfDevelopment("online_courses").click();
		fp.getComment().sendKeys("Heloo!!!");
		fp.getSubmitButton().click();
		Assert.assertTrue(fp.isTheFormSavedSuccesfully(),
				"Sorry for inconvenience. Form is not saved succesfully. :-(");
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}
