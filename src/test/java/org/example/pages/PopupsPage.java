package org.example.pages;

import io.qameta.allure.Step;
import org.example.utils.WaitUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.example.config.TimeoutConfig.*;

/**
 * Класс Page Object для работы со страницей всплывающих окон
 */
public class PopupsPage extends BasePage {
    @FindBy(id = "alert")
    private WebElement alertButton;

    @FindBy(id = "confirm")
    private WebElement confirmButton;

    @FindBy(id = "prompt")
    private WebElement promptButton;

    @FindBy(id = "confirmResult")
    private WebElement confirmResult;

    @FindBy(id = "promptResult")
    private WebElement promptResult;

    @FindBy(css = ".tooltip_text")
    private WebElement tooltip;

    @FindBy(xpath = "//div[contains(@class, 'tooltip_1')]")
    private WebElement tooltipTrigger;

    /**
     * Конструктор класса PopupsPage.
     * Инициализирует элементы страницы с помощью PageFactory.
     *
     * @param driver экземпляр WebDriver для взаимодействия с браузером
     */
    public PopupsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Нажимает кнопку вызова простого алерта.
     * Ожидает кликабельности кнопки перед выполнением действия.
     *
     * @return текущий экземпляр PopupsPage для цепочки вызовов
     */
    @Step("Клик по кнопке Alert")
    public PopupsPage clickAlertButton() {
        WaitUtils.waitForElementClickable(driver, alertButton, DEFAULT_TIMEOUT).click();
        return this;
    }

    /**
     * Нажимает кнопку вызова окна подтверждения.
     * Ожидает кликабельности кнопки перед выполнением действия.
     *
     * @return текущий экземпляр PopupsPage для цепочки вызовов
     */
    @Step("Клик по кнопке Confirm")
    public PopupsPage clickConfirmButton() {
        WaitUtils.waitForElementClickable(driver, confirmButton, DEFAULT_TIMEOUT).click();
        return this;
    }

    /**
     * Нажимает кнопку вызова промпта с вводом текста.
     * Ожидает кликабельности кнопки перед выполнением действия.
     *
     * @return текущий экземпляр PopupsPage для цепочки вызовов
     */
    @Step("Клик по кнопке Prompt")
    public PopupsPage clickPromptButton() {
        WaitUtils.waitForElementClickable(driver, promptButton, DEFAULT_TIMEOUT).click();
        return this;
    }

    /**
     * Нажимает на триггер тултипа для его активации.
     * Ожидает кликабельности элемента перед выполнением действия.
     *
     * @return текущий экземпляр PopupsPage для цепочки вызовов
     */
    @Step("Клик по тултипу")
    public PopupsPage clickTooltip() {
        WaitUtils.waitForElementClickable(driver, tooltipTrigger, DEFAULT_TIMEOUT).click();
        return this;
    }

    /**
     * Обрабатывает алерт, принимая или отклоняя его.
     * Ожидает появления алерта перед взаимодействием.
     *
     * @param accept true - принять алерт, false - отклонить
     * @return текущий экземпляр PopupsPage для цепочки вызовов
     */
    @Step("Обработка алерта")
    public PopupsPage handleAlert(boolean accept) {
        Alert alert = WaitUtils.waitForAlertPresent(driver, ALERT_TIMEOUT);
        if (accept) {
            alert.accept();
        } else {
            alert.dismiss();
        }
        return this;
    }

    /**
     * Обрабатывает промпт, вводя текст и принимая или отклоняя его.
     * Ожидает появления промпта перед взаимодействием.
     *
     * @param text текст для ввода в промпт (может быть null или пустым)
     * @param accept true - принять промпт, false - отклонить
     * @return текущий экземпляр PopupsPage для цепочки вызовов
     */
    @Step("Обработка промпта с текстом: {text}")
    public PopupsPage handlePrompt(String text, boolean accept) {
        Alert alert = WaitUtils.waitForAlertPresent(driver, ALERT_TIMEOUT);
        if (text != null && !text.isEmpty()) {
            alert.sendKeys(text);
        }

        if (accept) {
            alert.accept();
        } else {
            alert.dismiss();
        }
        return this;
    }

    /**
     * Получает текст результата подтверждения.
     * Ожидает видимости элемента результата.
     *
     * @return текст результата подтверждения
     */
    @Step("Получение результата подтверждения")
    public String getConfirmResult() {
        return WaitUtils.waitForElementVisible(driver, confirmResult, SHORT_TIMEOUT).getText();
    }

    /**
     * Получает текст результата промпта.
     * Ожидает видимости элемента результата.
     *
     * @return текст результата промпта
     */
    @Step("Получение результата промпта")
    public String getPromptResult() {
        return WaitUtils.waitForElementVisible(driver, promptResult, SHORT_TIMEOUT).getText();
    }

    /**
     * Получает текст тултипа.
     * Ожидает видимости элемента тултипа.
     *
     * @return текст тултипа
     */
    @Step("Получение текста тултипа")
    public String getTooltipText() {
        return WaitUtils.waitForElementVisible(driver, tooltip, SHORT_TIMEOUT).getText();
    }

    /**
     * Проверяет видимость тултипа на странице.
     * В случае исключения возвращает false.
     *
     * @return true если тултип видим, иначе false
     */
    @Step("Проверка видимости тултипа")
    public boolean isTooltipVisible() {
        try {
            return WaitUtils.waitForElementVisible(driver, tooltip, SHORT_TIMEOUT).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Проверяет, содержит ли результат промпта ожидаемый текст.
     *
     * @param expectedText ожидаемый текст для поиска
     * @return true если результат промпта содержит ожидаемый текст, иначе false
     */
    @Step("Проверка, что результат промпта содержит текст: {expectedText}")
    public boolean isPromptResultContains(String expectedText) {
        String actualText = getPromptResult();
        return actualText.contains(expectedText);
    }
}