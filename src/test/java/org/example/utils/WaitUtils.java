package org.example.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Утилитарный класс для ожидания различных состояний элементов на веб-странице.
 */
public final class WaitUtils {

    /**
     * Ожидает появления алерта на странице в течение указанного времени.
     * Возвращает объект Alert для дальнейшего взаимодействия.
     *
     * @param driver  экземпляр WebDriver для управления браузером
     * @param timeout время ожидания в секундах
     * @return объект Alert для взаимодействия с диалоговым окном
     * @throws TimeoutException если алерт не появился в течение заданного времени
     */
    public static Alert waitForAlertPresent(WebDriver driver, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    /**
     * Ожидает видимости элемента на странице в течение указанного времени.
     * Элемент считается видимым, когда он отображается и имеет ненулевой размер.
     *
     * @param driver  экземпляр WebDriver для управления браузером
     * @param element веб-элемент, видимость которого нужно ожидать
     * @param timeout время ожидания в секундах
     * @return видимый веб-элемент
     * @throws TimeoutException если элемент не стал видимым в течение заданного времени
     */
    public static WebElement waitForElementVisible(WebDriver driver, WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Ожидает кликабельности элемента на странице в течение указанного времени.
     * Элемент считается кликабельным, когда он видим и включен.
     *
     * @param driver  экземпляр WebDriver для управления браузером
     * @param element веб-элемент, кликабельность которого нужно ожидать
     * @param timeout время ожидания в секундах
     * @return кликабельный веб-элемент
     * @throws TimeoutException если элемент не стал кликабельным в течение заданного времени
     */
    public static WebElement waitForElementClickable(WebDriver driver, WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Ожидает видимости всех элементов в списке в течение указанного времени.
     * Все элементы должны стать видимыми для успешного завершения ожидания.
     *
     * @param driver   экземпляр WebDriver для управления браузером
     * @param elements список веб-элементов, видимость которых нужно ожидать
     * @param timeout  время ожидания в секундах
     * @return список видимых веб-элементов
     * @throws TimeoutException если не все элементы стали видимыми в течение заданного времени
     */
    public static List<WebElement> waitForAllElementsVisible(WebDriver driver, List<WebElement> elements, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }
}