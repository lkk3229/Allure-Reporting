package allureReport;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

public class Tests {

	WebDriver driver;
	
	@BeforeClass
	public void setup() {
	WebDriverManager.chromedriver().setup();
	driver= new ChromeDriver();
	driver.get("https://demo.nopcommerce.com/");
	driver.manage().window().maximize();
	}
	
	@Test(priority=1, description="Verify Logo presence on Home Page")
	@Description("Verify Logo presence on Home Page")
	@Epic("EP001")
	@Feature("Feature1: Logo")
	@Story("Story: Logo Presence")
	@Step("Verify logo Presence")
	@Severity(SeverityLevel.MINOR)
	public void logoPresence() {
	boolean disstatus = driver.findElement(By.xpath("//div[@class='header-logo']//a//img")).isDisplayed();	
	Assert.assertEquals(disstatus, true);
	}
	
	@Test(priority=2)
	@Description("Verify Login on Home Page")
	@Epic("EP001")
	@Feature("Feature1: Login")
	@Story("Story: Login")
	@Step("Verify login")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() {
		driver.findElement(By.linkText("Log in")).click();
		driver.findElement(By.id("Email")).sendKeys("xyz@gmail.com");
		driver.findElement(By.id("Password")).sendKeys("Test@123");
		driver.findElement(By.xpath("//input[@class='button-1 login-button']")).click();
		
		Assert.assertEquals(driver.getTitle(), "nopCommerce demo store");
	}
	
	@Test(priority=3)
	@Description("Verify User Registration..........")
	@Epic("EP001")
	@Feature("Feature1: Registration")
	@Story("Story: Registration ")
	@Step("Verify Registration")
	@Severity(SeverityLevel.NORMAL)
	public void registrationTest() {
		throw new SkipException("Skipping this test");
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
