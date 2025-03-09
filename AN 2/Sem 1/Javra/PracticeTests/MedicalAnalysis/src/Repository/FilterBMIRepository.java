package Repository;

import Domain.BodyMassIndex;
import Filter.Filter;

import java.util.ArrayList;

public class FilterBMIRepository extends MedicalAnalysisRepository<Integer, BodyMassIndex>{
    private Filter<BodyMassIndex> filter;
    private Iterable<BodyMassIndex> bmis = new ArrayList<>();

    public FilterBMIRepository(Filter<BodyMassIndex> filter, Iterable<BodyMassIndex> rentals) {
        this.filter = filter;
        this.bmis = rentals;
    }

    public Iterable<BodyMassIndex> filterAndSort(Filter<BodyMassIndex> filter, Iterable<BodyMassIndex> bmis) {
        ArrayList<BodyMassIndex> filteredbmis = new ArrayList<>();
        for (BodyMassIndex bmi : bmis) {
            if (filter.accept(bmi)) {
                filteredbmis.add(bmi);
            }
        }
        return filteredbmis;
    }
}
