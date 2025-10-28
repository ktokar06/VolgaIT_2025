package org.example.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Провайдер параметров для чтения конфигурационных файлов.
 */
public final class ParameterProvider {
    private static final String CONFIG_PATH = "configuration/config.properties";
    private static ParameterProvider instance;
    private final Map<String, String> parameters;

    /**
     * Приватный конструктор для реализации паттерна Singleton.
     * Загружает параметры из конфигурационного файла.
     *
     * @throws RuntimeException если произошла ошибка при загрузке конфигурационного файла
     */
    private ParameterProvider() {
        try {
            parameters = new HashMap<>();
            Properties prop = new Properties();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(CONFIG_PATH);
            prop.load(inputStream);
            prop.stringPropertyNames()
                    .forEach(key -> parameters.put(key, prop.getProperty(key)));
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при загрузке конфигурации", e);
        }
    }

    /**
     * Возвращает значение параметра по указанному ключу.
     * При первом вызове метода инициализирует экземпляр класса и загружает параметры.
     *
     * @param key ключ параметра для поиска
     * @return значение параметра или null, если параметр с указанным ключом не найден
     */
    public static String get(String key) {
        if (instance == null) {
            instance = new ParameterProvider();
        }
        return instance.parameters.get(key);
    }
}