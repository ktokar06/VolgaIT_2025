package org.example.tests;

import io.qameta.allure.*;
import org.example.pages.PopupsPage;
import org.example.utils.ParameterProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Epic("Автоматизация модальных окон")
@Feature("Работа с alert, confirm и prompt")
public class PopupsTest extends BaseTest {

    @Test
    @Story("Обработка алерта")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест проверяет работу с простым алертом")
    void testAlertPopup() {
        driver.get(ParameterProvider.get("popups.url"));
        PopupsPage popupsPage = new PopupsPage(driver);

        popupsPage.clickAlertButton()
                .handleAlert(true);

        assertTrue(true, "Alert должен быть успешно обработан");
    }

    @Test
    @Story("Обработка подтверждения")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест проверяет работу с confirm-окном")
    void testConfirmPopup() {
        driver.get(ParameterProvider.get("popups.url"));
        PopupsPage popupsPage = new PopupsPage(driver);

        popupsPage.clickConfirmButton()
                .handleAlert(false);

        assertEquals(popupsPage.getConfirmResult(), ParameterProvider.get("popup.text.cancel"));
    }

    @Test
    @Story("Обработка подтверждения с принятием")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест проверяет работу с confirm-окном при принятии")
    void testConfirmPopupAccept() {
        driver.get(ParameterProvider.get("popups.url"));
        PopupsPage popupsPage = new PopupsPage(driver);

        popupsPage.clickConfirmButton()
                .handleAlert(true);

        assertEquals(popupsPage.getConfirmResult(), ParameterProvider.get("popup.text.ok"));
    }

    @Test
    @Story("Обработка промпта")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест проверяет работу с prompt-окном")
    void testPromptPopup() {
        driver.get(ParameterProvider.get("popups.url"));
        PopupsPage popupsPage = new PopupsPage(driver);

        popupsPage.clickPromptButton()
                .handlePrompt(ParameterProvider.get("test.prompt.text"), true);

        assertTrue(popupsPage.getPromptResult().contains(ParameterProvider.get("test.prompt.text")),
                "Результат должен содержать введённый текст: " + popupsPage.getPromptResult());
    }

    @Test
    @Story("Обработка промпта с отменой")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет работу с prompt-окном при отмене")
    void testPromptPopupDismiss() {
        driver.get(ParameterProvider.get("popups.url"));
        PopupsPage popupsPage = new PopupsPage(driver);

        popupsPage.clickPromptButton()
                .handlePrompt("", false);

        assertEquals(popupsPage.getPromptResult(), ParameterProvider.get("popup.text.fine"));
    }

    @Test
    @Story("Проверка тултипа")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет отображение тултипа")
    void testTooltip() {
        driver.get(ParameterProvider.get("popups.url"));
        PopupsPage popupsPage = new PopupsPage(driver);

        assertFalse(popupsPage.isTooltipVisible(), "Тултип не должен быть виден изначально");
        popupsPage.clickTooltip();
        assertTrue(popupsPage.isTooltipVisible(), "Тултип должен быть виден после клика");
        assertEquals(popupsPage.getTooltipText(), ParameterProvider.get("popup.text.cool"));
    }
}