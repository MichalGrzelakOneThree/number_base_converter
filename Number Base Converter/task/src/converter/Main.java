package converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        String input;
        int sourceBase;
        int targetBase;

        while (true) {
            System.out.println("Enter two numbers in format:" +
                    " {source base} {target base} (To quit type /exit)");
            input = scanner.nextLine();
            if ("/exit".equals(input)) {
                break;
            } else {
                try {
                    String[] bases = input.split(" ");
                    sourceBase = Integer.parseInt(bases[0]);
                    targetBase = Integer.parseInt(bases[1]);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    break;
                }
            }
            while (true) {
                System.out.println("Enter number in base " + sourceBase + " to convert" +
                        " to base " + targetBase + " base} (To go back type /back)");
                input = scanner.nextLine();
                if ("/back".equals(input)) {
                    System.out.println();
                    break;
                }

                BigInteger convertedIntegerPartDec;
                BigDecimal convertedFractionalPartDec;
                String convertedIntegerToTarget;
                String convertedFractionalToTarget;
                String convertedNumber;

                if (input.contains(".")) {
                    String[] partsOfNumber = input.split("\\.");
                    if (sourceBase == targetBase) {
                        convertedNumber = input;
                    }
                    if (sourceBase != 10) {
                        convertedIntegerPartDec = convertIntegerToDecimal(partsOfNumber[0], sourceBase);
                        convertedFractionalPartDec = convertFractionalToDecimal(partsOfNumber[1], sourceBase);
                        convertedIntegerToTarget = convertIntegerToTargetBase(convertedIntegerPartDec, targetBase);
                        convertedFractionalToTarget = convertFractionalToTargetBase(convertedFractionalPartDec, targetBase);
                    } else {
                        convertedIntegerToTarget = convertIntegerToTargetBase(new BigInteger(partsOfNumber[0]), targetBase);
                        convertedFractionalToTarget = convertFractionalToTargetBase(new BigDecimal(partsOfNumber[1]), targetBase);
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append(convertedIntegerToTarget).append(convertedFractionalToTarget);
                    for (int i = 0; i < sb.toString().length() - 1; i++) {
                        if (sb.toString().charAt(i) == '0' && sb.toString().charAt(i + 1) == '.') {
                            sb.deleteCharAt(i);
                            break;
                        }
                    }
                    convertedNumber = sb.toString();
                } else {
                    if (sourceBase != 10) {
                        convertedIntegerPartDec = convertIntegerToDecimal(input, sourceBase);
                        convertedIntegerToTarget = convertIntegerToTargetBase(convertedIntegerPartDec, targetBase);
                    } else {
                        convertedIntegerToTarget = convertIntegerToTargetBase(new BigInteger(input), targetBase);
                    }
                    convertedNumber = convertedIntegerToTarget;
                }
                System.out.println("Conversion result: " + convertedNumber + "\n");
            }
        }
    }

    private static String convertIntegerToTargetBase(BigInteger c, int targetBase) {

        return c.toString(targetBase);
    }

    private static String convertFractionalToTargetBase(BigDecimal d, int targetBase) {

        BigDecimal targetBaseBD = new BigDecimal(targetBase);
        StringBuilder sb = new StringBuilder("0.");
        BigDecimal remainder = d.multiply(targetBaseBD);
        int i = 0;
        while (true) {
            if (i < 5) {
                sb.append(mapNumberToCode(remainder
                        .setScale(0, RoundingMode.DOWN)
                        .intValue()));
                if (remainder.compareTo(BigDecimal.ONE) > 0) {
                    remainder = remainder.subtract(remainder.setScale(0, RoundingMode.DOWN));
                }
                remainder = remainder.multiply(targetBaseBD);
                i++;
            } else {
                break;
            }
        }
        return sb.toString();
    }

    private static BigInteger convertIntegerToDecimal(String a, int sourceBase) {

        BigInteger integerSum = BigInteger.ZERO;
        BigInteger base = BigInteger.valueOf(sourceBase);

        for (int i = a.length() - 1, j = 0; i >= 0; i--, j++) {
            char c = a.toUpperCase().charAt(i);
            int number = mapCodeToNumber(c);
            integerSum = integerSum.add(BigInteger.valueOf(number).multiply(base.pow(j)));
        }

        return integerSum;
    }

    private static BigDecimal convertFractionalToDecimal(String b, int sourceBase) {

        BigDecimal fractionalSum = BigDecimal.ZERO;
        BigDecimal base = BigDecimal.valueOf(sourceBase);

        for (int i = 0; i < b.length(); i++) {
            char c = b.toUpperCase().charAt(i);
            int number = mapCodeToNumber(c);
            fractionalSum = fractionalSum.add(BigDecimal.valueOf(number)
                    .divide(base.pow(i + 1), 10, RoundingMode.DOWN));
        }

        return fractionalSum.setScale(5, RoundingMode.HALF_UP);
    }

    private static int mapCodeToNumber(char ch) {
        return ch <= '9' ? (ch - '0') : (ch - 'A' + 10);
    }

    private static char mapNumberToCode(int number) {
        return number < 10 ? (char) (number + '0') : (char) (number + 'A' - 10);
    }
}
