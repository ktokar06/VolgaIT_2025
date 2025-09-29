package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Абстрактный базовый класс для всех страниц веб-приложения.
 */
public abstract class BasePage {
    protected WebDriver driver;

    /**
     * Конструктор базового класса страницы.
     * Инициализирует WebDriver и элементы PageFactory.
     *
     * @param driver экземпляр WebDriver для управления браузером
     * @throws IllegalArgumentException если передан null WebDriver
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}