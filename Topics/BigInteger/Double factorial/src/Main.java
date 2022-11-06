import java.math.BigInteger;

class DoubleFactorial {
    public static BigInteger calcDoubleFactorial(int input) {
        BigInteger factorial = BigInteger.ONE;
        int n = input;

        while (n > 0) {
            factorial = factorial.multiply(BigInteger.valueOf(n));
            n -= 2;
        }
        return factorial;
    }
}