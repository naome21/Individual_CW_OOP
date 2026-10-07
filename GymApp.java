import java.util.List;
import java.util.Scanner;

public class GymApp {
    private static final String STUDENT_NAME = "KATUSHABE NAOME";
    private static final String REGISTRATION_NUMBER = "VU-BIT-2511-0786-EVE";
    private static final String ID_PREFIX = "KN786-";

    private final GymManager manager = new GymManager();
    private final InputHelper input;

    private int nextIdNumber = 1;

    public GymApp(Scanner scanner) {
        this.input = new InputHelper(scanner);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            new GymApp(scanner).run();
        }
    }

    private void run() {
        printHeader();

        boolean running = true;
        while (running) {
            showMenu();
            int choice = input.readInt("Choose an option: ", 1);

            switch (choice) {
                case 1 -> addMonthlyMember();
                case 2 -> addDailyMember();
                case 3 -> listReport();
                case 4 -> findMember();
                case 5 -> removeMember();
                case 6 -> showSummary();
                case 7 -> {
                    running = false;
                    System.out.println("Thank you for using our Gym Membership System.");
                }
                default -> System.out.println("Invalid menu option. Please choose 1 to 7.");
            }
        }
    }

    private void printHeader() {
        System.out.println("==============================================================");
        System.out.println("                 GYM MEMBERSHIP SYSTEM");
        System.out.println("Student: " + STUDENT_NAME);
        System.out.println("Registration No.: " + REGISTRATION_NUMBER);
        System.out.println("==============================================================");
    }

    private void showMenu() {
        System.out.println("\n---------------- MAIN MENU ----------------");
        System.out.println("1. Add Monthly Member");
        System.out.println("2. Add Daily Member");
        System.out.println("3. List Sorted Report");
        System.out.println("4. Find Member");
        System.out.println("5. Remove Member");
        System.out.println("6. Show Summary");
        System.out.println("7. Exit");
        System.out.println("-------------------------------------------");
    }

    private String generateId() {
        return ID_PREFIX + String.format("%03d", nextIdNumber++);
    }

    private void addMonthlyMember() {
        System.out.println("\n--- Add Monthly Member ---");
        String name = input.readString("Customer name: ");
        int months = input.readInt("Number of months: ", 1);
        boolean student = input.readYesNo("Is the member a student?");

        String id = generateId();

        try {
            MonthlyMember member = new MonthlyMember(id, name, months, student);
            manager.addMembership(member);

            System.out.println("Monthly member added successfully.");
            System.out.println("Membership ID: " + id);
            System.out.printf("Charge: UGX %,.0f%n", member.calculateCharge());

            if (member.getDiscountAmount() > 0) {
                System.out.printf("13%% student discount: UGX %,.0f%n",
                        member.getDiscountAmount());
            }
        } catch (InvalidMembershipException e) {
            System.out.println("Could not add member: " + e.getMessage());
        }
    }

    private void addDailyMember() {
        System.out.println("\n--- Add Daily Member ---");
        String name = input.readString("Customer name: ");
        int visits = input.readInt("Number of visits: ", 1);

        String id = generateId();

        try {
            DailyMember member = new DailyMember(id, name, visits);
            manager.addMembership(member);

            System.out.println("Daily member added successfully.");
            System.out.println("Membership ID: " + id);
            System.out.printf("Charge: UGX %,.0f%n", member.calculateCharge());
        } catch (InvalidMembershipException e) {
            System.out.println("Could not add member: " + e.getMessage());
        }
    }

    private void listReport() {
        System.out.println("\n--- SORTED MEMBERSHIP REPORT ---");
        List<Membership> members = manager.getSortedMemberships();

        if (members.isEmpty()) {
            System.out.println("No membership records found.");
            return;
        }

        System.out.printf("%-12s %-18s %-25s %12s%n",
                "ID", "TYPE", "CUSTOMER NAME", "CHARGE (UGX)");
        System.out.println("---------------------------------------------------------------------");

        // Polymorphism: each object is treated as Membership and calculateCharge()
        // executes the correct subclass implementation.
        for (Membership member : members) {
            System.out.printf("%-12s %-18s %-25s %,12.0f%n",
                    member.getId(),
                    member.getRecordType(),
                    member.getCustomerName(),
                    member.calculateCharge());
        }
    }

    private void findMember() {
        System.out.println("\n--- Find Member ---");
        String id = input.readString("Enter membership ID: ");

        Membership member = manager.findById(id);

        if (member == null) {
            System.out.println("No membership found with ID " + id + ".");
            return;
        }

        System.out.println("Record found:");
        System.out.println("ID: " + member.getId());
        System.out.println("Type: " + member.getRecordType());
        System.out.println("Customer: " + member.getCustomerName());
        System.out.printf("Charge: UGX %,.0f%n", member.calculateCharge());
    }

    private void removeMember() {
        System.out.println("\n--- Remove Member ---");
        String id = input.readString("Enter membership ID: ");

        Membership member = manager.findById(id);
        if (member == null) {
            System.out.println("No membership found with ID " + id + ".");
            return;
        }

        System.out.println("Customer: " + member.getCustomerName());
        System.out.println("Type: " + member.getRecordType());

        boolean confirmed = input.readYesNo("Are you sure you want to remove this record?");
        if (!confirmed) {
            System.out.println("Removal cancelled.");
            return;
        }

        if (manager.removeMembership(id)) {
            System.out.println("Membership removed successfully.");
        } else {
            System.out.println("Membership could not be removed.");
        }
    }

    private void showSummary() {
        System.out.println("\n--- SUMMARY ---");
        System.out.println("Total membership records: " + manager.size());
        System.out.printf("Total charges: UGX %,.0f%n", manager.getTotalCharges());
        System.out.printf("Total discount given: UGX %,.0f%n", manager.getTotalDiscount());
    }
}
