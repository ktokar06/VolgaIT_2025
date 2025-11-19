package org.example.tests.registration;

import io.qameta.allure.*;
import org.example.pages.RegistrationPage;
import org.example.tests.BaseTest;
import org.example.utils.ParameterProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Epic("Регистрация пользователя")
@Feature("Smoke тесты регистрации")
public class RegistrationSmokeTest extends BaseTest {

    @Test
    @Story("Успешная регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест проверяет успешную регистрацию с валидными данными")
    public void testSuccessfulRegistration() {
        driver.get(ParameterProvider.get("registration.url"));

        RegistrationPage registrationPage = new RegistrationPage(driver);

        registrationPage.fillPersonalData("Test", "User", "79998887766", "test@mail.com", "About Myself")
                .fillMaritalStatus("single")
                .fillHobbies("dance", "reading")
                .fillDateOfBirth("1", "15", "1990")
                .fillUsername("testuser")
                .fillPassword("password123")
                .fillConfirmPassword("password123")
                .submit();

        assertTrue(registrationPage.isSuccessDisplayed(),
                "Сообщение об успешной регистрации должно отображаться");
        assertFalse(registrationPage.getSuccessText().isEmpty(),
                "Текст успешной регистрации не должен быть пустым");
    }

    @Test
    @Story("Регистрация с разными семейными статусами")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет регистрацию с различными вариантами семейного положения")
    public void testRegistrationWithDifferentMaritalStatus() {
        driver.get(ParameterProvider.get("registration.url"));

        RegistrationPage registrationPage = new RegistrationPage(driver);

        registrationPage.fillPersonalData("Anna", "Smith", "79998887755", "anna@mail.com", "About Anna")
                .fillMaritalStatus("married")
                .fillHobby("cricket")
                .fillDateOfBirth("3", "25", "1985")
                .fillUsername("annasmith")
                .fillPassword("password123")
                .fillConfirmPassword("password123")
                .submit();

        assertTrue(registrationPage.isSuccessDisplayed(),
                "Сообщение об успешной регистрации должно отображаться");
    }
}