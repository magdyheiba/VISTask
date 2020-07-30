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
	private By proceedToCheckoutShipping_button = By
			.xpath("//button[contains (@type,'submit')and contains(@name,'submit_search')]");
	private By agreeTerms_checkBox = By.xpath("//input[@id='cgv']");
	private By payByBankWire_button = By.xpath("//a[@class='bankwire']");
	private By confirmOrder_button = By.xpath("//span[text()='I confirm my order']");
	private By orderStatus_text = By.xpath("//p[@class='cheque-indent']");
	private By backToOrders_button = By.xpath("//a[@title='Back to orders']");
	private By orderPaymentStatus_text = By.xpath("//table[@id='order-list']//tr/td[@class='history_method']");
	private By orderDetails_button = By.xpath("//span[contains(.,'Details')]");
	private By orderName_text = By.xpath("//div[@id='order-detail-content']/table//td[@class='bold']");
	private By lastProductAtSummary = By.xpath("//div[@class='cart_last_product_content']//p[@class='product-name']");

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
		ElementActions.click(driver, proceedToCheckoutShipping_button);
		ElementActions.click(driver, agreeTerms_checkBox);
		ElementActions.click(driver, payByBankWire_button);
		ElementActions.click(driver, confirmOrder_button);
		return this;
	}

	// order status method
	public String orderStatus() {
		String OrderStatus = ElementActions.getText(driver, orderStatus_text);
		return OrderStatus;
	}

	// Last Product Ordered method
	public String LastProductOrdered() {
		String LastProductOrdered = ElementActions.getText(driver, lastProductAtSummary);
		return LastProductOrdered;
	}

	// Order Payment Status method
	public String orderPaymentStatus() {
		String OrderPaymentStatus = ElementActions.getText(driver, orderPaymentStatus_text);
		return OrderPaymentStatus;
	}

	// navigate to order history method
	public Product navigateToMyOrders() {
		ElementActions.click(driver, backToOrders_button);
		return this;
	}

	// verify order details method
	public Product verifyOrderDetails() {
		ElementActions.click(driver, orderDetails_button);
		return this;
	}

	// verify order name method
	public String orderName() {
		String OrderName = ElementActions.getText(driver, orderName_text);
		return OrderName;
	}

}
