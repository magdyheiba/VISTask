package com.automationpractice.commons;

import org.openqa.selenium.WebDriver;

import com.automationpractice.order.Product;
import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.element.ElementActions;

import org.openqa.selenium.By;

public class Home {
	private WebDriver driver;
	private static String url = System.getProperty("gui.baseURL");

	private By signin_button = By.xpath("//a[@class='login']");
	private By womenTap_button = By.xpath("//a[text()='Women']");
	private By blousesTap_button = By.xpath("//a[text()='Blouses']");
	private By myOrders_button = By.xpath("//a[@title='My orders']");

	public Authentication navigateToAuthentication() {
		ElementActions.click(driver, signin_button);
		return new Authentication(driver);
	}
	
	 // Navigate to the URL method
    public Home navigate() {
	BrowserActions.navigateToURL(driver, url);
	return this;
    }

	public Product navigateToBlouseTap() {
		ElementActions.hover(driver, womenTap_button);
		ElementActions.click(driver, blousesTap_button);
		return new Product(driver);
	}

	public Home navigateToMyOrders() {
		ElementActions.click(driver, myOrders_button);
		return this;
	}

	// constructor
	public Home(ThreadLocal<WebDriver> driver) {
		this.driver = driver.get();
	}

	// actions
	public static String getUrl() {
		return url;
	}

}
