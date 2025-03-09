package Tests;

import Domain.Rental;
import Repository.Repository;

import java.util.Iterator;

public class FakeRepository implements Repository<Integer, Rental> {
    public boolean delete_should_throw_exception = false;

    public void delete(){
        if(delete_should_throw_exception){
            throw new IllegalArgumentException("Element doesn't exist.");
        }
    }

    @Override
    public void add(Integer identifiable, Rental rental){
    }

    @Override
    public void remove(Integer identifiable){
    }

    @Override
    public void update(Integer identifiable, Rental rental){
    }

    @Override
    public Rental findById(Integer identifiable){
        return null;
    }

    @Override
    public Iterable<Rental> getAll(){
        return null;
    }

    @Override
    public Iterator<Rental> iterator(){
        return null;
    }

}
