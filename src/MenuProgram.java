import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;

public class MenuProgram implements Menu {
    private LinkedHashSet<Patient> basePatient = new LinkedHashSet<>();

    @Override
    public int makeChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n1.Добавить пациента");
        System.out.println("2.Загрузить онлайн базу пациентов");
        System.out.println("3.Показать базу пациентов");
        System.out.println("4.Сохранение базы пациентов");
        System.out.println("5.Выход");
        int answer = scanner.nextInt();
        return answer;
    }

    public void chooseOperation() throws IOException, ParseException {
        DOMxmlReader domXmlReader = new DOMxmlReader("Base.xml");
        basePatient = domXmlReader.ReadPatientBase();
        boolean exit = true;
        while (exit) {
            switch (makeChoice()) {
                case 1:
                    PatientInput patientInput = new ConsolePatientInput();
                    Patient patient = patientInput.addPatient();
                    basePatient.add(patient);
                    break;
                case 2:
                    OnlinePatientBase onlinePatientBase = new OnlinePatientBase();
                    List<Patient> basePatientOnline = onlinePatientBase.getOnlinePatientBase();
                    for (Patient patients : basePatientOnline)
                        basePatient.add(patients);
                    break;
                case 3:
                    for (Patient patients : basePatient)
                        System.out.println(patients);
                    break;
                case 4:
                    DOMxmlWriter domXmlWriter = new DOMxmlWriter("Base.xml");
                    domXmlWriter.WritePatientBase(basePatient);
                    break;
                case 5:
                    exit = false;
                    break;
                default:
                    System.out.println("Данной операции нет, повторите ввод");
            }
        }
    }
}