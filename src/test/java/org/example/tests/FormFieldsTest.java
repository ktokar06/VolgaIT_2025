package org.example.tests;

import io.qameta.allure.*;
import org.example.pages.FormFieldsPage;
import org.example.helpers.ParameterProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Epic("Автоматизация веб-форм")
@Feature("Взаимодействие с полями формы")
public class FormFieldsTest extends BaseTest {

    @Test
    @Story("Заполнение всех полей формы и отправка")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест проверяет успешное заполнение формы и появление алерта 'Message received!'")
    public void testFillAndSubmitForm() {
        driver.get(ParameterProvider.get("form.fields.url"));
        FormFieldsPage formPage = new FormFieldsPage(driver);

        formPage.fillName(ParameterProvider.get("test.name"))
                .fillPassword(ParameterProvider.get("test.password"))
                .selectDrinks("Milk", "Coffee")
                .selectYellowColor()
                .selectAutomation("Yes")
                .fillEmail(ParameterProvider.get("test.email"))
                .fillMessageWithToolsInfo()
                .clickSubmit();

        assertTrue(formPage.isAlertPresentWithText("Message received!"),
                "Алерт с текстом 'Message received!' не появился");
    }
}