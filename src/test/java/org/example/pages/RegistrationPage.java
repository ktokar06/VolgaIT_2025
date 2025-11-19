package org.example.pages;

import org.example.config.TaskSolver;
import org.example.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import io.qameta.allure.Step;

import java.util.List;

/**
 * Page Object класс для страницы регистрации.
 * Предоставляет методы для заполнения полей формы регистрации,
 * выбора опций и отправки данных.
 */
public class RegistrationPage extends BasePage {
    @FindBy(name = "name")
    private WebElement firstNameField;

    @FindBy(xpath = "//label[contains(text(),'Last Name')]/following-sibling::input")
    private WebElement lastNameField;

    @FindBy(xpath = "//input[@type='radio' and following-sibling::text()[contains(., 'Single')]]")
    private WebElement singleRadio;

    @FindBy(xpath = "//input[@type='radio' and following-sibling::text()[contains(., 'Married')]]")
    private WebElement marriedRadio;

    @FindBy(xpath = "//input[@type='radio' and following-sibling::text()[contains(., 'Divorced')]]")
    private WebElement divorcedRadio;

    @FindBy(xpath = "//input[@type='checkbox' and following-sibling::text()[contains(., 'Dance')]]")
    private WebElement danceCheckbox;

    @FindBy(xpath = "//input[@type='checkbox' and following-sibling::text()[contains(., 'Reading')]]")
    private WebElement readingCheckbox;

    @FindBy(xpath = "//input[@type='checkbox' and following-sibling::text()[contains(., 'Cricket')]]")
    private WebElement cricketCheckbox;

    @FindBy(xpath = "//label[contains(text(),'Date of Birth')]/following-sibling::div[1]//select")
    private WebElement monthSelect;

    @FindBy(xpath = "//label[contains(text(),'Date of Birth')]/following-sibling::div[2]//select")
    private WebElement daySelect;

    @FindBy(xpath = "//label[contains(text(),'Date of Birth')]/following-sibling::div[3]//select")
    private WebElement yearSelect;

    @FindBy(name = "phone")
    private WebElement phoneField;

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(xpath = "//label[contains(text(),'About Yourself')]/following-sibling::textarea")
    private WebElement aboutField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(name = "c_password")
    private WebElement confirmPasswordField;

    @FindBy(css = "input[type='submit']")
    private WebElement submitBtn;

    @FindBy(css = ".success, .alert-success")
    private WebElement successText;

    /**
     * Конструктор класса RegistrationPage
     *
     * @param driver экземпляр WebDriver для управления браузером
     */
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Заполняет поле "Имя" указанным значением
     *
     * @param name имя для заполнения
     * @return текущий экземпляр RegistrationPage для цепочки вызовов
     */
    @Step("Заполнить поле 'Имя': {name}")
    public RegistrationPage fillName(String name) {
        WaitUtils.waitForElementVisible(driver, firstNameField, 10);
        firstNameField.clear();
        firstNameField.sendKeys(name);
        return this;
    }

    /**
     * Заполняет поле "Фамилия" указанным значением
     *
     * @param lastName фамилия для заполнения
     * @return текущий экземпляр RegistrationPage для цепочки вызовов
     */
    @Step("Заполнить поле 'Фамилия': {lastName}")
    public RegistrationPage fillLastName(String lastName) {
        WaitUtils.waitForElementVisible(driver, lastNameField, 10);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
        return this;
    }

    /**
     * Выбирает семейное положение из доступных опций
     *
     * @param status семейное положение ("single", "married", "divorced")
     * @return текущий экземпляр RegistrationPage для цепочки вызовов
     * @throws IllegalArgumentException если передан неизвестный статус
     */
    @Step("Выбрать семейное положение: {status}")
    public RegistrationPage fillMaritalStatus(String status) {
        WebElement radio = switch (status.toLowerCase()) {
            case "single" -> singleRadio;
            case "married" -> marriedRadio;
            case "divorced" -> divorcedRadio;
            default -> throw new IllegalArgumentException("Неизвестный статус: " + status);
        };

        WaitUtils.waitForElementClickable(driver, radio, 10);
        radio.click();
        return this;
    }

    /**
     * Выбирает одно хобби из доступных опций
     *
     * @param hobby хобби для выбора ("dance", "reading", "cricket")
     * @return текущий экземпляр RegistrationPage для цепочки вызовов
     * @throws IllegalArgumentException если передан неизвестный тип хобби
     */
    @Step("Выбрать хобби: {hobby}")
    public RegistrationPage fillHobby(String hobby) {
        WebElement checkbox = switch (hobby.toLowerCase()) {
            case "dance" -> danceCheckbox;
            case "reading" -> readingCheckbox;
            case "cricket" -> cricketCheckbox;
            default -> throw new IllegalArgumentException("Неизвестное хобби: " + hobby);
        };

        WaitUtils.waitForElementClickable(driver, checkbox, 10);

        if (!checkbox.isSelected()) {
            checkbox.click();
        }
        return this;
    }

