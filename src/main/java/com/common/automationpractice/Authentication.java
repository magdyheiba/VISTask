package com.common.automationpractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.shaft.gui.element.ElementActions;

public class Authentication {
	// variables
	private WebDriver driver;

	// locators
	private By email_register_input = By.id("email_create");
	private By submitEmail_register_button = By.id("SubmitCreate");
	private By email_login_input = By.id("email");
	private By password_input = By.id("passwd");
	private By signin_button = By.id("SubmitLogin");

	// constructor
	public Authentication(ThreadLocal<WebDriver> driver) {
		this.driver = driver.get();
	}

	// login method
	public void login(String Email, String Password) {
		ElementActions.type(driver, email_login_input, Email);
		ElementActions.typeSecure(driver, password_input, Password);
		ElementActions.click(driver, signin_button);
	}

	// Navigate to Register screen
	public void navigateToRegisterScreen(String Email) {
		ElementActions.type(driver, email_register_input, Email);
		ElementActions.click(driver, submitEmail_register_button);
	}

}
