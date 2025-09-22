package org.example.tests;

import io.qameta.allure.*;
import org.example.pages.FormFieldsPage;
import org.testng.annotations.Test;

import static org.example.config.Config.FORM_FIELDS_URL;
import static org.testng.Assert.assertTrue;

@Epic("Автоматизация веб-форм")
@Feature("Взаимодействие с полями формы")
public class FormFieldsTest extends BaseTest {

    @Test
    @Story("Заполнение всех полей формы и отправка")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест проверяет успешное заполнение формы и появление алерта 'Message received!'")
    public void testFillAndSubmitForm() {
        driver.get(FORM_FIELDS_URL);
        FormFieldsPage formPage = new FormFieldsPage(driver);

        formPage.fillName("Иван Иванов")
                .fillPassword("securePassword123")
                .selectDrinks("Milk", "Coffee")
                .selectYellowColor()
                .selectAutomation("Yes")
                .fillEmail("test@example.com")
                .fillMessageWithToolsInfo()
                .clickSubmit();

        assertTrue(formPage.isAlertPresentWithText("Message received!"),
                "Алерт с текстом 'Message received!' не появился");
    }
}