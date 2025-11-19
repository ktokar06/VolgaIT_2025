package org.example.tests.formfields;

import io.qameta.allure.*;
import org.example.pages.FormFieldsPage;
import org.example.tests.BaseTest;
import org.example.utils.ParameterProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Epic("Автоматизация веб-форм")
@Feature("Smoke тесты формы")
public class FormFieldsSmokeTest extends BaseTest {

    @Test
    @Story("Базовое заполнение формы")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Smoke тест проверяет минимальное заполнение формы")
    public void testBasicFormFill() {
        driver.get(ParameterProvider.get("form.fields.url"));
        FormFieldsPage formPage = new FormFieldsPage(driver);

        formPage.fillName(ParameterProvider.get("test.name"))
                .fillPassword(ParameterProvider.get("test.password"))
                .selectYellowColor()
                .clickSubmit();

        assertTrue(formPage.isAlertPresentWithText(ParameterProvider.get("alert.success")),
                "Алерт с текстом 'Message received!' не появился");
    }

    @Test
    @Story("Заполнение формы с выбором напитков")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Smoke тест проверяет выбор напитков в форме")
    public void testFormWithDrinks() {
        driver.get(ParameterProvider.get("form.fields.url"));
        FormFieldsPage formPage = new FormFieldsPage(driver);

        formPage.fillName(ParameterProvider.get("test.name"))
                .fillPassword(ParameterProvider.get("test.password"))
                .selectDrinks(ParameterProvider.get("drink.milk"), ParameterProvider.get("drink.coffee"))
                .selectYellowColor()
                .clickSubmit();

        assertTrue(formPage.isAlertPresentWithText(ParameterProvider.get("alert.success")),
                "Алерт с текстом 'Message received!' не появился");
    }
}