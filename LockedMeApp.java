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

    