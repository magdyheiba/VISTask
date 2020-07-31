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
import com.automationpractice.order.OrderHistory;
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
	String[] login;
	String[] deliveryAddress;

	// Test Cases [if the email was existed then we have to change it from excel sheet]
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
	@Description("When user inputs credentials of latest registerd account, then user should be able to login successfully and see 'My Account'")
	public void loginWithCreatedUser() {
		new Home(driver).navigate().navigateToAuthentication().login(login);
		String AccountStatus = new Register(driver).checkIfUserRegisterdSuccessfully();
		Assertions.assertEquals("MY ACCOUNT", AccountStatus, AssertionComparisonType.CONTAINS, AssertionType.POSITIVE);
	}

	@Test(description = "Register - Verify", dependsOnMethods = { "loginWithCreatedUser" }, groups = { "gui" })
	@Description("When user inputs credentials of latest registerd account, and navigates to my personal info then user should be able to see the date he entered saved successfully")
	public void verifyTheDateOfCreatedUser() throws ParseException {
		new Home(driver).navigate().navigateToAuthentication().login(login).navigateToPersonalInfo()
				.verifyTheDataRegisterNewAccount(verifyRegisteration);
	}

	@Test(description = "Add Product - Insert", dependsOnMethods = { "verifyTheDateOfCreatedUser" }, groups = { "gui" })
	@Description("When user inputs credentials of latest registerd account, then user should be able to add product to cart and checkout")
	public void addProductToCartAndCheckout() {
		new Home(driver).navigate().navigateToAuthentication().login(login);
		new Home(driver).navigateToBlouseTap().addProductToCart();
		String OrderStatus = new Product(driver).orderStatus();
		Assertions.assertEquals("complete", OrderStatus, AssertionComparisonType.CONTAINS, AssertionType.POSITIVE);
	}

	@Test(description = "Order History - Verify", dependsOnMethods = { "addProductToCartAndCheckout" }, groups = {
			"gui" })
	@Description("When user inputs credentials of latest registerd account,and navigates to order history screen then user should be able to see last order he added the cart and verify its data")
	public void verifyOrderDetails() {
		new Home(driver).navigate().navigateToAuthentication().login(login);
		new Register(driver).navigateToOrderHistory().clickOnOrderDetails().verifyDeliveryAddress(deliveryAddress);
		String ActualDate = new OrderHistory(driver).getActualDate();
		String ExpectedDate = new OrderHistory(driver).getCurrentDate();
		String PaymentMethod = new OrderHistory(driver).getOrderPaymentStatus();
		String ProductName = new OrderHistory(driver).getOrderName();
		Assertions.assertEquals(ExpectedDate, ActualDate, AssertionComparisonType.EQUALS, AssertionType.POSITIVE);
		Assertions.assertEquals("Bank wire", PaymentMethod, AssertionComparisonType.CONTAINS, AssertionType.POSITIVE);
		Assertions.assertEquals("Blouse", ProductName, AssertionComparisonType.CONTAINS, AssertionType.POSITIVE);
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

	private String[] readLoginTestData() {
		ArrayList<String> login = new ArrayList<String>();
		String colName = "Data1";
		String sheetName = "Register";
		login.add(automationPractice.get().getCellData(sheetName, "Email", colName));
		login.add(automationPractice.get().getCellData(sheetName, "Password", colName));
		return login.toArray(new String[0]);
	}

	private String[] readDeliveryAddressTestData() {
		ArrayList<String> deliveryAddress = new ArrayList<String>();
		String colName = "Data1";
		String sheetName = "Register";
		deliveryAddress.add(automationPractice.get().getCellData(sheetName, "Address first name", colName));
		deliveryAddress.add(automationPractice.get().getCellData(sheetName, "Address last name", colName));
		deliveryAddress.add(automationPractice.get().getCellData(sheetName, "Company", colName));
		deliveryAddress.add(automationPractice.get().getCellData(sheetName, "Address", colName));
		deliveryAddress.add(automationPractice.get().getCellData(sheetName, "Address (Line 2)", colName));
		deliveryAddress.add(automationPractice.get().getCellData(sheetName, "City", colName));
		deliveryAddress.add(automationPractice.get().getCellData(sheetName, "State", colName));
		deliveryAddress.add(automationPractice.get().getCellData(sheetName, "Zip/Postal Code", colName));
		deliveryAddress.add(automationPractice.get().getCellData(sheetName, "Country", colName));
		deliveryAddress.add(automationPractice.get().getCellData(sheetName, "Home phone", colName));
		deliveryAddress.add(automationPractice.get().getCellData(sheetName, "Mobile phone", colName));
		return deliveryAddress.toArray(new String[0]);
	}

	@BeforeClass
	public void beforeClass() {
		automationPractice
				.set(new ExcelFileManager(System.getProperty("testDataFolderPath") + "AutomationPractice.xlsx"));

		// read test data
		register = readRegisterTestData();
		verifyRegisteration = verifyRegisterTestData();
		login = readLoginTestData();
		deliveryAddress = readDeliveryAddressTestData();
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
