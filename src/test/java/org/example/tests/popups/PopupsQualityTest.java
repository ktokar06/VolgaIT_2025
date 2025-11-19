package org.example.tests.popups;

import io.qameta.allure.*;
import org.example.pages.PopupsPage;
import org.example.tests.BaseTest;
import org.example.utils.ParameterProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Epic("Автоматизация модальных окон")
@Feature("Quality тесты попапов")
public class PopupsQualityTest extends BaseTest {

    @Test
    @Story("Проверка текстов подтверждения")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет различные варианты подтверждения")
    public void testConfirmTextVariations() {
        driver.get(ParameterProvider.get("popups.url"));
        PopupsPage popupsPage = new PopupsPage(driver);

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
    }

    @Test
    @Story("Проверка поведения тултипа")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет отображение и скрытие тултипа")
    public void testTooltipBehavior() {
        driver.get(ParameterProvider.get("popups.url"));
        PopupsPage popupsPage = new PopupsPage(driver);

        assertFalse(popupsPage.isTooltipVisible(), "Тултип не должен быть виден изначально");

        popupsPage.clickTooltip();
        assertTrue(popupsPage.isTooltipVisible(), "Тултип должен быть виден после клика");

        assertEquals(popupsPage.getTooltipText(), ParameterProvider.get("popup.text.cool"),
                "Текст тултипа должен соответствовать ожидаемому");
    }
}