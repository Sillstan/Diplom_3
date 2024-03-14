package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResetPasswordPage {
    WebDriver driver;
    public ResetPasswordPage(WebDriver driver) {
        this.driver = driver;
    }
    private final By loginButton = By.xpath(".//div/p/a[@href = '/login' and text() = 'Войти']");
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}
