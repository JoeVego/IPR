package props;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 Класс для чтения записанных в файл «conf.properties» значений
 */
public class ReadProperties {
    private static final Properties PROPERTIES = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream("src/main/resources/conf.properties")) {
            PROPERTIES.load(fis);
        } catch (IOException e) {
            System.exit(3);
        }
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}
