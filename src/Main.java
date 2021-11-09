import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * Main class
 *
 * @author Team MST
 * @version 0.1
 * @since 27/10/2021
 */

public class Main {
    public static void main(String[] args) {
        parseAttacks("src/tests/attack.txt");
    }

    /**
     * Parses through txt file containing attacks Constructs the relevant Attack
     * objects Calls the relevant functions on the graph
     *
     * @param filename path to file
     */
    private static void parseAttacks(String filename) throws RuntimeException {
        Attack[] attacks = new Attack[400];
        try {
            int i = 0;
            Scanner f = new Scanner(new File(filename));
            while (f.hasNext()) {
                String[] line = f.nextLine().split("[,][ ]");
                attacks[i] = new Attack(line[1], line[2] + " " + line[3], line[0]);
                System.out.println(attacks[i]);
                i++;
            }
            f.close();
        } catch (FileNotFoundException | ParseException e) {
            System.out.println("File not found! Check if filename/path is correct!");
            System.exit(1);
        }
    }

}
