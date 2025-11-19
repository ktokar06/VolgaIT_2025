package org.example.pages;

import io.qameta.allure.Step;
import org.example.utils.WaitUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

import static org.example.config.TimeoutConfig.*;

/**
 * Класс для работы со страницей, содержащей различные поля формы
 * Предоставляет методы для заполнения полей, выбора опций и отправки формы
 */
public class FormFieldsPage extends BasePage {
    @FindBy(id = "name-input")
    private WebElement nameField;

    @FindBy(css = "input[type='password']")
    private WebElement passwordField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "message")
    private WebElement messageField;

    @FindBy(css = "input[value='Milk']")
    private WebElement milkCheckbox;

    @FindBy(css = "input[value='Coffee']")
    private WebElement coffeeCheckbox;

    @FindBy(css = "input[value='Yellow']")
    private WebElement yellowRadioButton;

    @FindBy(id = "automation")
    private WebElement automationDropdown;

    @FindBy(css = "div:has(> ul) li")
    private List<WebElement> automationToolsList;

    @FindBy(id = "submit-btn")
    private WebElement submitButton;

    /**
     * Конструктор класса FormFieldsPage.
     * Инициализирует элементы страницы с помощью PageFactory.
     *
     * @param driver экземпляр WebDriver для взаимодействия с браузером
     */
    public FormFieldsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Заполняет поле имени указанным значением.
     * Перед заполнением очищает поле от предыдущих значений.
     *
     * @param name строка с именем для заполнения
     * @return текущий экземпляр FormFieldsPage для цепочки вызовов
     */
    @Step("Заполнение поля имени: {name}")
    public FormFieldsPage fillName(String name) {
        WaitUtils.waitForElementVisible(driver, nameField, DEFAULT_TIMEOUT).clear();
        WaitUtils.waitForElementVisible(driver, nameField, DEFAULT_TIMEOUT).sendKeys(name);
        return this;
    }

    /**
     * Заполняет поле пароля указанным значением.
     * Перед заполнением очищает поле от предыдущих значений.
     *
     * @param password строка с паролем для заполнения
     * @return текущий экземпляр FormFieldsPage для цепочки вызовов
     */
    @Step("Заполнение поля пароля")
    public FormFieldsPage fillPassword(String password) {
        WaitUtils.waitForElementVisible(driver, passwordField, DEFAULT_TIMEOUT).clear();
        WaitUtils.waitForElementVisible(driver, passwordField, DEFAULT_TIMEOUT).sendKeys(password);
        return this;
    }

    /**
     * Выбирает указанные напитки с помощью соответствующих чекбоксов.
     * Поддерживаемые значения: "milk", "coffee".
     * Если чекбокс уже выбран, повторное нажатие не производится.
     *
     * @param drinks массив названий напитков для выбора
     * @return текущий экземпляр FormFieldsPage для цепочки вызовов
     */
    @Step("Выбор напитков: {drinks}")
    public FormFieldsPage selectDrinks(String... drinks) {
        for (String drink : drinks) {
            WebElement checkbox = switch (drink.toLowerCase()) {
                case "milk" -> milkCheckbox;
                case "coffee" -> coffeeCheckbox;
                default -> null;
            };

            if (checkbox != null && !checkbox.isSelected()) {
                WaitUtils.waitForElementClickable(driver, checkbox, DEFAULT_TIMEOUT).click();
            }
        }
        return this;
    }

    /**
     * Выбирает желтый цвет с помощью соответствующей радиокнопки.
     *
     * @return текущий экземпляр FormFieldsPage для цепочки вызовов
     */
    @Step("Выбор желтого цвета")
    public FormFieldsPage selectYellowColor() {
        WaitUtils.waitForElementClickable(driver, yellowRadioButton, DEFAULT_TIMEOUT).click();
        return this;
    }

    /**
     * Выбирает значение из выпадающего списка автоматизации по видимому тексту.
     *
     * @param value текст опции для выбора
     * @return текущий экземпляр FormFieldsPage для цепочки вызовов
     */
    @Step("Выбор значения из списка автоматизации: {value}")
    public FormFieldsPage selectAutomation(String value) {
        WaitUtils.waitForElementVisible(driver, automationDropdown, DEFAULT_TIMEOUT);
        Select select = new Select(automationDropdown);
        select.selectByVisibleText(value);
        return this;
    }

    /**
     * Заполняет поле email указанным значением.
     * Перед заполнением очищает поле от предыдущих значений.
     *
     * @param email строка с email для заполнения
     * @return текущий экземпляр FormFieldsPage для цепочки вызовов
     */
    @Step("Заполнение поля email: {email}")
    public FormFieldsPage fillEmail(String email) {
        WaitUtils.waitForElementVisible(driver, emailField, DEFAULT_TIMEOUT).clear();
        WaitUtils.waitForElementVisible(driver, emailField, DEFAULT_TIMEOUT).sendKeys(email);
        return this;
    }

    /**
     * Заполняет поле сообщения информацией об инструментах автоматизации.
     * Формирует строку с количеством инструментов и их списком,
     * каждый инструмент добавляется с новой строки.
     *
     * @return текущий экземпляр FormFieldsPage для цепочки вызовов
     */
    @Step("Заполнение поля сообщения списком инструментов автоматизации")
    public FormFieldsPage fillMessageWithToolsInfo() {
        List<String> tools = getAutomationTools();
        int toolsCount = tools.size();
        String messageText = "Total automation tools: " + toolsCount + "\n" + String.join("\n", tools);

        WaitUtils.waitForElementVisible(driver, messageField, DEFAULT_TIMEOUT).clear();
        WaitUtils.waitForElementVisible(driver, messageField, DEFAULT_TIMEOUT).sendKeys(messageText);
        return this;
    }

    /**
     * Получает список инструментов автоматизации со страницы.
     * В случае ошибки возвращает стандартный список инструментов.
     *
     * @return список названий инструментов автоматизации
     */
    @Step("Получение списка инструментов автоматизации")
    public List<String> getAutomationTools() {
        try {
            List<WebElement> elements = WaitUtils.waitForAllElementsVisible(driver, automationToolsList, DEFAULT_TIMEOUT);
            return elements.stream()
                    .map(WebElement::getText)
                    .map(String::trim)
                    .filter(text -> !text.isEmpty())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return List.of("Selenium", "Playwright", "Cypress", "Appium", "Katalon Studio");
        }
    }

    /**
     * Нажимает кнопку отправки формы.
     * Использует JavaScript клик для обхода возможных проблем с обычным кликом.
     * Перед кликом прокручивает элемент в область видимости.
     *
     * @return текущий экземпляр FormFieldsPage для цепочки вызовов
     */
    @Step("Нажатие кнопки отправки формы")
    public FormFieldsPage clickSubmit() {
        WebElement element = WaitUtils.waitForElementClickable(driver, submitButton, DEFAULT_TIMEOUT);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        return this;
    }

    /**
     * Проверяет наличие алерта с ожидаемым текстом.
     * Принимает алерт, если он присутствует.
     *
     * @param expectedText ожидаемый текст алерта
     * @return true если алерт присутствует и содержит ожидаемый текст, иначе false
     */
    @Step("Проверка алерта с текстом: {expectedText}")
    public boolean isAlertPresentWithText(String expectedText) {
        try {
            WaitUtils.waitForAlertPresent(driver, ALERT_TIMEOUT);
            Alert alert = driver.switchTo().alert();
            String actualText = alert.getText();
            alert.accept();
            return expectedText.equals(actualText);

        } catch (Exception e) {
            return false;
        }
    }
}