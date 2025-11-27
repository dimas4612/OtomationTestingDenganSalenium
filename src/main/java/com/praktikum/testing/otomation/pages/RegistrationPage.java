package com.praktikum.testing.otomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends BasePage {

    @FindBy(className = "ico-register")
    private WebElement registerLink;

    @FindBy(id = "gender-male")
    private WebElement genderMaleRadio;

    @FindBy(id = "gender-female")
    private WebElement genderFemaleRadio;

    @FindBy(id = "FirstName")
    private WebElement firstNameField;

    @FindBy(id = "LastName")
    private WebElement lastNameField;

    @FindBy(id = "Email")
    private WebElement emailField;

    @FindBy(id = "Password")
    private WebElement passwordField;

    @FindBy(id = "ConfirmPassword")
    private WebElement confirmPasswordField;

    @FindBy(id = "register-button")
    private WebElement registerButton;

    @FindBy(className = "result")
    private WebElement successMessage;

    @FindBy(css = ".page-title h1")
    private WebElement pageTitle;

    @FindBy(css = ".field-validation-error[data-valmsg-for='FirstName']")
    private WebElement firstNameError;

    @FindBy(css = ".field-validation-error[data-valmsg-for='Email']")
    private WebElement emailError;

    @FindBy(css = ".field-validation-error[data-valmsg-for='Password']")
    private WebElement passwordError;

    @FindBy(css = ".field-validation-error[data-valmsg-for='ConfirmPassword']")
    private WebElement confirmPasswordError;

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToRegisterPage() {
        navigateTo("http://demowebshop.tricentis.com/register");
    }

    public void selectGender(String gender) {
        if ("Male".equalsIgnoreCase(gender)) click(genderMaleRadio);
        else click(genderFemaleRadio);
    }

    public void enterFirstName(String firstName) { enterText(firstNameField, firstName); }
    public void enterLastName(String lastName) { enterText(lastNameField, lastName); }
    public void enterEmail(String email) { enterText(emailField, email); }
    public void enterPassword(String password) { enterText(passwordField, password); }
    public void enterConfirmPassword(String confirmPassword) { enterText(confirmPasswordField, confirmPassword); }

    public void clickRegisterButton() { click(registerButton); }

    public String getSuccessMessage() {
        return getText(successMessage);
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public String getFirstNameError() {
        try { waitForVisible(firstNameError); return getText(firstNameError); } catch (Exception e) { return ""; }
    }

    public String getEmailError() {
        try { waitForVisible(emailError); return getText(emailError); } catch (Exception e) { return ""; }
    }

    // Helper untuk register full
    public void registerUser(String gender, String fName, String lName, String email, String pass) {
        selectGender(gender);
        enterFirstName(fName);
        enterLastName(lName);
        enterEmail(email);
        enterPassword(pass);
        enterConfirmPassword(pass);
        clickRegisterButton();
    }

    public boolean isRegistrationSuccessful() {
        try {
            return getText(successMessage).contains("Your registration completed");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean hasValidationErrors() {
        return !getFirstNameError().isEmpty() || !getEmailError().isEmpty();
    }
}