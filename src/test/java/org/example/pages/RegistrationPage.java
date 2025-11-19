package org.example.pages;

import org.example.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import io.qameta.allure.Step;

public class RegistrationPage extends BasePage {

    @FindBy(name = "name")
    private WebElement firstNameField;

    @FindBy(xpath = "//label[contains(text(),'Last Name')]/following-sibling::input")
    private WebElement lastNameField;

    // Локаторы для Marital Status
    @FindBy(xpath = "//input[@type='radio' and following-sibling::text()[contains(., 'Single')]]")
    private WebElement singleRadio;

    @FindBy(xpath = "//input[@type='radio' and following-sibling::text()[contains(., 'Married')]]")
    private WebElement marriedRadio;

    @FindBy(xpath = "//input[@type='radio' and following-sibling::text()[contains(., 'Divorced')]]")
    private WebElement divorcedRadio;

    // Локаторы для Hobby
    @FindBy(xpath = "//input[@type='checkbox' and following-sibling::text()[contains(., 'Dance')]]")
    private WebElement danceCheckbox;

    @FindBy(xpath = "//input[@type='checkbox' and following-sibling::text()[contains(., 'Reading')]]")
    private WebElement readingCheckbox;

    @FindBy(xpath = "//input[@type='checkbox' and following-sibling::text()[contains(., 'Cricket')]]")
    private WebElement cricketCheckbox;

    // Локаторы для Date of Birth
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

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Заполнить поле 'Имя': {name}")
    public RegistrationPage fillName(String name) {
        WaitUtils.waitForElementVisible(driver, firstNameField, 10);
        firstNameField.clear();
        firstNameField.sendKeys(name);
        return this;
    }

    @Step("Заполнить поле 'Фамилия': {lastName}")
    public RegistrationPage fillLastName(String lastName) {
        WaitUtils.waitForElementVisible(driver, lastNameField, 10);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
        return this;
    }

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

    @Step("Выбрать несколько хобби")
    public RegistrationPage fillHobbies(String... hobbies) {
        for (String hobby : hobbies) {
            fillHobby(hobby);
        }
        return this;
    }

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

    @Step("Заполнить дату рождения: месяц={month}, день={day}, год={year}")
    public RegistrationPage fillDateOfBirth(String month, String day, String year) {

        WaitUtils.waitForElementVisible(driver, monthSelect, 10);
        Select monthDropdown = new Select(monthSelect);
        monthDropdown.selectByVisibleText(month);

        WaitUtils.waitForElementVisible(driver, daySelect, 10);
        Select dayDropdown = new Select(daySelect);
        dayDropdown.selectByVisibleText(day);


        WaitUtils.waitForElementVisible(driver, yearSelect, 10);
        Select yearDropdown = new Select(yearSelect);
        yearDropdown.selectByVisibleText(year);

        return this;
    }

    @Step("Заполнить поле 'Телефон': {phone}")
    public RegistrationPage fillPhone(String phone) {
        WaitUtils.waitForElementVisible(driver, phoneField, 10);
        phoneField.clear();
        phoneField.sendKeys(phone);
        return this;
    }

    @Step("Заполнить поле 'Имя пользователя': {username}")
    public RegistrationPage fillUsername(String username) {
        WaitUtils.waitForElementVisible(driver, usernameField, 10);
        usernameField.clear();
        usernameField.sendKeys(username);
        return this;
    }

    @Step("Заполнить поле 'Email': {email}")
    public RegistrationPage fillEmail(String email) {
        WaitUtils.waitForElementVisible(driver, emailField, 10);
        emailField.clear();
        emailField.sendKeys(email);
        return this;
    }

    @Step("Заполнить поле 'О себе': {about}")
    public RegistrationPage fillAbout(String about) {
        WaitUtils.waitForElementVisible(driver, aboutField, 10);
        aboutField.clear();
        aboutField.sendKeys(about);
        return this;
    }

    @Step("Заполнить поле 'Пароль': {password}")
    public RegistrationPage fillPassword(String password) {
        WaitUtils.waitForElementVisible(driver, passwordField, 10);
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }

    @Step("Подтвердить пароль: {password}")
    public RegistrationPage fillConfirmPassword(String password) {
        WaitUtils.waitForElementVisible(driver, confirmPasswordField, 10);
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(password);
        return this;
    }

    @Step("Нажать кнопку отправки формы")
    public RegistrationPage clickSubmit() {
        WaitUtils.waitForElementClickable(driver, submitBtn, 10);
        submitBtn.click();
        return this;
    }

    @Step("Получить текст успешной регистрации")
    public String getSuccessText() {
        WaitUtils.waitForElementVisible(driver, successText, 10);
        return successText.getText();
    }

    @Step("Проверить отображение сообщения об успехе")
    public boolean isSuccessDisplayed() {
        try {
            WaitUtils.waitForElementVisible(driver, successText, 5);
            return successText.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Заполнить все персональные данные: имя={name}, фамилия={lastName}, телефон={phone}, email={email}, о себе={about}")
    public RegistrationPage fillPersonalData(String name, String lastName, String phone, String email, String about) {
        return fillName(name)
                .fillLastName(lastName)
                .fillPhone(phone)
                .fillEmail(email)
                .fillAbout(about);
    }

    @Step("Заполнить учетные данные: имя пользователя={username}, пароль={password}")
    public RegistrationPage fillCredentials(String username, String password) {
        return fillUsername(username)
                .fillPassword(password)
                .fillConfirmPassword(password);
    }

    @Step("Выполнить отправку формы")
    public RegistrationPage submit() {
        return clickSubmit();
    }

    // Дополнительные методы для проверки состояний

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

    @Step("Получить выбранный месяц")
    public String getSelectedMonth() {
        WaitUtils.waitForElementVisible(driver, monthSelect, 5);
        Select monthDropdown = new Select(monthSelect);
        return monthDropdown.getFirstSelectedOption().getText();
    }

    @Step("Получить выбранный день")
    public String getSelectedDay() {
        WaitUtils.waitForElementVisible(driver, daySelect, 5);
        Select dayDropdown = new Select(daySelect);
        return dayDropdown.getFirstSelectedOption().getText();
    }

    @Step("Получить выбранный год")
    public String getSelectedYear() {
        WaitUtils.waitForElementVisible(driver, yearSelect, 5);
        Select yearDropdown = new Select(yearSelect);
        return yearDropdown.getFirstSelectedOption().getText();
    }
}