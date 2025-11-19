package org.example.tests.registration;

import org.example.pages.RegistrationPage;
import org.example.tests.BaseTest;
import org.example.utils.ParameterProvider;
import org.testng.annotations.Test;
import io.qameta.allure.*;

import static org.testng.Assert.*;

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

        String singleStatus = ParameterProvider.get("marital.status.single");
        String marriedStatus = ParameterProvider.get("marital.status.married");
        String danceHobby = ParameterProvider.get("hobby.dance");

        registrationPage.fillMaritalStatus(singleStatus);
        assertTrue(registrationPage.isMaritalStatusSelected(singleStatus),
                "Семейное положение 'Single' должно быть выбрано");

        registrationPage.fillHobby(danceHobby);
        assertTrue(registrationPage.isHobbySelected(danceHobby),
                "Хобби 'Dance' должно быть выбрано");

        registrationPage.uncheckHobby(danceHobby);
        assertFalse(registrationPage.isHobbySelected(danceHobby),
                "Хобби 'Dance' не должно быть выбрано после снятия");

        registrationPage.fillMaritalStatus(marriedStatus);
        assertTrue(registrationPage.isMaritalStatusSelected(marriedStatus),
                "Семейное положение 'Married' должно быть выбрано");
        assertFalse(registrationPage.isMaritalStatusSelected(singleStatus),
                "Семейное положение 'Single' не должно быть выбрано");
    }
}