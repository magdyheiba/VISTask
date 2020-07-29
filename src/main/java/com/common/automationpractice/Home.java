package com.common.automationpractice;

import org.openqa.selenium.WebDriver;

import com.shaft.gui.element.ElementActions;

import org.openqa.selenium.By;

public class Home {
	private WebDriver driver;
	private static String url = System.getProperty("gui.baseURL");

	private By signin_button = By.xpath("//a[@class='login']");
	private By womenTap_button = By.xpath("//a[text()='Women']");
	private By blousesTap_button = By.xpath("//a[text()='Blouses']");

	public Home navigateToAuthentication() {
		ElementActions.click(driver, signin_button);
		return this;
	}
	public Home navigateToBlouseTap() {
		ElementActions.hover(driver, womenTap_button);
		ElementActions.click(driver, blousesTap_button);
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
