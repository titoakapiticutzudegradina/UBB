import java.util.ArrayList;
import java.util.Scanner;

public class Second {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        System.out.println("Enter how many numbers you want to compare: ");
        int n = scanner.nextInt();
        System.out.println("Enter the numbers: ");
        for(int i = 0; i < n; i++){
            numbers.add(scanner.nextInt());
        }
        int max = numbers.get(0);
        for(int i = 1; i < n; i++){
            if(numbers.get(i) > max){
                max = numbers.get(i);
            }
        }
        System.out.println("The maximum number is: " + max);
    }
}
