package com.automationpractice.commons;

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
	public Authentication(WebDriver driver) {
		this.driver = driver;
	}

	// login method
	public Register login(String[] LoginData) {
		ElementActions.type(driver, email_login_input, LoginData[0]);
		ElementActions.typeSecure(driver, password_input, LoginData[1]);
		ElementActions.click(driver, signin_button);
		return new Register(driver);
	}

	// Navigate to Register screen
	public Register navigateToRegisterScreen(String Email) {
		ElementActions.type(driver, email_register_input, Email);
		ElementActions.click(driver, submitEmail_register_button);
		return new Register(driver);
	}

}
