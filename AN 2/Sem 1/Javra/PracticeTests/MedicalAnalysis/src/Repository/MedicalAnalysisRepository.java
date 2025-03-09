package Repository;

import Domain.Date;
import Domain.MedicalAnalysis;

import java.util.HashMap;
import java.util.Iterator;

public class MedicalAnalysisRepository <IDENTIFIABLE_TYPE, OBJECT extends MedicalAnalysis<Date, String>> implements Repository<IDENTIFIABLE_TYPE, OBJECT>{
    protected HashMap<IDENTIFIABLE_TYPE, OBJECT> map = new HashMap<>();

    @Override
    public void add(IDENTIFIABLE_TYPE identifiable, OBJECT element){
        map.put(identifiable, element);
    }

    @Override
    public void delete(IDENTIFIABLE_TYPE identifiable){
        map.remove(identifiable);
    }

    @Override
    public void update(IDENTIFIABLE_TYPE identifiable, OBJECT element){
        map.replace(identifiable, element);
    }

    @Override
    public OBJECT findById(IDENTIFIABLE_TYPE identifiable){
        return map.get(identifiable);
    }

    @Override
    public Iterable<OBJECT> getAll(){
        return map.values();
    }

    @Override
    public Iterator<OBJECT> iterator(){
        return map.values().iterator();
    }
}
