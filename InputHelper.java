import java.util.Scanner;

public class InputHelper {
    private final Scanner scanner;

    public InputHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Invalid input. Please enter a non-blank value.");
        }
    }

    public int readInt(String prompt, int minimum) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();

            try {
                int number = Integer.parseInt(value);
                if (number >= minimum) {
                    return number;
                }
                System.out.println("Invalid input. Enter a number of at least " + minimum + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    public boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt + " (Y/N): ");
            String value = scanner.nextLine().trim();

            if (value.equalsIgnoreCase("Y") || value.equalsIgnoreCase("YES")) {
                return true;
            }
            if (value.equalsIgnoreCase("N") || value.equalsIgnoreCase("NO")) {
                return false;
            }

            System.out.println("Invalid input. Please enter Y or N.");
        }
    }
}
