Этот проект содержит автоматические тесты для проверки API с использованием JUnit 5, RestAssured и Allure для отчётности.

Требования:
    Java 11 или выше
    Maven 3.6 или выше
    Интернет подключение (для скачивания зависимостей и выполнения тестов)

Структура проекта:

    - api/src/main/java/ApiTest.java  — файл запуска API автотестов
    - ui/src/main/java/uiTests/PerfomanceLabTests.java — файл запуска UI автотестов
    - pom.xml — файл конфигурации Maven с зависимостями

Для запуска автотестов:
1. Клонируйте репозиторий или скопируйте проект
    git clone git@github.com:JoeVego/IPR.git
2. Установите зависимости
3. Запустите автотесты: через мавен - mvn test или через файлы java
4. Для сборки аллюр отчета введите в терминале команду: allure serve

Пример аллюр-отчета.
![Text](allure_main.JPG)
![Text](allire_api.JPG)
![Text](allire_ui_failed.JPG)