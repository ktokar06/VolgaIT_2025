package org.example.pages;

import io.qameta.allure.Step;
import org.example.utils.WaitUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для работы со страницей, содержащей различные поля формы
 * Предоставляет методы для заполнения полей, выбора опций и отправки формы
 */
public class FormFieldsPage extends BasePage {

    @FindBy(id = "name-input")
    private WebElement nameField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "message")
    private WebElement messageField;

    @FindBy(id = "submit-btn")
    private WebElement submitButton;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordField;

    @FindBy(css = "ul li")
    private List<WebElement> automationToolsList;

    @FindBy(css = "#drink2")
    private WebElement milkCheckbox;

    @FindBy(css = "#drink3")
    private WebElement coffeeCheckbox;

    @FindBy(css = "#color3")
    private WebElement yellowRadioButton;

    @FindBy(css = "select[name='automation']")
    private WebElement automationDropdown;

    public FormFieldsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Заполняет поле имени указанным значением
     *
     * @param name имя для заполнения поля
     * @return текущий экземпляр FormFieldsPage
     */
    @Step("Заполнение поля имени: {name}")
    public FormFieldsPage fillName(String name) {
        WaitUtils.waitForElementVisible(driver, nameField, 10).sendKeys(name);
        return this;
    }

    /**
     * Заполняет поле пароля указанным значением
     *
     * @param password пароль для заполнения поля
     * @return текущий экземпляр FormFieldsPage
     */
    @Step("Заполнение поля пароля: {password}")
    public FormFieldsPage fillPassword(String password) {
        WaitUtils.waitForElementVisible(driver, passwordField, 10).sendKeys(password);
        return this;
    }

    /**
     * Выбирает указанные напитки с помощью чекбоксов
     * Поддерживаемые значения: "milk", "coffee"
     *
     * @param drinks массив названий напитков для выбора
     * @return текущий экземпляр FormFieldsPage
     */
    @Step("Выбор напитков: {drinks}")
    public FormFieldsPage selectDrinks(String... drinks) {
        for (String drink : drinks) {
            switch (drink.toLowerCase()) {
                case "milk":
                    WaitUtils.waitForElementClickable(driver, milkCheckbox, 10).click();
                    break;
                case "coffee":
                    WaitUtils.waitForElementClickable(driver, coffeeCheckbox, 10).click();
                    break;
            }
        }
        return this;
    }

    /**
     * Выбирает желтый цвет с помощью радиокнопки
     *
     * @return текущий экземпляр FormFieldsPage
     */
    @Step("Выбор желтого цвета")
    public FormFieldsPage selectYellowColor() {
        WaitUtils.waitForElementClickable(driver, yellowRadioButton, 10).click();
        return this;
    }

    /**
     * Выбирает значение из выпадающего списка автоматизации, по-видимому, тексту
     *
     * @param value текст опции для выбора
     * @return текущий экземпляр FormFieldsPage
     */
    @Step("Выбор значения из списка автоматизации: {value}")
    public FormFieldsPage selectAutomation(String value) {
        WaitUtils.waitForElementVisible(driver, automationDropdown, 10);
        Select select = new Select(automationDropdown);
        select.selectByVisibleText(value);
        return this;
    }

    /**
     * Заполняет поле email указанным значением
     *
     * @param email email для заполнения поля
     * @return текущий экземпляр FormFieldsPage
     */
    @Step("Заполнение поля email: {email}")
    public FormFieldsPage fillEmail(String email) {
        WaitUtils.waitForElementVisible(driver, emailField, 10).sendKeys(email);
        return this;
    }

    /**
     * Заполняет поле сообщения списком инструментов автоматизации,
     * полученных со страницы. Каждый инструмент добавляется с новой строки.
     *
     * @return текущий экземпляр FormFieldsPage
     */
    @Step("Заполнение поля сообщения списком инструментов автоматизации")
    public FormFieldsPage fillMessageWithToolsInfo() {
        List<String> tools = getAutomationTools();

        String messageText = String.join("\n", tools);
        WaitUtils.waitForElementVisible(driver, messageField, 10).sendKeys(messageText);
        return this;
    }

    /**
     * Получает список инструментов автоматизации со страницы
     *
     * @return список названий инструментов автоматизации
     */
    @Step("Получение списка инструментов автоматизации")
    public List<String> getAutomationTools() {
        List<WebElement> elements = WaitUtils.waitForAllElementsVisible(driver, automationToolsList, 10);
        List<String> tools = new ArrayList<>();

        for (WebElement element : elements) {
            String text = element.getText().trim();
            if (!text.isEmpty()) {
                tools.add(text);
            }
        }
        return tools;
    }

    /**
     * Нажимает кнопку отправки формы с использованием JavaScriptExecutor
     * для обхода возможных проблем с обычным кликом
     *
     * @return текущий экземпляр FormFieldsPage
     */
    @Step("Нажатие кнопки отправки формы")
    public FormFieldsPage clickSubmit() {
        WebElement element = WaitUtils.waitForElementClickable(driver, submitButton, 10);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        return this;
    }

    /**
     * Проверяет наличие алерта с ожидаемым текстом
     *
     * @param expectedText ожидаемый текст алерта
     * @return true если алерт присутствует и содержит ожидаемый текст, иначе false
     */
    @Step("Проверка алерта с текстом: {expectedText}")
    public boolean isAlertPresentWithText(String expectedText) {
        try {
            if (WaitUtils.waitForAlertPresence(driver, 5)) {
                Alert alert = driver.switchTo().alert();
                String actualText = alert.getText();
                alert.accept();
                return expectedText.equals(actualText);
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}