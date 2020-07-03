package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
    public Person(String firstName, Integer age) {
        setFirstName(firstName);
        setAge(age);
    }

    private StringProperty firstName;
    private IntegerProperty age;

    public String getFirstName() {
        return firstNameProperty().get();
    }
    public void setFirstName(String firstName) {
        firstNameProperty().set(firstName);
    }

    public Integer getAge() { return ageProperty().get(); }
    public void setAge(Integer age) { ageProperty().set(age); }

    public StringProperty firstNameProperty() {
        if (firstName == null) {
            firstName = new SimpleStringProperty(this, "firstName");
        }

        return firstName;
    }

    public IntegerProperty ageProperty() {
        if (age == null) {
            age = new SimpleIntegerProperty(this, "age");
        }
        return age;
    }
}
