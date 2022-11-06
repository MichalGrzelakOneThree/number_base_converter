import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int power = scanner.nextInt();
        int scale = scanner.nextInt();
        BigDecimal number = scanner.nextBigDecimal()
                        .setScale(scale, RoundingMode.FLOOR)
                        .pow(power);
        System.out.println(number);
    }
}