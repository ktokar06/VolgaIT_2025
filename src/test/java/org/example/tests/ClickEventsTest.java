package org.example.tests;

import io.qameta.allure.*;
import org.example.pages.ClickEventsPage;
import org.example.helpers.ParameterProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Epic("Автоматизация событий клика")
@Feature("Клики по кнопкам животных")
public class ClickEventsTest extends BaseTest {

    @Test
    @Story("Клики по кнопкам животных")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест проверяет корректность сообщений после кликов на кнопки различных животных")
    void testAnimalButtonClicks() {
        driver.get(ParameterProvider.get("click.events.url"));
        ClickEventsPage clickEventsPage = new ClickEventsPage(driver);

        clickEventsPage.clickCat();
        assertTrue(clickEventsPage.isDemoTextContains("Meow!"), "Сообщение после клика на Cat должно содержать 'Meow!'");

        clickEventsPage.clickDog();
        assertTrue(clickEventsPage.isDemoTextContains("Woof!"), "Сообщение после клика на Dog должно содержать 'Woof!'");

        clickEventsPage.clickPig();
        assertTrue(clickEventsPage.isDemoTextContains("Oink!"), "Сообщение после клика на Pig должно содержать 'Oink!'");

        clickEventsPage.clickCow();
        assertTrue(clickEventsPage.isDemoTextContains("Moo!"), "Сообщение после клика на Cow должно содержать 'Moo!'");
    }
}