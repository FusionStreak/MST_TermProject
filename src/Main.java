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
 * @version 0.1
 * @since 27/10/2021
 */

public class Main {
    private static Graph main = null;
    private static Attack[] attacksA;
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        printHeader();
        mainMenu(in);
    }

    private static void printHeader() {
        out.println("""
                  ,-^-.
                  |\\/\\|
                  `-V-'
                    H
                    H        PlagueSim
                    H           v0.5
                 .-;":-.
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

    private static String mainMenu(Scanner in) {
        out.println("------- MAIN MENU -------");
        out.println("Choose from these choices");
        out.println("-------------------------\n");
        out.println("G - Setup new graph");
        if (main != null) {
            out.println("A - Parse in new attack file");
            out.println("S - Enter search menu");
        }
        out.println("Q - Quit program");

        return in.next().toUpperCase(Locale.ROOT);
    }

    private static void gMenu(Scanner in) {
        boolean gRun = true;
        String input;
        while (gRun) {
            out.println("--------GRAPH MENU--------");
            out.println("<filePath> - File path to graph file(this will clear an exisiting graph).");
            out.println("Q - Return to main menu");

            input = in.next();
            if ("Q".equals(input)) {
                gRun = false;
            } else {
                main = new Graph(input);
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

    private static void sortAttacks(Attack[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            sortAttacks(arr, begin, partitionIndex - 1);
            sortAttacks(arr, partitionIndex + 1, end);
        }
    }

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

    private static void printAttacks() {
        for (Attack attack : attacksA) {
            System.out.println(attack);
        }
    }

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
