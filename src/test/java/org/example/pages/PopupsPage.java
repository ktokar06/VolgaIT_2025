package org.example.pages;

import io.qameta.allure.Step;
import org.example.utils.WaitUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Класс Page Object для работы со страницей всплывающих окон (попапов, алертов, тултипов)
 * Наследует функциональность от базового класса BasePage
 */
public class PopupsPage extends BasePage {

    @FindBy(id = "alert") private WebElement alertButton;
    @FindBy(id = "confirm") private WebElement confirmButton;
    @FindBy(id = "prompt") private WebElement promptButton;
    @FindBy(id = "confirmResult") private WebElement confirmResult;
    @FindBy(id = "promptResult") private WebElement promptResult;
    @FindBy(css = ".tooltip_text") private WebElement tooltip;
    @FindBy(xpath = "//div[contains(text(), 'click me to see a tooltip')]") private WebElement tooltipTrigger;

    /**
     * Конструктор класса PopupsPage
     *
     * @param driver экземпляр WebDriver для управления браузером
     */
    public PopupsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Клик по кнопке вызова алерта
     *
     * @return текущий экземпляр PopupsPage для поддержки цепочки вызовов
     */
    @Step("Клик по кнопке Alert")
    public PopupsPage clickAlertButton() {
        WaitUtils.waitForElementClickable(driver, alertButton, 10).click();
        return this;
    }

    /**
     * Клик по кнопке вызова confirm-диалога
     *
     * @return текущий экземпляр PopupsPage для поддержки цепочки вызовов
     */
    @Step("Клик по кнопке Confirm")
    public PopupsPage clickConfirmButton() {
        WaitUtils.waitForElementClickable(driver, confirmButton, 10).click();
        return this;
    }

    /**
     * Клик по кнопке вызова prompt-диалога
     *
     * @return текущий экземпляр PopupsPage для поддержки цепочки вызовов
     */
    @Step("Клик по кнопке Prompt")
    public PopupsPage clickPromptButton() {
        WaitUtils.waitForElementClickable(driver, promptButton, 10).click();
        return this;
    }

    /**
     * Клик по элементу-триггеру для отображения тултипа
     *
     * @return текущий экземпляр PopupsPage для поддержки цепочки вызовов
     */
    @Step("Клик по тултипу")
    public PopupsPage clickTooltip() {
        WaitUtils.waitForElementClickable(driver, tooltipTrigger, 10).click();
        return this;
    }

    /**
     * Обработка алерта с возможностью принятия или отклонения
     *
     * @param accept true - принять алерт (OK), false - отклонить (Cancel)
     * @return текущий экземпляр PopupsPage для поддержки цепочки вызовов
     */
    @Step("Обработка алерта")
    public PopupsPage handleAlert(boolean accept) {
        if (WaitUtils.waitForAlertPresence(driver, 5)) {
            Alert alert = driver.switchTo().alert();
            if (accept) {
                alert.accept();
            } else {
                alert.dismiss();
            }
        }
        return this;
    }

    /**
     * Обработка prompt-диалога с вводом текста и выбором действия
     *
     * @param text текст для ввода в prompt (может быть null)
     * @param accept true - принять (OK), false - отклонить (Cancel)
     * @return текущий экземпляр PopupsPage для поддержки цепочки вызовов
     */
    @Step("Обработка промпта с текстом: {text}")
    public PopupsPage handlePrompt(String text, boolean accept) {
        if (WaitUtils.waitForAlertPresence(driver, 5)) {
            Alert alert = driver.switchTo().alert();
            if (text != null) {
                alert.sendKeys(text);
            }
            if (accept) {
                alert.accept();
            } else {
                alert.dismiss();
            }
        }
        return this;
    }

    /**
     * Получение текста результата confirm-диалога
     *
     * @return текст результата подтверждения
     */
    @Step("Получение результата подтверждения")
    public String getConfirmResult() {
        return WaitUtils.waitForElementVisible(driver, confirmResult, 5).getText();
    }

    /**
     * Получение текста результата prompt-диалога
     *
     * @return текст результата промпта
     */
    @Step("Получение результата промпта")
    public String getPromptResult() {
        return WaitUtils.waitForElementVisible(driver, promptResult, 5).getText();
    }

    /**
     * Получение текста тултипа
     *
     * @return текст тултипа
     */
    @Step("Получение текста тултипа")
    public String getTooltipText() {
        return WaitUtils.waitForElementVisible(driver, tooltip, 5).getText();
    }

    /**
     * Проверка видимости тултипа на странице
     *
     * @return true если тултип видим, false если не видим или возникла ошибка
     */
    @Step("Проверка видимости тултипа")
    public boolean isTooltipVisible() {
        try {
            return WaitUtils.waitForElementVisible(driver, tooltip, 5).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}