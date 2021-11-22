import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import static java.lang.System.out;

/**
 * Main class
 *
 * @author Team MST
 * @version 0.5
 * @since 27/10/2021
 */

public class Main {
    private static Graph mainframe = null;
    private static Attack[] attacksA;
    private static Scanner in = new Scanner(System.in);

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        printHeader();
        boolean run = true;
        while (run) {
            switch (mainMenu()) {
            case "G" -> gMenu();
            case "A" -> aMenu();
            case "S" -> sMenu();
            case "Q" -> run = false;
            default -> out.println("Invalid selection.");
            }
        }
        out.println("Goodbye!");
    }

    /**
     * Prints the header of the application to the console
     */
    private static void printHeader() {
        out.println("""
                  ,-^-.
                  |\\/\\|
                  `-V-'
                    H
                    H        PlagueSim
                    H           v0.5
                 .-;':-.
                ,' | `; \\
                  """);
        out.println("=========================");
        out.println("      NET/OSS 3004");
        out.println("  Term Project Fall 2021");
        out.println("=========================");
        out.println("        Team MST");
        out.println("     Madeline Quang");
        out.println("     Sayfullah Eid");
        out.println("      Tolu Adebayo");
        out.println("=========================");
    }

    /**
     * Prints out the main menu.
     * 
     * @return the user's input(choice)
     */
    private static String mainMenu() {
        out.println("------- MAIN MENU -------");
        out.println("Choose from these choices");
        out.println("-------------------------\n");
        out.println("G - Graph menu");
        if (mainframe != null) {
            out.println("A - Parse in attack file.");
            out.println("S - Enter search menu.");
        }
        out.println("Q - Quit program");

        return in.next().toUpperCase(Locale.ROOT);
    }

    /**
     * The graph menu, used to:
     * <ul>
     * <li>Parse new graph file</li>
     * <li>Print current graph file(if not null)</li>
     * </ul>
     */
    private static void gMenu() {
        boolean gRun = true;
        String input;
        while (gRun) {
            out.println("------ GRAPH MENU -------");
            out.println("<filePath> - File path to graph file(this will clear an exisiting graph).");
            if (mainframe != null) {
                out.println("P - Print current graph.");
            }
            out.println("Q - Return to main menu.");

            input = in.next();
            switch (input) {
            case "Q" -> gRun = false;
            case "q" -> gRun = false;
            case "P" -> {
                if (mainframe != null)
                    out.println(mainframe);
            }
            case "p" -> {
                if (mainframe != null)
                    out.println(mainframe);
            }
            default -> mainframe = new Graph(input);
            }
        }
    }

    /**
     * The attack menu:
     * <ul>
     * <li>Parse in new attack file</li>
     * </ul>
     */
    private static void aMenu() {
        boolean aRun = true;
        String input;
        while (aRun) {
            out.println("------ ATACK MENU -------");
            out.println("<filePath> - File path to attack file.");
            out.println("Q - Return to main menu.");

            input = in.next();
            switch (input) {
            case "q" -> aRun = false;
            case "Q" -> aRun = false;
            default -> {
                parseAttacks(input);
                out.println("Read in " + attacksA.length + " attacks.");
                sortAttacks(attacksA, 0, attacksA.length - 1);
                for (Attack attack : attacksA) {
                    mainframe.attack(attack);
                }
            }
            }
        }
    }

    /**
     * The search menu:
     * <ul>
     * <li>Lists all nodes that have been attacked.</li>
     * <li>Lists all nodes that have a firewall.</li>
     * <li>Lists all nodes that have a firewall and have been attacked.</li>
     * <li>Lists all nodes that have an outbreak.</li>
     * <li>Lists all nodes currently offline/down</li>
     * </ul>
     */
    private static void sMenu() {
        boolean sRun = true;
        String input;
        while (sRun) {
            out.println("------ SEARCH MENU ------");
            out.println("I - Lists all nodes that have been attacked.");
            out.println("F - Lists all nodes that have a firewall.");
            out.println("IF - Lists all nodes that have a firewall and have been attacked.");
            out.println("O - Lists all nodes that have an outbreak.");
            out.println("D - Lists all nodes currently offline/down");
            out.println("Q - Return to main menu.");

            input = in.next().toUpperCase(Locale.ROOT);
            switch (input) {
            case "I" -> out.println(mainframe.isInfected());
            case "F" -> out.println(mainframe.hasFirewall());
            case "IF" -> out.println(mainframe.hasAttackedFirewall());
            case "O" -> out.println(mainframe.outbreaks());
            case "D" -> out.println(mainframe.inactive());
            case "Q" -> sRun = false;
            default -> out.println("Invalid selection.");
            }
        }
    }

    /**
     * Parses through txt file containing attacks Constructs the relevant Attack
     * objects.
     *
     * @param filename path to file
     */
    private static void parseAttacks(String filename) throws RuntimeException {
        ArrayList<Attack> attacks = new ArrayList<>();
        try {
            Scanner f = new Scanner(new File(filename));
            while (f.hasNext()) {
                String[] line = f.nextLine().split("[,][ ]");
                attacks.add(new Attack(line[1], line[2] + " " + line[3], line[0]));
            }
            f.close();
        } catch (FileNotFoundException | ParseException e) {
            System.out.println("File not found! Check if filename/path is correct!");
            System.exit(1);
        }
        attacksA = new Attack[attacks.size()];
        attacks.toArray(attacksA);
    }

    /**
     * Sorts an {@code Attack[]} chronologically
     * 
     * @param arr   {@code Attack[]} to be sorted
     * @param begin {@code int} starting index to be sorted
     * @param end   {@code int} ending index to be sorted
     */
    private static void sortAttacks(Attack[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            sortAttacks(arr, begin, partitionIndex - 1);
            sortAttacks(arr, partitionIndex + 1, end);
        }
    }

    /**
     * Supporting function for {@code sortAttacks()}
     * 
     * @param arr   {@code Attack[]}
     * @param begin {@code int}
     * @param end   {@code int}
     * @return {@code int}
     */
    private static int partition(Attack[] arr, int begin, int end) {
        long pivot = arr[end].toEpoch();
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j].toEpoch() <= pivot) {
                i++;

                Attack swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        Attack swapTemp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = swapTemp;

        return i + 1;
    }

    /**
     * Supporting function for testing that prints out {@code attacksA}
     */
    private static void printAttacks() {
        for (Attack attack : attacksA) {
            System.out.println(attack);
        }
    }

    /**
     * Supporting funtion for testing tha shuffles an array of {@code Attack[]}
     * 
     * @param ar {@code Attack[]}
     */
    static void shuffleArray(Attack[] ar) {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            Attack a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
}
