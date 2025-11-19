package org.example.tests.popups;

import io.qameta.allure.*;
import org.example.pages.PopupsPage;
import org.example.tests.BaseTest;
import org.example.utils.ParameterProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Epic("Автоматизация модальных окон")
@Feature("Regression тесты попапов")
public class PopupsRegressionTest extends BaseTest {

    @Test
    @Story("Полный цикл тестирования всех типов попапов")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Regression тест проверяет все типы модальных окон")
    public void testAllPopupTypes() {
        driver.get(ParameterProvider.get("popups.url"));
        PopupsPage popupsPage = new PopupsPage(driver);

        popupsPage.clickAlertButton()
                .handleAlert(true);

        popupsPage.clickConfirmButton()
                .handleAlert(true);
        assertEquals(popupsPage.getConfirmResult(), ParameterProvider.get("popup.text.ok"),
                "Текст результата должен соответствовать принятию");

        driver.get(ParameterProvider.get("popups.url"));
        popupsPage = new PopupsPage(driver);
        popupsPage.clickConfirmButton()
                .handleAlert(false);
        assertEquals(popupsPage.getConfirmResult(), ParameterProvider.get("popup.text.cancel"),
                "Текст результата должен соответствовать отмене");

        driver.get(ParameterProvider.get("popups.url"));
        popupsPage = new PopupsPage(driver);
        popupsPage.clickPromptButton()
                .handlePrompt(ParameterProvider.get("test.prompt.text"), true);
        assertTrue(popupsPage.isPromptResultContains(ParameterProvider.get("test.prompt.text")),
                "Результат должен содержать введённый текст");

        driver.get(ParameterProvider.get("popups.url"));
        popupsPage = new PopupsPage(driver);
        popupsPage.clickPromptButton()
                .handlePrompt("", false);
        assertEquals(popupsPage.getPromptResult(), ParameterProvider.get("popup.text.fine"),
                "Текст результата должен соответствовать отмене ввода");

        driver.get(ParameterProvider.get("popups.url"));
        popupsPage = new PopupsPage(driver);
        assertFalse(popupsPage.isTooltipVisible(), "Тултип не должен быть виден изначально");
    }

    @Test
    @Story("Проверка граничных значений в промпте")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет различные варианты ввода в промпт")
    public void testPromptEdgeCases() {
        driver.get(ParameterProvider.get("popups.url"));
        PopupsPage popupsPage = new PopupsPage(driver);

        popupsPage.clickPromptButton()
                .handlePrompt("", true);
        assertTrue(popupsPage.getPromptResult().contains(""),
                "Результат должен обрабатывать пустой ввод");

        driver.get(ParameterProvider.get("popups.url"));
        popupsPage = new PopupsPage(driver);
        String longText = "A".repeat(100);
        popupsPage.clickPromptButton()
                .handlePrompt(longText, true);
        assertTrue(popupsPage.isPromptResultContains("A"),
                "Результат должен содержать часть длинного текста");
    }

    @Test
    @Story("Проверка поведения тултипа")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет отображение и текст тултипа")
    public void testTooltipBehavior() {
        driver.get(ParameterProvider.get("popups.url"));
        PopupsPage popupsPage = new PopupsPage(driver);

        assertFalse(popupsPage.isTooltipVisible(), "Тултип не должен быть виден изначально");

        popupsPage.clickTooltip();

        String tooltipText = popupsPage.getTooltipText();
        assertNotNull(tooltipText, "Текст тултипа не должен быть null");
        assertFalse(tooltipText.isEmpty(), "Текст тултипа не должен быть пустым");
    }
}