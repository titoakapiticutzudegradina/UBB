import Domain.BloodPressure;
import Domain.BodyMassIndex;
import Repository.Repository;
import Repository.BinaryFileBMIRepository;
import Repository.BinaryFileBPRepository;
import Service.Service;
import UI.UI;
import Validators.BloodPressureValidator;
import Validators.BodyMassIndexValidator;
import Validators.DateValidator;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Repository<Integer,BodyMassIndex> repoBMI = new BinaryFileBMIRepository("bmi.bin");
        Repository<Integer, BloodPressure> repoBP = new BinaryFileBPRepository("bp.bin");
        DateValidator dateValidator = new DateValidator();
        BodyMassIndexValidator bodyMassIndexValidator = new BodyMassIndexValidator();
        BloodPressureValidator bloodPressureValidator = new BloodPressureValidator();

        Service service = new Service(repoBMI, repoBP, dateValidator, bodyMassIndexValidator,bloodPressureValidator);
        UI ui = new UI(service);
        ui.run();
    }
}