package org.example.tests.registration;

import io.qameta.allure.*;
import org.example.pages.RegistrationPage;
import org.example.tests.BaseTest;
import org.example.utils.ParameterProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;

@Epic("Регистрация пользователя")
@Feature("Quality тесты регистрации")
public class RegistrationQualityTest extends BaseTest {

    @Test
    @Story("Проверка валидации полей формы")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет обязательные поля формы регистрации")
    public void testFormValidation() {
        driver.get(ParameterProvider.get("registration.url"));
        RegistrationPage registrationPage = new RegistrationPage(driver);

        registrationPage.clickSubmit();

        assertFalse(registrationPage.isSuccessDisplayed(),
                "Форма не должна быть отправлена без заполнения обязательных полей");
    }
}