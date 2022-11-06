import java.math.BigDecimal;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BigDecimal dwalin = sc.nextBigDecimal();
        BigDecimal balin = sc.nextBigDecimal();
        BigDecimal thorin = sc.nextBigDecimal();
        System.out.println(dwalin.add(balin.add(thorin)));
    }
}