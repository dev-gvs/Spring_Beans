package kz.gvs.spring;

public class Record {

    int id;
    String firstName;
    String lastName;
    String number;

    public Record() {
        this.id = 0;
        this.firstName = "";
        this.lastName = "";
        this.number = "";
    }

    public Record(String firstName, String lastName, String number) {
        this.id = 0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return String.format("Имя=%s, Фамилия=%s, Номер=%s", firstName, lastName, number);
    }

}
