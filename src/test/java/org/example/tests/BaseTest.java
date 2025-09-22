package org.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.time.Duration;

/**
 * Базовый класс для всех тестов, содержащий общие настройки и методы.
 */
public class BaseTest {

    protected WebDriver driver;

    /**
     * Метод, выполняемый перед каждым тестом.
     * Инициализирует ChromeDriver, максимизирует окно браузера и устанавливает неявные ожидания.
     */
    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Неподдерживаемый браузер: " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    /**
     * Метод, выполняемый после каждого теста.
     * Закрывает браузер.
     */
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            try {
                Allure.getLifecycle().addAttachment(
                        "Скриншот",
                        "image/png",
                        "png",
                        ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)
                );
            } catch (Exception e) {
                System.err.println("Не удалось сделать скриншот: " + e.getMessage());
            } finally {
                driver.quit();
                driver = null;
            }
        }
    }
}