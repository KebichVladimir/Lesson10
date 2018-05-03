import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ConsolePatientInput implements PatientInput {

    @Override
    public Patient addPatient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя:");
        String firstName = scanner.nextLine();
        System.out.println("Введите фамилию:");
        String secondName = scanner.nextLine();
        System.out.println("Введите дату рождения(yyyy-MM-dd):");
        Date birthday = birthday(scanner.nextLine());
        System.out.println("Введите состояние здоровья(true/false):");
        boolean healthy = scanner.nextBoolean();
        return new Patient(firstName, secondName, birthday, healthy);
    }

    private Date birthday(String birthday) {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(birthday);
        } catch (ParseException e) {
            System.out.println("Неверная дата рождения, введите дату заново");
            return birthday(scanner.nextLine());
        }
    }
}
