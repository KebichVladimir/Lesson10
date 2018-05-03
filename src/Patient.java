import java.util.Date;
import java.util.Objects;

public class Patient {
    private String firstName;
    private String secondName;
    private Date birthday;
    private boolean healthy;

    public Patient(String firstName, String secondName, Date birthday, boolean healthy) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthday = birthday;
        this.healthy = healthy;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public boolean isHealthy() {
        return healthy;
    }

    @Override
    public String toString() {
        return firstName + " " + secondName + " " + birthday + " " + healthy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return healthy == patient.healthy &&
                Objects.equals(firstName, patient.firstName) &&
                Objects.equals(secondName, patient.secondName) &&
                Objects.equals(birthday, patient.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName, birthday, healthy);
    }
}
