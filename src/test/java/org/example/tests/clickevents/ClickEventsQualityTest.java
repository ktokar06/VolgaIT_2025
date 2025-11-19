package org.example.tests.clickevents;

import io.qameta.allure.*;
import org.example.pages.ClickEventsPage;
import org.example.tests.BaseTest;
import org.example.utils.ParameterProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Epic("Автоматизация событий клика")
@Feature("Quality тесты кликов")
public class ClickEventsQualityTest extends BaseTest {

    @Test
    @Story("Множественные последовательные клики")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет поведение при множественных кликах")
    public void testMultipleSequentialClicks() {
        driver.get(ParameterProvider.get("click.events.url"));
        ClickEventsPage clickEventsPage = new ClickEventsPage(driver);

        clickEventsPage.clickCat()
                .clickDog()
                .clickPig()
                .clickCow();

        String demoText = clickEventsPage.getDemoText();
        assertTrue(demoText.contains("Moo!"),
                "После всех кликов должен отображаться текст последнего клика");
    }

    @Test
    @Story("Проверка порядка сообщений")
    @Severity(SeverityLevel.MINOR)
    @Description("Тест проверяет, что каждое сообщение соответствует правильной кнопке")
    public void testMessageOrder() {
        driver.get(ParameterProvider.get("click.events.url"));
        ClickEventsPage clickEventsPage = new ClickEventsPage(driver);

        clickEventsPage.clickCat();
        assertEquals(clickEventsPage.getDemoText(), "Meow!",
                "После клика Cat должно быть 'Meow!'");

        clickEventsPage.clickDog();
        assertEquals(clickEventsPage.getDemoText(), "Woof!",
                "После клика Dog должно быть 'Woof!'");

        clickEventsPage.clickPig();
        assertEquals(clickEventsPage.getDemoText(), "Oink!",
                "После клика Pig должно быть 'Oink!'");

        clickEventsPage.clickCow();
        assertEquals(clickEventsPage.getDemoText(), "Moo!",
                "После клика Cow должно быть 'Moo!'");
    }
}