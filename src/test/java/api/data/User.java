package api.data;

import java.math.BigDecimal;

public class User {
    private int age;
    private String firstName;
    private int id;
    private BigDecimal money;
    private String secondName;
    private UserSex sex;

    public User(int age, String firstName, int id, BigDecimal money, String secondName, UserSex sex) {
        this.age = age;
        this.firstName = firstName;
        this.id = id;
        this.money = money;
        this.secondName = secondName;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", firstName='" + firstName + '\'' +
                ", id=" + id +
                ", money=" + money +
                ", secondName='" + secondName + '\'' +
                ", sex=" + sex +
                '}';
    }
}
