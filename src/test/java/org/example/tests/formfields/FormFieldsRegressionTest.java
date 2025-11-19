package org.example.tests.formfields;

import io.qameta.allure.*;
import org.example.pages.FormFieldsPage;
import org.example.tests.BaseTest;
import org.example.utils.ParameterProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Epic("Автоматизация веб-форм")
@Feature("Regression тесты формы")
public class FormFieldsRegressionTest extends BaseTest {

    @Test
    @Story("Полное заполнение формы")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Regression тест проверяет полное заполнение всех полей формы")
    public void testCompleteFormFill() {
        driver.get(ParameterProvider.get("form.fields.url"));
        FormFieldsPage formPage = new FormFieldsPage(driver);

        formPage.fillName(ParameterProvider.get("test.name"))
                .fillPassword(ParameterProvider.get("test.password"))
                .selectDrinks(ParameterProvider.get("drink.milk"), ParameterProvider.get("drink.coffee"))
                .selectYellowColor()
                .selectAutomation(ParameterProvider.get("automation.yes"))
                .fillEmail(ParameterProvider.get("test.email"))
                .fillMessageWithToolsInfo()
                .clickSubmit();

        assertTrue(formPage.isAlertPresentWithText(ParameterProvider.get("alert.success")),
                "Алерт с текстом 'Message received!' не появился");
    }

    @Test
    @Story("Множественные отправки формы")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет поведение при множественных отправках формы")
    public void testMultipleSubmissions() {
        driver.get(ParameterProvider.get("form.fields.url"));
        FormFieldsPage formPage = new FormFieldsPage(driver);

        formPage.fillName(ParameterProvider.get("test.name"))
                .fillPassword(ParameterProvider.get("test.password"))
                .selectYellowColor()
                .clickSubmit();

        assertTrue(formPage.isAlertPresentWithText(ParameterProvider.get("alert.success")),
                "Алерт с текстом 'Message received!' не появился при первой отправке");

        driver.get(ParameterProvider.get("form.fields.url"));
        formPage = new FormFieldsPage(driver);

        formPage.fillName(ParameterProvider.get("test.name.second"))
                .fillPassword(ParameterProvider.get("test.password"))
                .selectDrinks(ParameterProvider.get("drink.coffee"))
                .selectYellowColor()
                .clickSubmit();

        assertTrue(formPage.isAlertPresentWithText(ParameterProvider.get("alert.success")),
                "Алерт с текстом 'Message received!' не появился при второй отправке");
    }
}