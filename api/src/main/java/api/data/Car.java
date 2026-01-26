package api.data;

import java.math.BigDecimal;

public class Car {

    private final String engineType;
    private final int id;
    private final String mark;
    private final String model;
    private final BigDecimal price;

    public Car(String engineType, int id, String mark, String model, BigDecimal price) {
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.engineType = engineType;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "engineType='" + engineType + '\'' +
                ", id=" + id +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                '}';
    }
}
