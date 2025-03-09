package UI;

import Domain.BloodPressure;
import Domain.BodyMassIndex;
import Service.Service;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {
    private Service service;

    public UI(Service service) {
        this.service = service;
    }

    public static final int EXIT = 0;
    public static final int ADD_BMI = 1;
    public static final int ADD_BP = 2;
    public static final int SHOW_BMI = 3;
    public static final int SHOW_BP = 4;
    public static final int SHOW_PAST2MONTHS_BMI = 5;
    public static final int SHOW_PAST2MONTHS_BP = 6;

    public void showMenu(){
        System.out.println(EXIT + ".Exit");
        System.out.println(ADD_BMI + ".Add body mass index");
        System.out.println(ADD_BP + ".Add blood pressure");
        System.out.println(SHOW_BMI + ".Show body mass index");
        System.out.println(SHOW_BP + ".Show blood pressure");
        System.out.println(SHOW_PAST2MONTHS_BMI + ".Show past 2 months body mass index");
        System.out.println(SHOW_PAST2MONTHS_BP + ".Show past 2 months blood pressure");

    }

    public void run(){
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                showMenu();
                System.out.println("Enter option:");
                int option = scanner.nextInt();
                if (option == EXIT)
                    break;
                switch (option) {
                    case ADD_BMI:
                        System.out.println("Id:");
                        int id = scanner.nextInt();
                        System.out.println("Day:");
                        int day = scanner.nextInt();
                        System.out.println("Month:");
                        int month = scanner.nextInt();
                        System.out.println("Year:");
                        int year = scanner.nextInt();
                        System.out.println("Status(done/ in progress):");
                        String status = scanner.next();
                        System.out.println("Body mass index:");
                        float bmi = scanner.nextFloat();
                        service.addBMI(id, day, month, year, status, bmi);
                    case ADD_BP:
                        System.out.println("Id:");
                        int idN = scanner.nextInt();
                        System.out.println("Day:");
                        int dayN = scanner.nextInt();
                        System.out.println("Month:");
                        int monthN = scanner.nextInt();
                        System.out.println("Year:");
                        int yearN = scanner.nextInt();
                        System.out.println("Status(done/ in progress):");
                        String statusN = scanner.next();
                        System.out.println("Systolic value:");
                        int systolic = scanner.nextInt();
                        System.out.println("Diastolic value:");
                        int diastolic = scanner.nextInt();
                        service.addBP(idN, dayN, monthN, yearN, statusN, systolic, diastolic);
                    case SHOW_BMI:
                        for (BodyMassIndex bodyMassIndex : service.getAllBMI()) {
                            System.out.println(bodyMassIndex);
                        }
                        break;
                    case SHOW_BP:
                        for (BloodPressure bloodPressure : service.getAllBP()) {
                            System.out.println(bloodPressure);
                        }
                        break;
                    case SHOW_PAST2MONTHS_BMI:
                        System.out.println("Enter status:");
                        String statusfilte = scanner.next();
                        System.out.println("Enter month:");
                        int monthfilter = scanner.nextInt();
                        for (BodyMassIndex bmifliter : service.getAllPast2MonthBMI(statusfilte, monthfilter))
                            System.out.println(bmifliter);
                        break;
                    case SHOW_PAST2MONTHS_BP:
                        System.out.println("Enter status:");
                        String statusfilteN = scanner.next();
                        System.out.println("Enter month:");
                        int monthfilterN = scanner.nextInt();
                        for (BloodPressure bpfliter : service.getAllPast2MonthBP(statusfilteN, monthfilterN))
                            System.out.println(bpfliter);
                        break;
                }
            }catch (InputMismatchException exception) {
                System.out.println("Wrong type!");
                scanner.next();
            }catch (RuntimeException exception){
                System.out.println(exception.getMessage());
            }
        }
    }

}
