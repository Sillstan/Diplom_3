import Build.API;
import Build.BrowserFactory;
import PageObject.LoginPage;
import PageObject.MainPage;
import PageObject.RegistrationPage;
import PageObject.ResetPasswordPage;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class LoginUserTest {
    private WebDriver driver;
    private API api;
    private String accessToken;
    private String email;
    private String password;

    @Before
    public void setUp() {
        api = new API();
        Response response = api.createRandomUser();
        email = api.getEmail();
        password = api.getPassword();
        accessToken = response.jsonPath().getString("accessToken");
        BrowserFactory browserFactory = new BrowserFactory();
        driver = browserFactory.getWebDriver();
    }

    @Test
    @DisplayName("Логин юзера по кнопке «Войти в аккаунт» на главной")
    public void testLoginUserMainPageLoginButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.sendKeyToEmailField(email);
        loginPage.sendKeyToPasswordField(password);
        loginPage.clickLoginButton();
        assertTrue(mainPage.textMainPageIsDisplayed());
    }

    @Test
    @DisplayName("Логин юзера по кнопке «Личный кабинет» на главной")
    public void testLoginUserMainPageAccountButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.sendKeyToEmailField(email);
        loginPage.sendKeyToPasswordField(password);
        loginPage.clickLoginButton();
        assertTrue(mainPage.textMainPageIsDisplayed());
    }

    @Test
    @DisplayName("Логин юзера через форму регистрации")
    public void testLoginUserRegistrationPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegistrationButton();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.clickLoginButton();
        loginPage.sendKeyToEmailField(email);
        loginPage.sendKeyToPasswordField(password);
        loginPage.clickLoginButton();
        assertTrue(mainPage.textMainPageIsDisplayed());
    }

    @Test
    @DisplayName("Логин юзера через страницу восстановления пароля")
    public void testLoginUserResetPasswordPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickResetPasswordButton();
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);
        resetPasswordPage.clickLoginButton();
        loginPage.sendKeyToEmailField(email);
        loginPage.sendKeyToPasswordField(password);
        loginPage.clickLoginButton();
        assertTrue(mainPage.textMainPageIsDisplayed());
    }

    @After
    public void tearDown() {
        api.deleteUser(accessToken);
        driver.quit();
    }
}
