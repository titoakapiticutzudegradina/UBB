package Service;

import Domain.BloodPressure;
import Domain.BodyMassIndex;
import Domain.Date;
import Filter.FilterStatusBMI;
import Filter.FilterStatusBP;
import Repository.Repository;
import Repository.FilterBPRepository;
import Repository.FilterBMIRepository;
import Validators.BloodPressureValidator;
import Validators.BodyMassIndexValidator;
import Validators.DateValidator;

import java.util.ArrayList;
import java.util.Comparator;

public class Service {
    private Repository<Integer, BodyMassIndex> repoBMI;
    private Repository<Integer, BloodPressure> repoBP;
    private DateValidator dateValidator;
    private BodyMassIndexValidator bodyMassIndexValidator;
    private BloodPressureValidator bloodPressureValidator;

    public Service(Repository<Integer, BodyMassIndex> repoBMI, Repository<Integer, BloodPressure> repoBP, DateValidator dateValidator, BodyMassIndexValidator bodyMassIndexValidator, BloodPressureValidator bloodPressureValidator) {
        this.repoBMI = repoBMI;
        this.repoBP = repoBP;
        this.dateValidator = dateValidator;
        this.bodyMassIndexValidator = bodyMassIndexValidator;
        this.bloodPressureValidator = bloodPressureValidator;
    }

    public void addBMI(int BMIid, int day, int month, int year, String status, float bmi){
        if(BMIid < 0){
            throw new IllegalArgumentException("Id must be positive!");
        }
        Date date = new Date(day, month,year);
        dateValidator.validateDate(date);
        BodyMassIndex BMI = new BodyMassIndex(date, status, bmi);
        bodyMassIndexValidator.validateBodyMassIndex(BMI);
        repoBMI.add(BMIid,BMI);
    }

    public void addBP(int BPid, int day, int month, int year, String status, int systolic, int diastolic){
        if(BPid < 0){
            throw new IllegalArgumentException("Id must be positive!");
        }
        Date date = new Date(day, month,year);
        dateValidator.validateDate(date);
        BloodPressure BP= new BloodPressure(date, status, systolic, diastolic);
        bloodPressureValidator.validateBloodPressure(BP);
        repoBP.add(BPid,BP);
    }

    public Iterable<BodyMassIndex> getAllBMI(){
        ArrayList<BodyMassIndex> finallist = (ArrayList<BodyMassIndex>) repoBMI.getAll();
        finallist.sort(BodyMassIndex::compareTo);
        return finallist;
    }

    public Iterable<BloodPressure> getAllBP(){
        ArrayList<BloodPressure> finallist = (ArrayList<BloodPressure>) repoBP.getAll();
        finallist.sort(BloodPressure::compareTo);
        return finallist;
    }

    public Iterable<BodyMassIndex> getAllPast2MonthBMI(String status, int monthCompare){
        FilterBMIRepository filterRepo = new FilterBMIRepository(new FilterStatusBMI(status), repoBMI.getAll());
        Iterable<BodyMassIndex> results = filterRepo.filterAndSort(new FilterStatusBMI(status), repoBMI.getAll());
        ArrayList<BodyMassIndex> finalResults = new ArrayList<>();
        for (BodyMassIndex bmi : results){
            if(monthCompare == 1)
                if(bmi.getDate().getMonth() ==1)
                    finalResults.add(bmi);
            else if (bmi.getDate().getMonth() == (monthCompare -1) || bmi.getDate().getMonth() == monthCompare){
                finalResults.add(bmi);
            }
        }
        return finalResults;
    }

    public Iterable<BloodPressure> getAllPast2MonthBP(String status, int monthCompare){
        FilterBPRepository filterRepo = new FilterBPRepository(new FilterStatusBP(status), repoBP.getAll());
        Iterable<BloodPressure> results = filterRepo.filterAndSort(new FilterStatusBP(status), repoBP.getAll());
        ArrayList<BloodPressure> finalResults = new ArrayList<>();
        for (BloodPressure bp : results){
            if(monthCompare == 1)
                if(bp.getDate().getMonth() ==1)
                    finalResults.add(bp);
                else if (bp.getDate().getMonth() == (monthCompare -1) || bp.getDate().getMonth() == monthCompare){
                    finalResults.add(bp);
                }
        }
        return finalResults;
    }
}
