 
import java.util.Scanner;

public class TestProgram {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        BSearchTreeType tree = new BSearchTreeType();

        boolean running = true;
        while (running) {
            printMenu();
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                    addPatient(tree);
                    if (!askToContinue()) running = false;
                    break;
                case "2":
                    searchByName(tree);
                    if (!askToContinue()) running = false;
                    break;
                case "3":
                    searchByRoom(tree);
                    if (!askToContinue()) running = false;
                    break;
                case "4":
                    updatePatient(tree);
                    if (!askToContinue()) running = false;
                    break;
                case "5":
                    deletePatient(tree);
                    if (!askToContinue()) running = false;
                    break;
                case "6":
                    System.out.println("\n-- Inorder listing --");
                    tree.inorderTraversal();
                    if (!askToContinue()) running = false;
                    break;
                case "7":
                    System.out.println("Tree Height: " + tree.treeHeight());
                    System.out.println("Number of Nodes: " + tree.treeNodeCount());
                    System.out.println("Number of Leaves: " + tree.treeLeavesCount());
                    if (!askToContinue()) running = false;
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        sc.close();
        System.out.println("Exiting. Goodbye.");
    }

    private static void printMenu() {
        System.out.println("\nHospital Records Menu:");
        System.out.println("1) Add new patient");
        System.out.println("2) Search patient by name");
        System.out.println("3) Search patient by room number");
        System.out.println("4) Update patient record");
        System.out.println("5) Delete patient record");
        System.out.println("6) Print all patients (inorder)");
        System.out.println("7) Tree statistics");
        System.out.println("0) Exit");
        System.out.print("Enter choice: ");
    }

    private static void addPatient(BSearchTreeType tree) {
        System.out.print("Enter full name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter medical history summary: ");
        String history = sc.nextLine().trim();
        System.out.print("Enter admission date (YYYY-MM-DD): ");
        String date = sc.nextLine().trim();
        System.out.print("Enter room number: ");
        int room = readIntOrDefault(-1);

        NodeType node = new NodeType(name, history, date, room);
        tree.insert(node);
        System.out.println("Patient added (or duplicate prevented).\n");
    }

    private static void searchByName(BSearchTreeType tree) {
        System.out.print("Enter name to search: ");
        String name = sc.nextLine().trim();
        NodeType found = tree.getPatientByName(name);
        if (found != null) System.out.println("Found: " + found);
        else System.out.println("No patient found with that name.");
    }

    private static void searchByRoom(BSearchTreeType tree) {
        System.out.print("Enter room number to search: ");
        int room = readIntOrDefault(-1);
        NodeType found = tree.searchByRoom(room);
        if (found != null) System.out.println("Found: " + found);
        else System.out.println("No patient found in that room.");
    }

    private static void updatePatient(BSearchTreeType tree) {
        System.out.print("Enter name of patient to update: ");
        String name = sc.nextLine().trim();
        NodeType p = tree.getPatientByName(name);
        if (p == null) {
            System.out.println("Patient not found.");
            return;
        }
        System.out.println("Current record: " + p);
        System.out.print("Enter new medical history (leave blank to keep): ");
        String history = sc.nextLine().trim();
        if (!history.isEmpty()) p.medicalHistory = history;
        System.out.print("Enter new admission date (leave blank to keep): ");
        String date = sc.nextLine().trim();
        if (!date.isEmpty()) p.admissionDate = date;
        System.out.print("Enter new room number (leave blank to keep): ");
        String roomStr = sc.nextLine().trim();
        if (!roomStr.isEmpty()) {
            try { p.roomNumber = Integer.parseInt(roomStr); } catch (NumberFormatException e) { /* ignore */ }
        }
        System.out.println("Patient updated: " + p);
    }

    private static void deletePatient(BSearchTreeType tree) {
        System.out.print("Enter name of patient to delete: ");
        String name = sc.nextLine().trim();
        tree.deleteNode(name);
    }

    private static int readIntOrDefault(int def) {
        String s = sc.nextLine().trim();
        if (s.isEmpty()) return def;
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    private static boolean askToContinue() {
        while (true) {
            System.out.print("Would you like to make another selection? (y/n): ");
            String response = sc.nextLine().trim().toLowerCase();
            if (response.equals("y")) {
                return true;
            } else if (response.equals("n")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
        }
    }
}
