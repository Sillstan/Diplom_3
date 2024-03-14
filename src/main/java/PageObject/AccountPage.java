package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {
    WebDriver driver;
    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }
    private final By constructorButton = By.xpath(".//a[@href='/']/p[text()='Конструктор']");
    private final By logoButton = By.xpath(".//div/a[@href='/']");
    private final By exitButton = By.xpath(".//li/button[text()='Выход']");
    public final By textAccountPage = By.xpath(".//nav/p[text()='В этом разделе вы можете изменить свои персональные данные']");
    public boolean textAccountPageIsDisplayed() {
        return driver.findElement(textAccountPage).isDisplayed();
    }
    public void clickLogoButton() {
        driver.findElement(logoButton).click();
    }
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }
    public void clickExitButton() {
        driver.findElement(exitButton).click();
    }
}
