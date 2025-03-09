package Repository;

import Domain.Identifiable;
import java.util.HashMap;
import java.util.Iterator;

public class MemoryRepository <IDENTIFIABLE_TYPE, OBJECT extends Identifiable<IDENTIFIABLE_TYPE>> implements Repository<IDENTIFIABLE_TYPE, OBJECT>{
    protected HashMap<IDENTIFIABLE_TYPE, OBJECT> map = new HashMap<>();

    @Override
    public void add(IDENTIFIABLE_TYPE identifiable, OBJECT element){
        map.put(identifiable, element);
    }

    @Override
    public void remove(IDENTIFIABLE_TYPE identifiable){
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
