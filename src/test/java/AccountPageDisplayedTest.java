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

public class AccountPageDisplayedTest {
    private WebDriver driver;
    private API api;
    private String accessToken;
    private String email;
    private String password;

    @Before
    public void setUp() {
        BrowserFactory browserFactory = new BrowserFactory();
        driver = browserFactory.getWebDriver();
        api = new API();
        Response response = api.createRandomUser();
        email = api.getEmail();
        password = api.getPassword();
        accessToken = response.jsonPath().getString("accessToken");
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.sendKeyToEmailField(email);
        loginPage.sendKeyToPasswordField(password);
        loginPage.clickLoginButton();
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    public void testAccountPageDisplayed() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickAccountButton();
        AccountPage accountPage = new AccountPage(driver);
        assertTrue(accountPage.textAccountPageIsDisplayed());
    }

    @After
    public void tearDown() {
        api.deleteUser(accessToken);
        driver.quit();
    }
}
