import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BigDecimal startingAmount = scanner.nextBigDecimal();
        BigDecimal rate = scanner.nextBigDecimal();
        int years = scanner.nextInt();
        System.out.println("Amount of money in the account: "
                + startingAmount
                .multiply(BigDecimal.ONE
                        .add(rate.divide(BigDecimal.TEN.multiply(BigDecimal.TEN)))
                        .pow(years))
                .setScale(2, RoundingMode.CEILING));
    }
}