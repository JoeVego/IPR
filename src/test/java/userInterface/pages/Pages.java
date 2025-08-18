package userInterface.pages;

/**
 * Создал чтобы подставлять разные объекты классов
 * на место интерфейса
 * в методе по проверке на нулл объекта страницы
 */
public interface Pages {
    /**
     * метод обязательный сделал дефолтным,
     * чтобы в страницах лишнего не писать
     * @return
     */
    public default Pages getPage(){
        return this;
    };
}
