package com.common.automationpractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.shaft.gui.element.ElementActions;
import com.shaft.tools.io.ReportManager;
import com.shaft.validation.Verifications;

public class Register {
	private WebDriver driver;
	// locators
	private By mr_radioButton = By.id("id_gender1");
	private By mrs_radioButton = By.id("id_gender2");
	private By firstName_input = By.id("customer_firstname");
	private By lastName_input = By.id("customer_lastname");
	private By email_input = By.id("email");
	private By password_input = By.id("passwd");
	private By days_select = By.id("days");
	private By months_select = By.id("months");
	private By years_select = By.id("years");
	private By signUpForNewsLetter_checkBox = By.id("newsletter");
	private By receiveSpecialOffers_checkBox = By.id("optin");
	private By address_firstName_input = By.id("firstname");
	private By address_lastName_input = By.id("lastname");
	private By company_input = By.id("company");
	private By address_input = By.id("address1");
	private By city_input = By.id("city");
	private By state_select = By.id("id_state");
	private By zipCode_input = By.id("postcode");
	private By country_select = By.id("id_country");
	private By additionalInfo_input = By.id("other");
	private By homePhone_input = By.id("phone");
	private By mobilePhone_input = By.id("phone_mobile");
	private By aliasAddress_input = By.id("alias");
	private By submit_button = By.id("submitAccount");
	private By myAccount_text = By.xpath("//h1[text()='My account']");

	// input Register method
	public Register inputRegisterNewAccount(String[] Register) {
		ReportManager.logDiscrete("Inputing Register screen test data " + Register);
		switch (Register[0].toLowerCase()) {
		case "mr":
			ElementActions.click(driver, mr_radioButton);
		case "mrs":
			ElementActions.click(driver, mrs_radioButton);
		}
		ElementActions.type(driver, firstName_input, Register[1]);
		ElementActions.type(driver, lastName_input, Register[2]);
		ElementActions.type(driver, email_input, Register[3]);
		ElementActions.type(driver, password_input, Register[4]);
		ElementActions.select(driver, days_select, Register[5]);
		ElementActions.select(driver, months_select, Register[6]);
		ElementActions.select(driver, years_select, Register[7]);
		if (Register[8].equalsIgnoreCase("yes")) {
			ElementActions.click(driver, signUpForNewsLetter_checkBox);
		}
		if (Register[9].equalsIgnoreCase("yes")) {
			ElementActions.click(driver, receiveSpecialOffers_checkBox);
		}
		ElementActions.type(driver, address_firstName_input, Register[10]);
		ElementActions.type(driver, address_lastName_input, Register[11]);
		ElementActions.type(driver, company_input, Register[12]);
		ElementActions.type(driver, address_input, Register[13]);
		ElementActions.type(driver, city_input, Register[14]);
		ElementActions.select(driver, state_select, Register[15]);
		ElementActions.type(driver, zipCode_input, Register[16]);
		ElementActions.select(driver, country_select, Register[17]);
		ElementActions.type(driver, additionalInfo_input, Register[18]);
		ElementActions.type(driver, homePhone_input, Register[19]);
		ElementActions.type(driver, mobilePhone_input, Register[20]);
		ElementActions.type(driver, aliasAddress_input, Register[21]);
		return this;
	}

	// verify Register method
	public Register verifyTheDataRegisterNewAccount(String[] Register) {
		ReportManager.logDiscrete("Verifying Register screen test data " + Register);
		switch (Register[0].toLowerCase()) {
		case "mr":
			Verifications.verifyElementAttribute(driver, mr_radioButton, "selected", "true");
		case "mrs":
			Verifications.verifyElementAttribute(driver, mrs_radioButton, "selected", "true");
		}
		Verifications.verifyElementAttribute(driver, firstName_input, "text", Register[1]);
		Verifications.verifyElementAttribute(driver, lastName_input, "text", Register[2]);
		Verifications.verifyElementAttribute(driver, email_input, "text", Register[3]);
		Verifications.verifyElementAttribute(driver, password_input, "text", Register[4]);
		Verifications.verifyElementAttribute(driver, days_select, "selectedText", Register[5]);
		Verifications.verifyElementAttribute(driver, months_select, "selectedText", Register[6]);
		Verifications.verifyElementAttribute(driver, years_select, "selectedText", Register[7]);
		if (Register[8].equalsIgnoreCase("yes")) {
			Verifications.verifyElementAttribute(driver, signUpForNewsLetter_checkBox, "selected", "true");
		} else {
			Verifications.verifyElementAttribute(driver, signUpForNewsLetter_checkBox, "selected", "false");
		}
		if (Register[9].equalsIgnoreCase("yes")) {
			Verifications.verifyElementAttribute(driver, receiveSpecialOffers_checkBox, "selected", "true");
		} else {
			Verifications.verifyElementAttribute(driver, receiveSpecialOffers_checkBox, "selected", "false");
		}
		Verifications.verifyElementAttribute(driver, address_firstName_input, "text", Register[10]);
		Verifications.verifyElementAttribute(driver, address_lastName_input, "text", Register[11]);
		Verifications.verifyElementAttribute(driver, company_input, "text", Register[12]);
		Verifications.verifyElementAttribute(driver, address_input, "text", Register[13]);
		Verifications.verifyElementAttribute(driver, city_input, "text", Register[14]);
		Verifications.verifyElementAttribute(driver, state_select, "selectedText", Register[15]);
		Verifications.verifyElementAttribute(driver, zipCode_input, "text", Register[16]);
		Verifications.verifyElementAttribute(driver, country_select, "selectedText", Register[17]);
		Verifications.verifyElementAttribute(driver, additionalInfo_input, "text", Register[18]);
		Verifications.verifyElementAttribute(driver, homePhone_input, "text", Register[19]);
		Verifications.verifyElementAttribute(driver, mobilePhone_input, "text", Register[20]);
		Verifications.verifyElementAttribute(driver, aliasAddress_input, "text", Register[21]);
		return this;
	}

	// click on register button
	public Register clickOnSubmitButton() {
		ReportManager.logDiscrete("Clicking on Register Button");
		ElementActions.click(driver, submit_button);
		return this;
	}

	// check if user registers successfully
	public String checkIfUserRegisterdSuccessfully() {
		String MyAccount = ElementActions.getText(driver, myAccount_text);
		return MyAccount;
	}

	// constructor
	public Register(ThreadLocal<WebDriver> driver) {
		this.driver = driver.get();
	}

}
