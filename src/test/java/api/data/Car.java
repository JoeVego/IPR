package api.data;

import java.math.BigDecimal;

public class Car {

    private String engineType;
    private int id;
    private String mark;
    private String model;
    private BigDecimal price;

    public Car(String engineType, int id, String mark, String model, BigDecimal price) {
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.engineType = engineType;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getMark() {
        return mark;
    }

    public String getModel() {
        return model;
    }

    public String getEngineType() {
        return engineType;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
