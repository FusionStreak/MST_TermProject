import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Graph class
 *
 * @author Team MST
 * @version 0.1
 * @since 27/10/2021
 */

public class Graph {
    /** HashMap<> of City objects and list of neighbouring cities */
    private final HashMap<City, City[]> citiesMap = new HashMap<>();

    Graph() {

    }

    /**
     * Constructs graph based on an input file
     *
     * @param file path to graph file
     */
    Graph(String file) {
        try {
            Scanner f = new Scanner(new File(file));
            boolean isRoutes = false;
            while (f.hasNext()) {
                String line = f.nextLine();
                if (line.contains("---")) {
                    isRoutes = true;
                } else if (!isRoutes) {
                    String[] l = line.split("[,][ ]");
                    l[1] = l[1].replace("(", "");
                    l[2] = l[2].replace(")", "");
                    City.Point p = new City.Point(Float.parseFloat(l[1]), Float.parseFloat(l[2]));
                    if (l.length == 3) {
                        this.addCity(l[0], false, p);
                    } else if (l.length == 4) {
                        this.addCity(l[0], true, p);
                    }
                } else {
                    String[] r = line.split("[,][ ]");
                    this.addRoute(r[0], r[1]);
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    /**
     * 
     * @param cit
     * @param c1
     * @return
     */
    private City[] extendArray(City[] cit, City c1) {
        if (cit[0] == null) {
            cit[0] = c1;
            return cit;
        }
        City[] tmp = new City[cit.length + 1];
        System.arraycopy(cit, 0, tmp, 0, cit.length);
        tmp[cit.length] = c1;
        return tmp;
    }

    /**
     * 
     * @param cit
     * @return
     */
    private String printCityNames(City[] cit) {
        String str = "";
        for (City city : cit) {
            if (city == null)
                break;
            str += city.getName() + ", ";
        }
        return str;
    }

    /**
     * 
     * @param c1
     * @param c2
     * @return
     */
    private City[] findCities(String c1, String c2) {
        City[] c = { null, null };
        for (City city : this.citiesMap.keySet()) {
            if (city.getName().equals(c1)) {
                c[0] = city;
            }
            if (city.getName().equals(c2)) {
                c[1] = city;
            }
        }
        return c;
    }

    /**
     * The general function that will parse a new Attack object. It will: Add Attack
     * to the corresponding City, Manage if there is an outbreak (and possible
     * subsequent outbreaks), Manage if a City should be set to inactive
     * 
     * @param a Attack object to be parsed
     */
    public void attack(Attack a) {
        /*
         * Pseudo Code:
         * Parse the attack object "a" --> in Attack
         * Get the city
         * add attack to the city
         * if city has routes
         * add attack to the routes as well
         * redo the process for the routes as well
         * recursive method??
         */

        // Get the city
        String targetCity = a.getCity();

        // If the targetCity has routes
        // if()
        // Add the attack to the city?
        // if the gotten city is equal to its keyset, set that city as target
        for (City city : this.citiesMap.keySet()) {
            if (city.getName().compareToIgnoreCase(targetCity) == 0) {
                // found city representing attack source
                if (city.addAttack(a)) {
                    City[] cities = this.citiesMap.get(city);
                    for (City connectedCity : cities) {
                        Attack connectedAttack = new Attack(a.getType(), a.getDateTime(),
                                connectedCity.getName());
                        this.attack(connectedAttack);
                    }
                }

            }

        }

    }

    /**
     * Add a City object to the Graph object
     * 
     * @param c City object to be added
     */
    public void addCity(City c) {
        this.citiesMap.put(c, new City[1]);
    }

    /**
     * Add a City to the Graph object given a String(city name), boolean(if the city
     * has a firewall), and City.Point object
     * 
     * @param name Name of the city
     * @param fire If the city has a firewall or not
     * @param pos  Position of city
     */
    public void addCity(String name, boolean fire, City.Point pos) {
        City c1 = new City(name, fire, pos);
        this.addCity(c1);
    }

    /**
     * 
     * @param c1
     * @oaram c2
     */
    public void addRoute(String c1, String c2) {
        City[] c = this.findCities(c1, c2);
        this.citiesMap.put(c[0], this.extendArray(this.citiesMap.get(c[0]), c[1]));
        this.citiesMap.put(c[1], this.extendArray(this.citiesMap.get(c[1]), c[0]));
    }

    /**
     * Attempts find a path between two cities
     * 
     * @param c1
     * @param c2
     * @return
     */
    public String findPath(String c1, String c2) {
        // TODO: #3 Implement finding path between 2 cities
        City[] cit = this.findCities(c1, c2);
        if (cit[0].getCurrStatus() != City.Status.SAFE)
            return c1 + " is infected/offline";
        if (cit[1].getCurrStatus() != City.Status.SAFE)
            return c2 + " is infected/offline";
        for (City city : this.citiesMap.get(cit[0])) {
            if (city.equals(cit[1])) {
                return c1 + " is directly connected to " + c2;
            }
        }

        return "No path available between '" + c1 + "' and '" + c2 + "'";
    }

    /**
     * Queries the Graph for all City objects that have been infected
     * 
     * @return String of all cities that have been infected
     */
    public String isInfected() {
        String str = "";
        int count = 0;
        for (City city : this.citiesMap.keySet()) {
            if (city.getCurrStatus() != City.Status.SAFE) {
                count++;
                str += String.valueOf(count) + ' ' + city.getName() + '\n';
            }
        }
        return str;
    }

    /**
     * Quries the Graph for all City objects that have a Firewall, i.e.
     * city.firewall = true
     * 
     * @return String of all cities that have a firewall
     */
    public String hasFirewall() {
        String str = "";
        int count = 0;
        for (City city : this.citiesMap.keySet()) {
            if (city.getFirewall() == true) {
                count++;
                str += String.valueOf(count) + ' ' + city.getName() + '\n';
            }
        }
        return str;
    }

    /**
     * Queries the Graph for all City objects that have a firewall and have had an
     * attempted attack
     * 
     * @return String of a cities that have a firewall and have been attacked
     */
    public String hasAttackedFirewall() {
        String str = "";
        int count = 0;
        for (City city : this.citiesMap.keySet()) {
            if (city.getFirewall() == true && city.getAttacks().isEmpty() == false) {
                count++;
                str += String.valueOf(count) + ' ' + city.getName() + '\n';
            }
        }
        return str;
    }

    /**
     * Queries the Graph for all City objects that have had an outbreak
     * 
     * @return String of all the cities that have had an outbreak
     */
    public String outbreaks() {
        String str = "";
        int count = 0;
        for (City city : this.citiesMap.keySet()) {
            if (city.getCurrStatus() == City.Status.OUTBREAK) {
                count++;
                str += String.valueOf(count) + ' ' + city.getName() + '\n';
            }
        }
        return str;
    }

    /**
     * Queries the Graph for all City objects that have been set to Inactive
     * 
     * @return String of all the cities that have been set to Inactive
     */
    public String inactive() {
        String str = "";
        int count = 0;
        for (City city : this.citiesMap.keySet()) {
            if (city.getCurrStatus() == City.Status.OFFLINE) {
                count++;
                str += String.valueOf(count) + ' ' + city.getName() + '\n';
            }
        }
        return str;
    }

    public String toString() {
        String str = "";
        for (City city : this.citiesMap.keySet()) {
            str += city.toString() + "\n";
        }
        for (City city : this.citiesMap.keySet()) {
            str += city.getName() + ": " + this.printCityNames(this.citiesMap.get(city)) + "\n";
        }
        return str;
    }
}
