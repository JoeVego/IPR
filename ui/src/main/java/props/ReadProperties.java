package props;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 Класс для чтения записанных в файл «conf.properties» значений
 */
public class ReadProperties {
    private static FileInputStream fileInputStream;
    private static Properties PROPERTIES;

    static {
        try {
            //указание пути до файла с настройками
            fileInputStream = new FileInputStream("src/main/resources/conf.properties");
            //загрузка файла
            PROPERTIES = new Properties();
            PROPERTIES.load(fileInputStream);
        } catch (IOException e) {
//            e.printStackTrace();
            System.exit(3);
        } finally {
            //закрытие файла
            if (fileInputStream != null)
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     *  метод для возврата строки со значением из файла о ключу
     */
    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}
