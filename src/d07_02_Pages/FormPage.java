package d07_02_Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FormPage {

	private WebDriver driver;

	public FormPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getFullNameInput() {
		return driver.findElement(By.id("first-name"));
	}

	public WebElement getDateOfBirth() {
		return driver.findElement(By.id("dob"));
	}

	public WebElement getEmail() {
		return driver.findElement(By.id("email"));
	}

	public WebElement getGender(String radioValue) {
		return driver.findElement(By.xpath("//*[@name='gender'][@value='" + radioValue + "']"));
	}

	public Select getRole() {
		Select select = new Select(driver.findElement(By.id("role")));
		return select;
	}

	public WebElement getWaysOfDevelopment(String checkboxValue) {
		return driver.findElement(By.xpath("//*[@type='checkbox'][@value='" + checkboxValue + "']"));
	}

	public WebElement getComment() {
		return driver.findElement(By.id("comment"));
	}

	public WebElement getSubmitButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementWithText(By.id("submit"), "disabled"));

		return driver.findElement(By.id("submit"));
	}

	public boolean isTheFormSavedSuccesfully() {
		boolean saved = true;
		List<WebElement> list = driver.findElements(By.xpath("//div[@style='visibility: visible']"));
		if (list.size() == 0) {
			saved = false;
		}
		return saved;
	}

}
