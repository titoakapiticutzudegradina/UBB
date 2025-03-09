package Repository;

import Domain.BloodPressure;
import Domain.BloodPressure;
import Filter.Filter;

import java.util.ArrayList;

public class FilterBPRepository extends MedicalAnalysisRepository<Integer, BloodPressure>{
    private Filter<BloodPressure> filter;
    private Iterable<BloodPressure> bps = new ArrayList<>();

    public FilterBPRepository(Filter<BloodPressure> filter, Iterable<BloodPressure> bps) {
        this.filter = filter;
        this.bps = bps;
    }

    public Iterable<BloodPressure> filterAndSort(Filter<BloodPressure> filter, Iterable<BloodPressure> bps) {
        ArrayList<BloodPressure> filteredbps = new ArrayList<>();
        for (BloodPressure bp : bps) {
            if (filter.accept(bp)) {
                filteredbps.add(bp);
            }
        }
        return filteredbps;
    }
}
