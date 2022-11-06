import java.math.BigInteger;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] numbers = scanner.nextLine().split(" ");
        BigInteger num1 = new BigInteger(numbers[0]);
        BigInteger num2 = new BigInteger(numbers[1]);
        BigInteger num3 = new BigInteger(numbers[2]);
        BigInteger num4 = new BigInteger(numbers[3]);

        System.out.println(num1.negate()
                .multiply(num2)
                .add(num3)
                .subtract(num4));
    }
}