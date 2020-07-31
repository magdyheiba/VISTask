package com.automationpractice.order;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.shaft.gui.element.ElementActions;
import com.shaft.tools.io.ReportManager;

public class Product {
	// variables
	private WebDriver driver;

	// locators
	private By inStock_button = By.xpath("//span[@class='available-now']");
	private By addToCart_button = By.xpath("//span[text()='Add to cart']");
	private By proceedToCheckoutAtModel_button = By.xpath("//a[@title='Proceed to checkout']");
	private By proceedToCheckoutSummary_button = By.xpath("	//span[text()='Proceed to checkout']");
	private By proceedToCheckoutShipping_button = By.xpath("//button[@name='processCarrier']");
	private By agreeTerms_checkBox = By.xpath("//input[@id='cgv']");
	private By payByBankWire_button = By.xpath("//a[@class='bankwire']");
	private By confirmOrder_button = By.xpath("//span[text()='I confirm my order']");
	private By orderStatus_text = By.xpath("//p[@class='cheque-indent']");
	private By backToOrders_button = By.xpath("//a[@title='Back to orders']");

	// Actions
	// constructor
	public Product(ThreadLocal<WebDriver> driver) {
		this.driver = driver.get();
	}

	public Product(WebDriver driver) {
		this.driver = driver;
	}

	// Adding product to cart and checking out it method
	public Product addProductToCart() {
		ReportManager.logDiscrete("Adding product to cart and checking out it ");
		ElementActions.hover(driver, inStock_button);
		ElementActions.click(driver, addToCart_button);
		ElementActions.click(driver, proceedToCheckoutAtModel_button);
		ElementActions.click(driver, proceedToCheckoutSummary_button);
		ElementActions.click(driver, proceedToCheckoutSummary_button);
		ElementActions.click(driver, agreeTerms_checkBox);
		ElementActions.click(driver, proceedToCheckoutShipping_button);
		ElementActions.click(driver, payByBankWire_button);
		ElementActions.click(driver, confirmOrder_button);
		return this;
	}

	// order status method
	public String orderStatus() {
		String OrderStatus = ElementActions.getText(driver, orderStatus_text);
		return OrderStatus;
	}

	// navigate to order history method
	public Product navigateToMyOrders() {
		ElementActions.click(driver, backToOrders_button);
		return this;
	}

}
