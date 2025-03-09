package Filter;

public interface Filter <OBJECT>{
    public boolean accept(OBJECT element);       //An element is accepted if it satisfies the condition
}
