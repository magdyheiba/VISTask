package tests.gui.automationpractice;

import java.text.ParseException;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automationpractice.commons.Home;
import com.automationpractice.commons.Register;
import com.automationpractice.order.Product;
import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.browser.BrowserFactory;
import com.shaft.tools.io.ExcelFileManager;
import com.shaft.validation.Assertions;
import com.shaft.validation.Assertions.AssertionComparisonType;
import com.shaft.validation.Assertions.AssertionType;

import io.qameta.allure.Description;

public class AutomationPractice_Testcases {
	private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private ThreadLocal<ExcelFileManager> automationPractice = new ThreadLocal<>();
	String[] register;
	String[] verifyRegisteration;

	// Test Cases
	@Test(description = "Register - Insert", groups = { "gui" })
	@Description("When I enter Register page, then all data should be saved successfully and a user should have a new account")
	public void createNewAccount() {
		new Home(driver).navigate().navigateToAuthentication()
				.navigateToRegisterScreen(automationPractice.get().getCellData("Register", "Email", "Data1"))
				.inputRegisterNewAccount(register).clickOnSubmitButton();
		String AccountStatus = new Register(driver).checkIfUserRegisterdSuccessfully();
		Assertions.assertEquals("MY ACCOUNT", AccountStatus, AssertionComparisonType.CONTAINS, AssertionType.POSITIVE);
	}

	@Test(description = "Login - Insert", dependsOnMethods = { "createNewAccount" }, groups = { "gui" })
	@Description("When user entered credentials of latest registerd account, then user should be able to ogin successfully and see 'My Account'")
	public void loginWithCreatedUser() {
		new Home(driver).navigate().navigateToAuthentication().login(
				automationPractice.get().getCellData("Register", "Email", "Data1"),
				automationPractice.get().getCellData("Register", "Password", "Data1"));
		String AccountStatus = new Register(driver).checkIfUserRegisterdSuccessfully();
		Assertions.assertEquals("MY ACCOUNT", AccountStatus, AssertionComparisonType.CONTAINS, AssertionType.POSITIVE);
	}

	@Test(description = "Register - Verify", dependsOnMethods = { "loginWithCreatedUser" }, groups = { "gui" })
	@Description("When user entered credentials of latest registerd account, then user should be able to ogin successfully and see 'My Account'")
	public void verifyTheDateOfCreatedUser() throws ParseException {
		new Home(driver).navigate().navigateToAuthentication()
				.login(automationPractice.get().getCellData("Register", "Email", "Data1"),
						automationPractice.get().getCellData("Register", "Password", "Data1"))
				.navigateToPersonalInfo().verifyTheDataRegisterNewAccount(verifyRegisteration);
	}

	@Test(description = "Register - Verify", dependsOnMethods = { "verifyTheDateOfCreatedUser" }, groups = { "gui" })
	@Description("When user entered credentials of latest registerd account, then user should be able to ogin successfully and see 'My Account'")
	public void addProductToCartAndCheckout() {
		new Home(driver).navigate().navigateToAuthentication().login(
				automationPractice.get().getCellData("Register", "Email", "Data1"),
				automationPractice.get().getCellData("Register", "Password", "Data1"));
		new Home(driver).navigateToBlouseTap().addProductToCart();
		String OrderStatus = new Product(driver).orderStatus();
		Assertions.assertEquals("complete", OrderStatus, AssertionComparisonType.CONTAINS, AssertionType.POSITIVE);
	}

	// Reading From excel sheet using Data Driven Technique
	private String[] readRegisterTestData() {
		ArrayList<String> register = new ArrayList<String>();
		String colName = "Data1";
		String sheetName = "Register";
		register.add(automationPractice.get().getCellData(sheetName, "mr/mrs", colName));
		register.add(automationPractice.get().getCellData(sheetName, "First name", colName));
		register.add(automationPractice.get().getCellData(sheetName, "Last name", colName));
		register.add(automationPractice.get().getCellData(sheetName, "Email", colName));
		register.add(automationPractice.get().getCellData(sheetName, "Password", colName));
		register.add(automationPractice.get().getCellData(sheetName, "Day", colName));
		register.add(automationPractice.get().getCellData(sheetName, "Month", colName));
		register.add(automationPractice.get().getCellData(sheetName, "year", colName));
		register.add(automationPractice.get().getCellData(sheetName, "newsletter checkBox", colName));
		register.add(automationPractice.get().getCellData(sheetName, "Receive special offers checkBox", colName));
		register.add(automationPractice.get().getCellData(sheetName, "Address first name", colName));
		register.add(automationPractice.get().getCellData(sheetName, "Address last name", colName));
		register.add(automationPractice.get().getCellData(sheetName, "Company", colName));
		register.add(automationPractice.get().getCellData(sheetName, "Address", colName));
		register.add(automationPractice.get().getCellData(sheetName, "Address (Line 2)", colName));
		register.add(automationPractice.get().getCellData(sheetName, "City", colName));
		register.add(automationPractice.get().getCellData(sheetName, "State", colName));
		register.add(automationPractice.get().getCellData(sheetName, "Zip/Postal Code", colName));
		register.add(automationPractice.get().getCellData(sheetName, "Country", colName));
		register.add(automationPractice.get().getCellData(sheetName, "Additional information", colName));
		register.add(automationPractice.get().getCellData(sheetName, "Home phone", colName));
		register.add(automationPractice.get().getCellData(sheetName, "Mobile phone", colName));
		register.add(automationPractice.get().getCellData(sheetName, "address alias", colName));
		return register.toArray(new String[0]);
	}

	private String[] verifyRegisterTestData() {
		ArrayList<String> register = new ArrayList<String>();
		String colName = "Data1";
		String sheetName = "Register";
		register.add(automationPractice.get().getCellData(sheetName, "mr/mrs", colName));
		register.add(automationPractice.get().getCellData(sheetName, "First name", colName));
		register.add(automationPractice.get().getCellData(sheetName, "Last name", colName));
		register.add(automationPractice.get().getCellData(sheetName, "Email", colName));
		register.add(automationPractice.get().getCellData(sheetName, "Day", colName));
		register.add(automationPractice.get().getCellData(sheetName, "Month", colName));
		register.add(automationPractice.get().getCellData(sheetName, "year", colName));
		register.add(automationPractice.get().getCellData(sheetName, "newsletter checkBox", colName));
		register.add(automationPractice.get().getCellData(sheetName, "Receive special offers checkBox", colName));
		return register.toArray(new String[0]);
	}

	@BeforeClass
	public void beforeClass() {
		automationPractice
				.set(new ExcelFileManager(System.getProperty("testDataFolderPath") + "AutomationPractice.xlsx"));

		// read test data
		register = readRegisterTestData();
		verifyRegisteration = verifyRegisterTestData();

	}

	@BeforeMethod(onlyForGroups = { "gui" })
	public void beforeMethod() {
		driver.set(BrowserFactory.getBrowser());
	}

	@AfterMethod(onlyForGroups = { "gui" })
	public void afterMethod() {
		BrowserActions.closeCurrentWindow(driver.get());
	}
}
