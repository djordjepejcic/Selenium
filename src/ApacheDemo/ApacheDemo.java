package ApacheDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ApacheDemo {

	private WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {
//		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
//		driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
//
//		fp = new FormPage(driver);
//		File file_1 = new File("files/form.html");
//		driver.manage().window().maximize();
//		driver.navigate().to(file_1.getAbsolutePath());
	}

	@Test
	public void ApacheDemoTest() throws IOException {
		File file_1 = new File("data/Podaci.xlsx");
		FileInputStream fis = new FileInputStream(file_1);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet_1 = wb.getSheet("Korisnici");
		
		for (int i = 0; i < 4; i++) {
			String ime = sheet_1.getRow(i).getCell(0).getStringCellValue();
			String prezime = sheet_1.getRow(i).getCell(1).getStringCellValue();
			String email = sheet_1.getRow(i).getCell(2).getStringCellValue();

			System.out.println(ime + ", " + prezime + ", " + email);
			
		}
	}

//	@AfterMethod
//	public void afterMethod() {
//		driver.quit();
//	}
}
