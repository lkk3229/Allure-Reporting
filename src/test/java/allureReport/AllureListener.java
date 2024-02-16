package allureReport;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Attachment;

public class AllureListener implements ITestListener {

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}
	
	@Attachment
	public byte[] saveFailureScreenShot(WebDriver driver) {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	}
	
	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}
	
	@Override
	public void onStart(ITestContext iTextContext) {
		System.out.println("I am in onStart method "+ iTextContext.getName());
		iTextContext.setattribute("WebDriver", BaseClass.getDriver());
	}
	
	@Override
	public void onFinish(ITestContext iTextContext) {
		System.out.println("I am in onFinish method "+ iTextContext.getName());
	}
	
	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println("I am in onTestSuccess method "+ getTestMethodName(iTestResult) + " start");
	}
	
	@Override
	public void onTestFailure(ITestResult iTestResult) {
		System.out.println("I am in onTestFailure method "+ getTestMethodName(iTestResult) + " start");
		Object testClass = iTestResult.getInstance();
		WebDriver driver = BaseClass.getDriver();
		//Allure Screenshot and SaveTestLog
		if(driver instanceof WebDriver) {
			System.out.println("Screenshot captured for test cases: " + getTestMethodName(iTestResult));
			saveFailureScreenShot(driver);
		}
		saveTextLog(getTestMethodName(iTestResult) + "failed and screenshot taken!");
	}
		
	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println("I am in onTestSkipped method "+ getTestMethodName(iTestResult) + " start");
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		System.out.println("Test failed but it is in defined success ratio "+ getTestMethodName(iTestResult) + " start");
	}
}
