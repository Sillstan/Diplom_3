import Build.BrowserFactory;
import PageObject.LoginPage;
import PageObject.MainPage;
import PageObject.RegistrationPage;
import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class RegistrationUserTest {
    private WebDriver driver;
    private final Faker faker = new Faker();
    private String name = faker.name().username();
    private String password = faker.internet().password();
    private String incorrectPassword = "1234";
    private String email = faker.internet().emailAddress();

    @Before
    public void setUp() {
        BrowserFactory browserFactory = new BrowserFactory();
        driver = browserFactory.getWebDriver();
    }

    @Test
    @DisplayName("Проверка регистрации пользователя")
    public void testRegistrationUser() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegistrationButton();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.clickAndSendKeyToNameField(name);
        registrationPage.clickAndSendKeyToEmailField(email);
        registrationPage.clickAndSendKeyToPasswordField(password);
        registrationPage.clickRegistrationButton();
        assertTrue(loginPage.enterTextIsDisplayed());
    }

    @Test
    @DisplayName("Проверка регистрации пользователя с невалидным паролем")
    public void testRegistrationUserWithIncorrectPassword() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegistrationButton();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.clickAndSendKeyToNameField(name);
        registrationPage.clickAndSendKeyToEmailField(email);
        registrationPage.clickAndSendKeyToPasswordField(incorrectPassword);
        registrationPage.clickRegistrationButton();
        assertTrue(registrationPage.errorPasswordFieldIsDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
