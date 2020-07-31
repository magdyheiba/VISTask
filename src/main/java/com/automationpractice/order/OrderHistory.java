package com.automationpractice.order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.shaft.gui.element.ElementActions;
import com.shaft.tools.io.ReportManager;
import com.shaft.validation.Verifications;

public class OrderHistory {
	private WebDriver driver;
	private By orderDetails_button = By.xpath("//span[contains(.,'Details')]");
	private By orderName_text = By.xpath("//div[@id='order-detail-content']/table//td[@class='bold']");
	private By lastProductAtSummary = By.xpath("//div[@class='cart_last_product_content']//p[@class='product-name']");
	private By orderPaymentStatus_text = By.xpath("//table[@id='order-list']//tr/td[@class='history_method']");
	private By orderDate_text = By.xpath("//table[@id='order-list']//tr/td[@class='history_date bold']");

	String deliveryAddressTable = "//ul[contains(@class,' alternate_item')]";
	private By deliveryAddressTable_firstName_text = By
			.xpath(deliveryAddressTable + "//span[@class='address_firstname']");
	private By deliveryAddressTable_lastName_text = By
			.xpath(deliveryAddressTable + "//span[@class='address_lastname']");
	private By deliveryAddressTable_company_text = By.xpath(deliveryAddressTable + "//li[@class='address_company']");
	private By deliveryAddressTable_address1_text = By
			.xpath(deliveryAddressTable + "//span[@class='address_address1']");
	private By deliveryAddressTable_address2_text = By
			.xpath(deliveryAddressTable + "//span[@class='address_address2']");
	private By deliveryAddressTable_city_text = By.xpath(deliveryAddressTable + "//span[@class='address_city']");
	private By deliveryAddressTable_state_text = By.xpath(deliveryAddressTable + "//span[@class='address_State:name']");
	private By deliveryAddressTable_zipCode_text = By.xpath(deliveryAddressTable + "//span[@class='address_postcode']");
	private By deliveryAddressTable_country_text = By
			.xpath(deliveryAddressTable + "//span[@class='address_Country:name']");
	private By deliveryAddressTable_phone_text = By.xpath(deliveryAddressTable + "//span[@class='address_phone']");
	private By deliveryAddressTable_mobilePhone_text = By
			.xpath(deliveryAddressTable + "//li[@class='address_phone_mobile']");

	// Last Product Ordered method
	public String LastProductOrdered() {
		String LastProductOrdered = ElementActions.getText(driver, lastProductAtSummary);
		return LastProductOrdered;
	}

	// verify order details method
	public OrderHistory clickOnOrderDetails() {
		ElementActions.click(driver, orderDetails_button);
		return this;
	}

	// verify order name method
	public String getOrderName() {
		String OrderName = ElementActions.getText(driver, orderName_text);
		return OrderName;
	}

	// Actions
	// constructor
	public OrderHistory(ThreadLocal<WebDriver> driver) {
		this.driver = driver.get();
	}

	public OrderHistory(WebDriver driver) {
		this.driver = driver;
	}

	// Order Payment Status method
	public String getOrderPaymentStatus() {
		String OrderPaymentStatus = ElementActions.getText(driver, orderPaymentStatus_text);
		return OrderPaymentStatus;
	}

	// get current Day of date
	public String getCurrentDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDateTime now = LocalDateTime.now();
		String CurrentDay = dtf.format(now);
		return CurrentDay;
	}

	// get Actual date method
	public String getActualDate() {
		String ActualDate = ElementActions.getText(driver, orderDate_text);
		return ActualDate;
	}

	// verify DELIVERY ADDRESS method
	public OrderHistory verifyDeliveryAddress(String[] deliveryAddress) {
		ReportManager.logDiscrete("Verifying delivery address data : " + deliveryAddress);
		Verifications.verifyElementAttribute(driver, deliveryAddressTable_firstName_text, "text", deliveryAddress[0]);
		Verifications.verifyElementAttribute(driver, deliveryAddressTable_lastName_text, "text", deliveryAddress[1]);
		Verifications.verifyElementAttribute(driver, deliveryAddressTable_company_text, "text", deliveryAddress[2]);
		Verifications.verifyElementAttribute(driver, deliveryAddressTable_address1_text, "text", deliveryAddress[3]);
		Verifications.verifyElementAttribute(driver, deliveryAddressTable_address2_text, "text", deliveryAddress[4]);
		Verifications.verifyElementAttribute(driver, deliveryAddressTable_city_text, "text", deliveryAddress[5]+",");
		Verifications.verifyElementAttribute(driver, deliveryAddressTable_state_text, "text", deliveryAddress[6]);
		Verifications.verifyElementAttribute(driver, deliveryAddressTable_zipCode_text, "text", deliveryAddress[7]);
		Verifications.verifyElementAttribute(driver, deliveryAddressTable_country_text, "text", deliveryAddress[8]);
		Verifications.verifyElementAttribute(driver, deliveryAddressTable_phone_text, "text", deliveryAddress[9]);
		Verifications.verifyElementAttribute(driver, deliveryAddressTable_mobilePhone_text, "text",
				deliveryAddress[10]);
		return this;
	}

}
