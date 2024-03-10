package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By textMainPage = By.xpath(".//h1[text()='Соберите бургер']");
    private final By accountButton = By.xpath(".//a[@href='/account']");
    private final By bunsButton = By.xpath("//div[contains(@class, 'tab_tab')]/span[@class='text text_type_main-default' and text()='Булки']");
    private final By saucesButton = By.xpath("//span[@class='text text_type_main-default'][text()='Соусы']");
    private final By fillingsButton = By.xpath("//span[@class='text text_type_main-default'][text()='Начинки']");
    private final By activeBunsButton = By.xpath("//div[contains(@class, 'tab_tab_type_current')]/span[@class='text text_type_main-default' and text()='Булки']");
    private final By activeSausesButton = By.xpath(".//h2[text()='Булки']");
    private final By activeFillingsButton = By.xpath(".//h2[text()='Булки']");

    public void clickAccountButton() {
        driver.findElement(accountButton).click();
    }
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
    public boolean textMainPageIsDisplayed() {
        return driver.findElement(textMainPage).isDisplayed();
    }
    public void clickBunsButton() {
        driver.findElement(bunsButton).click();
    }
    public void clickSaucesButton() {
        driver.findElement(saucesButton).click();
    }
    public void clickFillingsButton() {
        driver.findElement(fillingsButton).click();
    }
    public boolean bunsButtonIsActiv() {
        return driver.findElement(activeBunsButton).isDisplayed();
    }
    public boolean saucesButtonIsActiv() {
        return driver.findElement(activeSausesButton).isDisplayed();
    }
    public boolean fillingsButtonIsActiv() {
        return driver.findElement(activeFillingsButton).isDisplayed();
    }
}