    /**
     * Выбирает несколько хобби из доступных опций
     *
     * @param hobbies массив хобби для выбора
     * @return текущий экземпляр RegistrationPage для цепочки вызовов
     */
    @Step("Выбрать несколько хобби")
    public RegistrationPage fillHobbies(String... hobbies) {
        for (String hobby : hobbies) {
            fillHobby(hobby);
        }
        return this;
    }

    /**
     * Снимает выбор с указанного хобби
     *
     * @param hobby хобби для снятия выбора
     * @return текущий экземпляр RegistrationPage для цепочки вызовов
     * @throws IllegalArgumentException если передан неизвестный тип хобби
     */
    @Step("Снять выбор хобби: {hobby}")
    public RegistrationPage uncheckHobby(String hobby) {
        WebElement checkbox = switch (hobby.toLowerCase()) {
            case "dance" -> danceCheckbox;
            case "reading" -> readingCheckbox;
            case "cricket" -> cricketCheckbox;
            default -> throw new IllegalArgumentException("Неизвестное хобби: " + hobby);
        };

        WaitUtils.waitForElementClickable(driver, checkbox, 10);

        if (checkbox.isSelected()) {
            checkbox.click();
        }
        return this;
    }

    /**
     * Заполняет дату рождения значениями по умолчанию (первые доступные значения)
     *
     * @return текущий экземпляр RegistrationPage для цепочки вызовов
     */
    @Step("Заполнить дату рождения")
    public RegistrationPage fillDateOfBirth() {
        WaitUtils.waitForElementVisible(driver, monthSelect, 10);
        Select monthDropdown = new Select(monthSelect);
        List<WebElement> monthOptions = monthDropdown.getOptions();
        if (monthOptions.size() > 1) {
            monthDropdown.selectByIndex(1);
        }

        WaitUtils.waitForElementVisible(driver, daySelect, 10);
        Select dayDropdown = new Select(daySelect);
        List<WebElement> dayOptions = dayDropdown.getOptions();
        if (dayOptions.size() > 1) {
            dayDropdown.selectByIndex(1);
        }

        WaitUtils.waitForElementVisible(driver, yearSelect, 10);
        Select yearDropdown = new Select(yearSelect);
        List<WebElement> yearOptions = yearDropdown.getOptions();
        if (yearOptions.size() > 1) {
            yearDropdown.selectByIndex(1);
        }

        return this;
    }

    /**
     * Заполняет дату рождения указанными значениями
     *
     * @param month месяц рождения
     * @param day день рождения
     * @param year год рождения
     * @return текущий экземпляр RegistrationPage для цепочки вызовов
     */
    @Step("Заполнить дату рождения с определенными значениями")
    public RegistrationPage fillDateOfBirth(String month, String day, String year) {
        try {
            WaitUtils.waitForElementVisible(driver, monthSelect, 10);
            new Select(monthSelect).selectByVisibleText(month);
        } catch (Exception e) {
            Select monthDropdown = new Select(monthSelect);
            if (monthDropdown.getOptions().size() > 1) {
                monthDropdown.selectByIndex(1);
            }
        }

        try {
            WaitUtils.waitForElementVisible(driver, daySelect, 10);
            new Select(daySelect).selectByVisibleText(day);
        } catch (Exception e) {
            Select dayDropdown = new Select(daySelect);
            if (dayDropdown.getOptions().size() > 1) {
                dayDropdown.selectByIndex(1);
            }
        }

        try {
            WaitUtils.waitForElementVisible(driver, yearSelect, 10);
            new Select(yearSelect).selectByVisibleText(year);
        } catch (Exception e) {
            Select yearDropdown = new Select(yearSelect);
            if (yearDropdown.getOptions().size() > 1) {
                yearDropdown.selectByIndex(1);
            }
        }

        return this;
    }

    /**
     * Заполняет поле "Телефон" указанным значением
     *
     * @param phone номер телефона для заполнения
     * @return текущий экземпляр RegistrationPage для цепочки вызовов
     */
    @Step("Заполнить поле 'Телефон': {phone}")
    public RegistrationPage fillPhone(String phone) {
        WaitUtils.waitForElementVisible(driver, phoneField, 10);
        phoneField.clear();
        phoneField.sendKeys(phone);
        return this;
    }

    /**
     * Заполняет поле "Имя пользователя" указанным значением
     *
     * @param username имя пользователя для заполнения
     * @return текущий экземпляр RegistrationPage для цепочки вызовов
     */
    @Step("Заполнить поле 'Имя пользователя': {username}")
    public RegistrationPage fillUsername(String username) {
        WaitUtils.waitForElementVisible(driver, usernameField, 10);
        usernameField.clear();
        usernameField.sendKeys(username);
        return this;
    }

    /**
     * Заполняет поле "Email" указанным значением
     *
     * @param email email для заполнения
     * @return текущий экземпляр RegistrationPage для цепочки вызовов
     */
    @Step("Заполнить поле 'Email': {email}")
    public RegistrationPage fillEmail(String email) {
        WaitUtils.waitForElementVisible(driver, emailField, 10);
        emailField.clear();
        emailField.sendKeys(email);
        return this;
    }

