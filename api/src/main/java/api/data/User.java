package api.data;

import java.math.BigDecimal;

public class User {
    private final int age;
    private final String firstName;
    private final int id;
    private final BigDecimal money;
    private final String secondName;
    private final UserSex sex;

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
