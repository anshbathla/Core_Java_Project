import java.io.File;
import java.io.IOException;
import java.util.*;

public class LockedMeApp {
    private static final String ROOT_DIR = "lockedme_files";

    public static void main(String[] args) {
        setupRootDirectory();
        showWelcomeScreen();

        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            showMainMenu();
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    listFiles();
                    break;
                case "2":
                    showBusinessOptions(sc);
                    break;
                case "3":
                    System.out.println("\nExiting. Goodbye!\n");
                    exit = true;
                    break;
                default:
                    System.out.println("\n⚠ Invalid option, please try again.\n");
                    break;
            }
        }

        sc.close();
    }

    private static void setupRootDirectory() {
        new File(ROOT_DIR).mkdirs();
    }

    private static void showWelcomeScreen() {
        System.out.println("\n=================================");
        System.out.println("           LockedMe.com             ");
        System.out.println("      Developer: Ansh Bathla     ");
        System.out.println("=================================\n");
    }

    private static void showMainMenu() {
        System.out.println("Main Menu:");
        System.out.println("1. List all files (sorted)");
        System.out.println("2. Business options (Add/Delete/Search)");
        System.out.println("3. Exit\nYour choice: ");
    }

    private static void listFiles() {
        File directory = new File(ROOT_DIR);
        String[] files = directory.list();
        if (files == null || files.length == 0) {
            System.out.println("\n(No files found)\n");
            return;
        }

        List<String> fileList = new ArrayList<>(Arrays.asList(files));
        Collections.sort(fileList);
        System.out.println("\nFiles in ascending order:");
        fileList.forEach(f -> System.out.println("- " + f));
        System.out.println();
    }
  
    private static void showBusinessOptions(Scanner sc) {
        boolean backToMain = false;
        while (!backToMain) {
            System.out.println("\nBusiness Menu:");
            System.out.println("1. Add file");
            System.out.println("2. Delete file");
            System.out.println("3. Search file");
            System.out.println("4. Back to Main Menu\nYour choice: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    addFile(sc);
                    break;
                case "2":
                    deleteFile(sc);
                    break;
                case "3":
                    searchFile(sc);
                    break;
                case "4":
                    backToMain = true;
                    break;
                default:
                    System.out.println("\n⚠ Invalid option, please try again.");
                    break;
            }
        }
    }

    private static void addFile(Scanner sc) {
        System.out.print("\nEnter file name to add: ");
        String fileName = sc.nextLine().trim();
        File file = new File(ROOT_DIR, fileName);

        try {
            if (file.exists()) {
                System.out.println("\n⚠ File already exists.");
            } else {
                file.createNewFile();
                System.out.println("\n✅ File successfully added.");
            }
        } catch (IOException e) {
            System.out.println("\n❌ Error creating file: " + e.getMessage());
        }
    }

    private static void deleteFile(Scanner sc) {
        System.out.print("\nEnter file name to delete (case-sensitive): ");
        String fileName = sc.nextLine().trim();
        File file = new File(ROOT_DIR, fileName);

        if (file.exists()) {
            file.delete();
            System.out.println("\n✅ File successfully deleted.");
        } else {
            System.out.println("\n❌ FNF (File not found)");
        }
    }

    private static void searchFile(Scanner sc) {
        System.out.print("\nEnter file name to search (case-sensitive): ");
        String fileName = sc.nextLine().trim();
        File file = new File(ROOT_DIR, fileName);

        if (file.exists()) {
            System.out.println("\n✅ File found.");
        } else {
            System.out.println("\n❌ FNF (File not found)");
        }
    }
}
    