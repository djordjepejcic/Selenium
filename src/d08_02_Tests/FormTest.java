package d08_02_Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import d08_02_Pages.FormPage;

public class FormTest {
	
//	1.Zadatak
//	Za potrebe zadatka kreirati FormPage koji ima sve potrebne metode.
//	Zatim kreirati FormTest koji 
//	Ucitava form.html stranicu (form.html je u folderu za domaci skinite je i otvorite u chrome i iskopirajte url)
//	I popunjava formu koristeci FormData.xlsx fajl (u folderu je za domaci)
//	Postavite odgovarajuce waitere tako da se saceka sledeci unos podataka u formu nakon submitovanja
	
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
	public void checkForm() throws IOException, InterruptedException {
		File file_2 = new File("data/FormData.xlsx");
		FileInputStream fis = new FileInputStream(file_2);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		SoftAssert sa = new SoftAssert();

		XSSFSheet sheet = wb.getSheet("form");

		for (int i = 1; i <= 6; i++) {

			String fullName = sheet.getRow(i).getCell(0).getStringCellValue();
			String gender = sheet.getRow(i).getCell(1).getStringCellValue();
			Date dateOfBirth = sheet.getRow(i).getCell(2).getDateCellValue();
			String email = sheet.getRow(i).getCell(3).getStringCellValue();
			String role = sheet.getRow(i).getCell(4).getStringCellValue();
			String waysOfDev = sheet.getRow(i).getCell(5).getStringCellValue();
			String comment = sheet.getRow(i).getCell(6).getStringCellValue();

			fp.getFullNameInput().sendKeys(fullName);
			Thread.sleep(500);
			fp.getGender(gender).click();
			Thread.sleep(500);

			DateFormat df = new SimpleDateFormat();
			String dateOfBirth1 = df.format(dateOfBirth);

			fp.getDateOfBirth().sendKeys(dateOfBirth1);
			fp.getEmail().sendKeys(email);
			Thread.sleep(500);
			fp.getRole().selectByValue(role);
			Thread.sleep(500);
			fp.getWaysOfDevelopment(waysOfDev).click();
			Thread.sleep(500);
			fp.getComment().sendKeys(comment);
			Thread.sleep(500);
			fp.getSubmitButton().click();

			sa.assertTrue(fp.isTheFormSavedSuccesfully(), "Form isn't saved properly!");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.invisibilityOfElementWithText(
					By.xpath("//*[@class='message-element poruka']"), "Uspesno ste sacuvali podatke!"));
			driver.navigate().refresh();
		}
		sa.assertAll();
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}
