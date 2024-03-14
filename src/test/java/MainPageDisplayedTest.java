import Build.API;
import Build.BrowserFactory;
import PageObject.AccountPage;
import PageObject.LoginPage;
import PageObject.MainPage;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class MainPageDisplayedTest {
    private WebDriver driver;
    private API api;
    private String accessToken;

    @Before
    public void setUp() {
        BrowserFactory browserFactory = new BrowserFactory();
        driver = browserFactory.getWebDriver();
        api = new API();
        Response response = api.createRandomUser();
        String email = api.getEmail();
        String password = api.getPassword();
        accessToken = response.jsonPath().getString("accessToken");
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.sendKeyToEmailField(email);
        loginPage.sendKeyToPasswordField(password);
        loginPage.clickLoginButton();
        mainPage.clickAccountButton();
    }

    @Test
    @DisplayName("Переход по логотипу")
    public void testLogoButton() {
        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickLogoButton();
        MainPage mainPage = new MainPage(driver);
        assertTrue(mainPage.textMainPageIsDisplayed());
    }

    @Test
    @DisplayName("Переход по кнопке конструктор")
    public void testConstructorButton() {
        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickConstructorButton();
        MainPage mainPage = new MainPage(driver);
        assertTrue(mainPage.textMainPageIsDisplayed());
    }

    @After
    public void tearDown() {
        api.deleteUser(accessToken);
        driver.quit();
    }
}
