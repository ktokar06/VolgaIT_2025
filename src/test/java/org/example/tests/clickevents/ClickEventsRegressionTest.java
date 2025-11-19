package org.example.tests.clickevents;

import io.qameta.allure.*;
import org.example.pages.ClickEventsPage;
import org.example.tests.BaseTest;
import org.example.utils.ParameterProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Epic("Автоматизация событий клика")
@Feature("Regression тесты кликов")
public class ClickEventsRegressionTest extends BaseTest {

    @Test
    @Story("Полный цикл кликов по всем кнопкам")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Regression тест проверяет все кнопки животных")
    public void testAllAnimalButtons() {
        driver.get(ParameterProvider.get("click.events.url"));
        ClickEventsPage clickEventsPage = new ClickEventsPage(driver);

        clickEventsPage.clickCat();
        assertTrue(clickEventsPage.isDemoTextContains("Meow!"),
                "Сообщение после клика на Cat должно содержать 'Meow!'");

        clickEventsPage.clickDog();
        assertTrue(clickEventsPage.isDemoTextContains("Woof!"),
                "Сообщение после клика на Dog должно содержать 'Woof!'");

        clickEventsPage.clickPig();
        assertTrue(clickEventsPage.isDemoTextContains("Oink!"),
                "Сообщение после клика на Pig должно содержать 'Oink!'");

        clickEventsPage.clickCow();
        assertTrue(clickEventsPage.isDemoTextContains("Moo!"),
                "Сообщение после клика на Cow должно содержать 'Moo!'");
    }

    @Test
    @Story("Повторные клики на одну кнопку")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет поведение при повторных кликах на одну кнопку")
    public void testRepeatedClicksOnSameButton() {
        driver.get(ParameterProvider.get("click.events.url"));
        ClickEventsPage clickEventsPage = new ClickEventsPage(driver);

        clickEventsPage.clickCat();
        assertTrue(clickEventsPage.isDemoTextContains("Meow!"),
                "Сообщение после первого клика на Cat должно содержать 'Meow!'");

        clickEventsPage.clickCat();
        assertTrue(clickEventsPage.isDemoTextContains("Meow!"),
                "Сообщение после второго клика на Cat должно содержать 'Meow!'");
    }
}