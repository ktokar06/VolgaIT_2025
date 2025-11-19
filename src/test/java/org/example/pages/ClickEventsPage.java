package org.example.pages;

import io.qameta.allure.Step;
import org.example.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.example.config.TimeoutConfig.*;

/**
 * Класс страницы для работы с событиями кликов.
 * Предоставляет методы для взаимодействия с кнопками животных (Cat, Dog, Pig, Cow)
 */
public class ClickEventsPage extends BasePage {
    @FindBy(xpath = "//button[contains(@class, 'custom_btn') and contains(., 'Cat')]")
    private WebElement catButton;

    @FindBy(xpath = "//button[contains(@class, 'custom_btn') and contains(., 'Dog')]")
    private WebElement dogButton;

    @FindBy(xpath = "//button[contains(@class, 'custom_btn') and contains(., 'Pig')]")
    private WebElement pigButton;

    @FindBy(xpath = "//button[contains(@class, 'custom_btn') and contains(., 'Cow')]")
    private WebElement cowButton;

    @FindBy(id = "demo")
    private WebElement demoTextElement;

    /**
     * Конструктор класса ClickEventsPage.
     * Инициализирует элементы страницы с помощью PageFactory.
     *
     * @param driver экземпляр WebDriver для взаимодействия с браузером
     */
    public ClickEventsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Нажимает на кнопку с изображением кота.
     * Ожидает кликабельности кнопки перед выполнением действия.
     *
     * @return текущий экземпляр ClickEventsPage для цепочки вызовов
     */
    @Step("Нажать на кнопку Cat")
    public ClickEventsPage clickCat() {
        WaitUtils.waitForElementClickable(driver, catButton, DEFAULT_TIMEOUT).click();
        return this;
    }

    /**
     * Нажимает на кнопку с изображением собаки.
     * Ожидает кликабельности кнопки перед выполнением действия.
     *
     * @return текущий экземпляр ClickEventsPage для цепочки вызовов
     */
    @Step("Нажать на кнопку Dog")
    public ClickEventsPage clickDog() {
        WaitUtils.waitForElementClickable(driver, dogButton, DEFAULT_TIMEOUT).click();
        return this;
    }

    /**
     * Нажимает на кнопку с изображением свиньи.
     * Ожидает кликабельности кнопки перед выполнением действия.
     *
     * @return текущий экземпляр ClickEventsPage для цепочки вызовов
     */
    @Step("Нажать на кнопку Pig")
    public ClickEventsPage clickPig() {
        WaitUtils.waitForElementClickable(driver, pigButton, DEFAULT_TIMEOUT).click();
        return this;
    }

    /**
     * Нажимает на кнопку с изображением коровы.
     * Ожидает кликабельности кнопки перед выполнением действия.
     *
     * @return текущий экземпляр ClickEventsPage для цепочки вызовов
     */
    @Step("Нажать на кнопку Cow")
    public ClickEventsPage clickCow() {
        WaitUtils.waitForElementClickable(driver, cowButton, DEFAULT_TIMEOUT).click();
        return this;
    }

    /**
     * Получает текст из демо-элемента после ожидания его видимости.
     * Демо-элемент отображает звуки животных после соответствующих кликов.
     *
     * @return текст, содержащийся в демо-элементе
     */
    @Step("Получить текст из демо-элемента")
    public String getDemoText() {
        return WaitUtils.waitForElementVisible(driver, demoTextElement, DEFAULT_TIMEOUT).getText();
    }

    /**
     * Проверяет, содержит ли текст демо-элемента ожидаемую подстроку.
     * Используется для проверки правильности отображения звуков животных.
     *
     * @param expectedText ожидаемая подстрока для поиска в демо-тексте
     * @return true если демо-текст содержит ожидаемую подстроку, иначе false
     */
    @Step("Проверить, что демо-текст содержит {expectedText}")
    public boolean isDemoTextContains(String expectedText) {
        String actualText = getDemoText();
        return actualText.contains(expectedText);
    }
}