package org.example.tests;

import io.qameta.allure.*;
import org.example.pages.PopupsPage;
import org.testng.annotations.Test;

import static org.example.config.Config.POPUPS_URL;
import static org.testng.Assert.*;

@Epic("Автоматизация модальных окон")
@Feature("Работа с alert, confirm и prompt")
public class PopupsTest extends BaseTest {

    private static final String CANCEL_TEXT = "Cancel it is!";
    private static final String OK_TEXT = "OK it is!";
    private static final String FINE_TEXT = "Fine, be that way...";
    private static final String COOL_TEXT = "Cool text";
    private static final String TEST_INPUT_TEXT = "Automation Tester";

    @Test
    @Story("Обработка алерта")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест проверяет работу с простым алертом")
    void testAlertPopup() {
        driver.get(POPUPS_URL);
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
        driver.get(POPUPS_URL);
        PopupsPage popupsPage = new PopupsPage(driver);

        popupsPage.clickConfirmButton()
                .handleAlert(false);

        assertEquals(popupsPage.getConfirmResult(), CANCEL_TEXT);
    }

    @Test
    @Story("Обработка подтверждения с принятием")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест проверяет работу с confirm-окном при принятии")
    void testConfirmPopupAccept() {
        driver.get(POPUPS_URL);
        PopupsPage popupsPage = new PopupsPage(driver);

        popupsPage.clickConfirmButton()
                .handleAlert(true);

        assertEquals(popupsPage.getConfirmResult(), OK_TEXT);
    }

    @Test
    @Story("Обработка промпта")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест проверяет работу с prompt-окном")
    void testPromptPopup() {
        driver.get(POPUPS_URL);
        PopupsPage popupsPage = new PopupsPage(driver);

        popupsPage.clickPromptButton()
                .handlePrompt(TEST_INPUT_TEXT, true);

        assertTrue(popupsPage.getPromptResult().contains(TEST_INPUT_TEXT),
                "Результат должен содержать введённый текст: " + popupsPage.getPromptResult());
    }

    @Test
    @Story("Обработка промпта с отменой")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет работу с prompt-окном при отмене")
    void testPromptPopupDismiss() {
        driver.get(POPUPS_URL);
        PopupsPage popupsPage = new PopupsPage(driver);

        popupsPage.clickPromptButton()
                .handlePrompt("", false);

        assertEquals(popupsPage.getPromptResult(), FINE_TEXT);
    }

    @Test
    @Story("Проверка тултипа")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет отображение тултипа")
    void testTooltip() {
        driver.get(POPUPS_URL);
        PopupsPage popupsPage = new PopupsPage(driver);

        assertFalse(popupsPage.isTooltipVisible(), "Тултип не должен быть виден изначально");
        popupsPage.clickTooltip();

        assertTrue(popupsPage.isTooltipVisible(), "Тултип должен быть виден после клика");
        assertEquals(popupsPage.getTooltipText(), COOL_TEXT);
    }
}