    /**
     * Заполняет поле "О себе" указанным значением
     *
     * @param about информация о себе для заполнения
     * @return текущий экземпляр RegistrationPage для цепочки вызовов
     */
    @Step("Заполнить поле 'О себе': {about}")
    public RegistrationPage fillAbout(String about) {
        WaitUtils.waitForElementVisible(driver, aboutField, 10);
        aboutField.clear();
        aboutField.sendKeys(about);
        return this;
    }

    /**
     * Заполняет поле "Пароль" указанным значением
     *
     * @param password пароль для заполнения
     * @return текущий экземпляр RegistrationPage для цепочки вызовов
     */
    @Step("Заполнить поле 'Пароль': {password}")
    public RegistrationPage fillPassword(String password) {
        WaitUtils.waitForElementVisible(driver, passwordField, 10);
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }

    /**
     * Заполняет поле подтверждения пароля указанным значением
     *
     * @param password пароль для подтверждения
     * @return текущий экземпляр RegistrationPage для цепочки вызовов
     */
    @Step("Подтвердить пароль: {password}")
    public RegistrationPage fillConfirmPassword(String password) {
        WaitUtils.waitForElementVisible(driver, confirmPasswordField, 10);
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(password);
        return this;
    }

    /**
     * Нажимает кнопку отправки формы
     *
     * @return текущий экземпляр RegistrationPage для цепочки вызовов
     */
    @Step("Нажать кнопку отправки формы")
    public RegistrationPage clickSubmit() {
        WaitUtils.waitForElementClickable(driver, submitBtn, 10);
        submitBtn.click();
        return this;
    }

    /**
     * Проверяет отображение сообщения об успешной регистрации
     *
     * @return true если сообщение отображается, false в противном случае
     */
    @Step("Проверить отображение сообщения об успехе")
    public boolean isSuccessDisplayed() {
        try {
            WaitUtils.waitForElementVisible(driver, successText, 5);
            return successText.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Заполняет все персональные данные пользователя
     *
     * @param name имя пользователя
     * @param lastName фамилия пользователя
     * @param phone телефон пользователя
     * @param email email пользователя
     * @param about информация о пользователе
     * @return текущий экземпляр RegistrationPage для цепочки вызовов
     */
    @Step("Заполнить все персональные данные: имя={name}, фамилия={lastName}, телефон={phone}, email={email}, о себе={about}")
    public RegistrationPage fillPersonalData(String name, String lastName, String phone, String email, String about) {
        return fillName(name)
                .fillLastName(lastName)
                .fillPhone(phone)
                .fillEmail(email)
                .fillAbout(about);
    }

    /**
     * Выполняет отправку формы регистрации
     *
     * @return текущий экземпляр RegistrationPage для цепочки вызовов
     */
    @Step("Выполнить отправку формы")
    public RegistrationPage submit() {
        return clickSubmit();
    }

    /**
     * Проверяет выбрано ли указанное семейное положение
     *
     * @param status семейное положение для проверки
     * @return true если статус выбран, false в противном случае
     * @throws IllegalArgumentException если передан неизвестный статус
     */
    @Step("Проверить выбрано ли семейное положение: {status}")
    public boolean isMaritalStatusSelected(String status) {
        WebElement radio = switch (status.toLowerCase()) {
            case "single" -> singleRadio;
            case "married" -> marriedRadio;
            case "divorced" -> divorcedRadio;
            default -> throw new IllegalArgumentException("Неизвестный статус: " + status);
        };

        WaitUtils.waitForElementVisible(driver, radio, 5);
        return radio.isSelected();
    }

    /**
     * Проверяет выбрано ли указанное хобби
     *
     * @param hobby хобби для проверки
     * @return true если хобби выбрано, false в противном случае
     * @throws IllegalArgumentException если передан неизвестный тип хобби
     */
    @Step("Проверить выбрано ли хобби: {hobby}")
    public boolean isHobbySelected(String hobby) {
        WebElement checkbox = switch (hobby.toLowerCase()) {
            case "dance" -> danceCheckbox;
            case "reading" -> readingCheckbox;
            case "cricket" -> cricketCheckbox;
            default -> throw new IllegalArgumentException("Неизвестное хобби: " + hobby);
        };

        WaitUtils.waitForElementVisible(driver, checkbox, 5);
        return checkbox.isSelected();
    }

    /**
     * Решает все алгоритмические задачи с использованием TaskSolver
     *
     * @return строка с результатами выполнения алгоритмов
     */
    @Step("Решить все алгоритмические задачи")
    public static String solveAllTasks() {
        List<String> permutations = TaskSolver.permutations(new int[]{2025, 3, 11});
        String permutationsStr = String.join("; ", permutations);

        int lisLength = TaskSolver.lis(new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6, 12, 2, 7, 11});

        int lcsLength = TaskSolver.lcs("ABCDGH", "AEDFHR");

        return String.format(
                "Algorithm Results:\nPermutations: %s\nLIS length: %d\nLCS length: %d",
                permutationsStr, lisLength, lcsLength
        );
    }
}