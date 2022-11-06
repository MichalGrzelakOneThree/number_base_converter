import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String m = scanner.nextLine();
        
        BigInteger biggestFactorial = findMaxFactorialLowerThan(new BigInteger(m));
        System.out.println(biggestFactorial);
    }

    private static BigInteger findMaxFactorialLowerThan(BigInteger m) {
        BigInteger factorial = BigInteger.ONE;
        BigInteger n = BigInteger.ZERO;

        while (n.compareTo(m) < 0) {
            n = n.add(BigInteger.ONE);
            factorial = factorial.multiply(n);
            if (factorial.compareTo(m) >= 0) {
                break;
            }
        }
        return n;
    }
}
