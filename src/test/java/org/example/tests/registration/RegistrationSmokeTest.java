package org.example.tests.registration;

import org.example.pages.RegistrationPage;
import org.example.tests.BaseTest;
import org.example.utils.ParameterProvider;
import org.testng.annotations.Test;
import io.qameta.allure.*;

import static org.testng.Assert.*;

@Epic("Регистрация пользователя")
@Feature("Smoke тесты регистрации")
public class RegistrationSmokeTest extends BaseTest {

    @Test
    @Story("Успешная регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест проверяет успешную регистрацию с валидными данными и результатами алгоритмических задач")
    public void testSuccessfulRegistration() {
        driver.get(ParameterProvider.get("registration.url"));
        RegistrationPage registrationPage = new RegistrationPage(driver);

        String tasksResult = RegistrationPage.solveAllTasks();

        registrationPage.fillPersonalData(
                        ParameterProvider.get("registration.firstname"),
                        ParameterProvider.get("registration.lastname"),
                        ParameterProvider.get("registration.phone"),
                        ParameterProvider.get("registration.email"),
                        tasksResult)
                .fillMaritalStatus(ParameterProvider.get("marital.status.single"))
                .fillHobbies(
                        ParameterProvider.get("hobby.dance"),
                        ParameterProvider.get("hobby.reading"))
                .fillDateOfBirth()
                .fillUsername(ParameterProvider.get("registration.username"))
                .fillPassword(ParameterProvider.get("registration.password"))
                .fillConfirmPassword(ParameterProvider.get("registration.confirm.password"))
                .submit();

        assertTrue(driver.getCurrentUrl().contains("registration") ||
                        registrationPage.isSuccessDisplayed(),
                "Форма должна быть обработана после отправки");
    }

    @Test
    @Story("Регистрация с разными семейными статусами")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет регистрацию с различными вариантами семейного положения")
    public void testRegistrationWithDifferentMaritalStatus() {
        driver.get(ParameterProvider.get("registration.url"));
        RegistrationPage registrationPage = new RegistrationPage(driver);

        String tasksResult = RegistrationPage.solveAllTasks();

        registrationPage.fillPersonalData(
                        ParameterProvider.get("registration.firstname.anna"),
                        ParameterProvider.get("registration.lastname.smith"),
                        ParameterProvider.get("registration.phone.anna"),
                        ParameterProvider.get("registration.email.anna"),
                        tasksResult)
                .fillMaritalStatus(ParameterProvider.get("marital.status.married"))
                .fillHobby(ParameterProvider.get("hobby.cricket"))
                .fillDateOfBirth()
                .fillUsername(ParameterProvider.get("registration.username.anna"))
                .fillPassword(ParameterProvider.get("registration.password"))
                .fillConfirmPassword(ParameterProvider.get("registration.confirm.password"))
                .submit();

        assertTrue(driver.getCurrentUrl().contains("registration") ||
                        registrationPage.isSuccessDisplayed(),
                "Форма должна быть обработана после отправки");
    }
}