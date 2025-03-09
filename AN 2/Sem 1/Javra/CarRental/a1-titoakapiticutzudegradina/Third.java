import java.util.Scanner;

public class Third {

    public int gcd(int a, int b){
        if(b == 0) return a;
        return gcd(b, a % b);
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int a = scanner.nextInt();
        System.out.println("Enter another number: ");
        int b = scanner.nextInt();
        System.out.println("The greatest common divisor is: " + new Third().gcd(a, b));
    }
}
