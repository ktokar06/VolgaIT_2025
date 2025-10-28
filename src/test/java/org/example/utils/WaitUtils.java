package org.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Утилитарный класс для ожидания различных состояний элементов на веб-странице.
 */
public final class WaitUtils {

    /**
     * Ожидает появления алерта на странице с заданным таймаутом.
     *
     * @param driver  Веб-драйвер, используемый для управления браузером.
     * @param timeout Таймаут ожидания в секундах.
     * @return {@code true}, если алерт появился, иначе {@code false}.
     */
    public static boolean waitForAlertPresence(WebDriver driver, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Ожидает видимости элемента на странице.
     *
     * @param driver  Веб-драйвер, используемый для управления браузером.
     * @param element Элемент, которого нужно ожидать.
     * @param timeout Таймаут ожидания в секундах.
     * @return Видимый элемент.
     */
    public static WebElement waitForElementVisible(WebDriver driver, WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Ожидает кликабельности элемента на странице.
     *
     * @param driver  Веб-драйвер, используемый для управления браузером.
     * @param element Элемент, которого нужно ожидать.
     * @param timeout Таймаут ожидания в секундах.
     * @return Кликабельный элемент.
     */
    public static WebElement waitForElementClickable(WebDriver driver, WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Ожидает видимости всех элементов в списке.
     *
     * @param driver   Веб-драйвер, используемый для управления браузером.
     * @param elements Список элементов, которых нужно ожидать.
     * @param timeout  Таймаут ожидания в секундах.
     * @return Список видимых элементов.
     */
    public static List<WebElement> waitForAllElementsVisible(WebDriver driver, List<WebElement> elements, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }
}