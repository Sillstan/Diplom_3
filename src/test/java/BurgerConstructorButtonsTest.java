import Build.BrowserFactory;
import PageObject.MainPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class BurgerConstructorButtonsTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        BrowserFactory browserFactory = new BrowserFactory();
        driver = browserFactory.getWebDriver();
    }

    @Test
    @DisplayName("Переход на вкладку булки")
    public void testActiveBunsButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSaucesButton();
        mainPage.clickBunsButton();
        assertTrue(mainPage.bunsButtonIsActiv());
    }

    @Test
    @DisplayName("Переход на вкладку соусы")
    public void testActiveSaucesButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSaucesButton();
        assertTrue(mainPage.saucesButtonIsActiv());
    }

    @Test
    @DisplayName("Переход на вкладку начинки")
    public void testActiveFillingsButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingsButton();
        assertTrue(mainPage.fillingsButtonIsActiv());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
