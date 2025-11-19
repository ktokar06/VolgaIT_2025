package org.example.tests.registration;

import io.qameta.allure.*;
import org.example.pages.RegistrationPage;
import org.example.tests.BaseTest;
import org.example.utils.ParameterProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Epic("Регистрация пользователя")
@Feature("Regression тесты регистрации")
public class RegistrationRegressionTest extends BaseTest {

    @Test
    @Story("Проверка состояний элементов формы")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет корректность выбора семейного положения и хобби")
    public void testFormElementsStates() {
        driver.get(ParameterProvider.get("registration.url"));

        RegistrationPage registrationPage = new RegistrationPage(driver);


        registrationPage.fillMaritalStatus("single");
        assertTrue(registrationPage.isMaritalStatusSelected("single"),
                "Семейное положение 'Single' должно быть выбрано");


        registrationPage.fillHobby("dance");
        assertTrue(registrationPage.isHobbySelected("dance"),
                "Хобби 'Dance' должно быть выбрано");


        registrationPage.uncheckHobby("dance");
        assertFalse(registrationPage.isHobbySelected("dance"),
                "Хобби 'Dance' не должно быть выбрано после снятия");
    }

    @Test
    @Story("Регистрация с различными комбинациями хобби и датой рождения")
    @Severity(SeverityLevel.MINOR)
    @Description("Тест проверяет регистрацию с разными комбинациями хобби и датой рождения")
    public void testRegistrationWithDifferentHobbyCombinations() {
        driver.get(ParameterProvider.get("registration.url"));

        RegistrationPage registrationPage = new RegistrationPage(driver);

        registrationPage.fillPersonalData("John", "Doe", "79998887744", "john@mail.com", "About John")
                .fillMaritalStatus("divorced")
                .fillHobbies("dance", "reading", "cricket")
                .fillDateOfBirth("1", "1", "2014") // Используем числовые значения
                .fillUsername("johndoe")
                .fillPassword("password123")
                .fillConfirmPassword("password123")
                .submit();

        assertTrue(registrationPage.isSuccessDisplayed(),
                "Сообщение об успешной регистрации должно отображаться");
    }
}