package Domain;

public interface Identifiable<IDENTIFIABLE_TYPE>{
    IDENTIFIABLE_TYPE getId();
    void setId(IDENTIFIABLE_TYPE identifiable);
}
