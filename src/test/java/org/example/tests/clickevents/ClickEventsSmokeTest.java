package org.example.tests.clickevents;

import io.qameta.allure.*;
import org.example.pages.ClickEventsPage;
import org.example.tests.BaseTest;
import org.example.utils.ParameterProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Epic("Автоматизация событий клика")
@Feature("Smoke тесты кликов")
public class ClickEventsSmokeTest extends BaseTest {

    @Test
    @Story("Базовые клики по кнопкам")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Smoke тест проверяет базовые клики по кнопкам животных")
    public void testBasicButtonClicks() {
        driver.get(ParameterProvider.get("click.events.url"));
        ClickEventsPage clickEventsPage = new ClickEventsPage(driver);

        clickEventsPage.clickCat();
        assertTrue(clickEventsPage.isDemoTextContains("Meow!"),
                "Сообщение после клика на Cat должно содержать 'Meow!'");

        clickEventsPage.clickDog();
        assertTrue(clickEventsPage.isDemoTextContains("Woof!"),
                "Сообщение после клика на Dog должно содержать 'Woof!'");
    }
}