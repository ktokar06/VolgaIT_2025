package org.example.pages;

import io.qameta.allure.Step;
import org.example.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Класс страницы для работы с событиями кликов.
 * Предоставляет методы для взаимодействия с кнопками животных (Cat, Dog, Pig, Cow)
 */
public class ClickEventsPage extends BasePage {

    @FindBy(xpath = "//button[contains(., 'Cat')]")
    private WebElement catButton;

    @FindBy(xpath = "//button[contains(., 'Dog')]")
    private WebElement dogButton;

    @FindBy(xpath = "//button[contains(., 'Pig')]")
    private WebElement pigButton;

    @FindBy(xpath = "//button[contains(., 'Cow')]")
    private WebElement cowButton;

    @FindBy(id = "demo")
    private WebElement demoTextElement;

    public ClickEventsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Нажимает на кнопку "Cat" после ожидания ее доступности для клика.
     *
     * @return текущий экземпляр ClickEventsPage
     */
    @Step("Нажать на кнопку Cat")
    public ClickEventsPage clickCat() {
        WaitUtils.waitForElementClickable(driver, catButton, 10).click();
        return this;
    }

    /**
     * Нажимает на кнопку "Dog" после ожидания ее доступности для клика.
     *
     * @return текущий экземпляр ClickEventsPage
     */
    @Step("Нажать на кнопку Dog")
    public ClickEventsPage clickDog() {
        WaitUtils.waitForElementClickable(driver, dogButton, 10).click();
        return this;
    }

    /**
     * Нажимает на кнопку "Pig" после ожидания ее доступности для клика.
     *
     * @return текущий экземпляр ClickEventsPage
     */
    @Step("Нажать на кнопку Pig")
    public ClickEventsPage clickPig() {
        WaitUtils.waitForElementClickable(driver, pigButton, 10).click();
        return this;
    }

    /**
     * Нажимает на кнопку "Cow" после ожидания ее доступности для клика.
     *
     * @return текущий экземпляр ClickEventsPage
     */
    @Step("Нажать на кнопку Cow")
    public ClickEventsPage clickCow() {
        WaitUtils.waitForElementClickable(driver, cowButton, 10).click();
        return this;
    }

    /**
     * Получает текст из демо-элемента после ожидания его видимости.
     *
     * @return текст, содержащийся в демо-элементе
     */
    @Step("Получить текст из демо-элемента")
    public String getDemoText() {
        return WaitUtils.waitForElementVisible(driver, demoTextElement, 10).getText();
    }

    /**
     * Проверяет, содержит ли текст демо-элемента ожидаемую подстроку.
     *
     * @param expectedText ожидаемый текст для проверки
     * @return true - если демо-текст содержит ожидаемую подстроку, в противном случае - false
     */
    @Step("Проверить, что демо-текст содержит {expectedText}")
    public boolean isDemoTextContains(String expectedText) {
        String actualText = getDemoText();
        return actualText.contains(expectedText);
    }
}