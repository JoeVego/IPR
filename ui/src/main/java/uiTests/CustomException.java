package uiTests;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.openqa.selenium.ElementNotInteractableException;
import org.opentest4j.AssertionFailedError;

public class CustomException implements TestExecutionExceptionHandler {
    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable)
            throws Throwable {
        // Проверка типа исключения
        if (throwable instanceof AssertionFailedError ||
                throwable instanceof NullPointerException ||
                throwable instanceof ElementNotInteractableException)
        {
            // Вывод стека
            throwable.printStackTrace();

            // Генерация нового исключения с нужным сообщением
            throw new AssertionFailedError("Тест неуспешен, драйвер закрыт");
        }
        // Если исключение не относится к перечисленным — пробросить дальше
        throw throwable;
    }
}

