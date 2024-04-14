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

public class ExitUserTest {
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
    @DisplayName("Выход пользователя")
    public void testExitUser() {
        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickExitButton();
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.enterTextIsDisplayed());
    }

    @After
    public void tearDown() {
        api.deleteUser(accessToken);
        driver.quit();
    }
}
