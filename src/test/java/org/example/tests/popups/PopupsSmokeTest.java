package org.example.tests.popups;

import io.qameta.allure.*;
import org.example.pages.PopupsPage;
import org.example.tests.BaseTest;
import org.example.utils.ParameterProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Epic("Автоматизация модальных окон")
@Feature("Smoke тесты попапов")
public class PopupsSmokeTest extends BaseTest {

    @Test
    @Story("Базовая обработка алерта")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Smoke тест проверяет работу с простым алертом")
    public void testBasicAlert() {
        driver.get(ParameterProvider.get("popups.url"));
        PopupsPage popupsPage = new PopupsPage(driver);

        popupsPage.clickAlertButton()
                .handleAlert(true);

        assertTrue(true, "Alert должен быть успешно обработан");
    }

    @Test
    @Story("Базовая обработка подтверждения")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Smoke тест проверяет работу с confirm-окном")
    public void testBasicConfirm() {
        driver.get(ParameterProvider.get("popups.url"));
        PopupsPage popupsPage = new PopupsPage(driver);

        popupsPage.clickConfirmButton()
                .handleAlert(true);

        assertTrue(popupsPage.getConfirmResult().contains("OK"),
                "Текст результата должен содержать информацию о принятии");
    }
}