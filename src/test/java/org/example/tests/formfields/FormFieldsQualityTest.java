package org.example.tests.formfields;

import io.qameta.allure.*;
import org.example.pages.FormFieldsPage;
import org.example.tests.BaseTest;
import org.example.utils.ParameterProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Epic("Автоматизация веб-форм")
@Feature("Quality тесты формы")
public class FormFieldsQualityTest extends BaseTest {

    @Test
    @Story("Проверка списка инструментов автоматизации")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет корректность отображения списка инструментов")
    public void testAutomationToolsList() {
        driver.get(ParameterProvider.get("form.fields.url"));
        FormFieldsPage formPage = new FormFieldsPage(driver);

        var tools = formPage.getAutomationTools();

        assertFalse(tools.isEmpty(), "Список инструментов не должен быть пустым");
        assertTrue(tools.size() >= 3, "Должно быть как минимум 3 инструмента автоматизации");
    }

    @Test
    @Story("Проверка работы формы с различными данными")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет отправку формы с полным набором данных")
    public void testFormSubmissionWithCompleteData() {
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
                "Форма должна успешно отправляться с полными корректными данными");
    }

    @Test
    @Story("Проверка работы формы с минимальными данными")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет отправку формы только с обязательными полями")
    public void testFormSubmissionWithMinimalData() {
        driver.get(ParameterProvider.get("form.fields.url"));
        FormFieldsPage formPage = new FormFieldsPage(driver);

        formPage.fillName(ParameterProvider.get("test.name.minimal"))
                .fillPassword(ParameterProvider.get("test.password.minimal"))
                .selectYellowColor()
                .clickSubmit();

        assertTrue(formPage.isAlertPresentWithText(ParameterProvider.get("alert.success")),
                "Форма должна отправляться с минимальными обязательными данными");
    }
}