package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    private final By enterText = By.xpath(".//main/div/h2[text()='Вход']");
    private final By emailField = By.xpath(".//div[@class='input pr-6 pl-6 input_type_text input_size_default']/input[@name='name']");
    private final By passwordField = By.xpath(".//div[@class='input pr-6 pl-6 input_type_password input_size_default']/input[@name='Пароль']");
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    private final By registrationButton = By.xpath(".//a[@href='/register' and text()='Зарегистрироваться']");
    private final By resetPasswordButton = By.xpath(".//a[@href='/forgot-password' and text()='Восстановить пароль']");
    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
    public void sendKeyToEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }
    public void sendKeyToPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
    public void clickResetPasswordButton() {
        driver.findElement(resetPasswordButton).click();
    }
    public boolean enterTextIsDisplayed() {
        return driver.findElement(enterText).isDisplayed();
    }
}
