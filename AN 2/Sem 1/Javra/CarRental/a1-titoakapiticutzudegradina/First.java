import java.util.Scanner;

public class First {

    public static boolean prime(int n){
        if(n <= 1) return false;
        if(n == 2) return true;
        for(int i = 2; i <= n/2; i++){
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int a = scanner.nextInt();
        System.out.println("Enter another number: ");
        int b = scanner.nextInt();
        for(int i = a; i <= b; i++ ){
            if(prime(i)){
                System.out.println(i);
            }
        }
    }
}
