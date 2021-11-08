import java.util.ArrayList;

/**
 * Graph class
 *
 * @author Team MST
 * @version 0.1
 * @since 27/10/2021
 */

public class Graph {
    /** ArrayList<> of all the Route(connections) objects in the Graph */
    private ArrayList<Route> routes = new ArrayList<Route>();
    /** ArrayList<> of all the City objects in the Graph */
    private ArrayList<City> cities = new ArrayList<City>();

    Graph() {

    }

    /**
     * Constructs graph based on an input file
     *
     * @param file path to graph file
     */
    Graph(String file) {

    }

    /**
     * The general function that will parse a new Attack object. It will: Add Attack
     * to the corresponding City, Manage if there is an outbreak (and possible
     * subsequent outbreaks), Manage if a City should be set to inactive
     * 
     * @param a Attack object to be parsed
     */
    public void attack(Attack a) {

    }

    /**
     * Add a City object to the Graph object
     * 
     * @param c City object to be added
     */
    public void addCity(City c) {
        this.cities.add(c);
    }

    /**
     * Add a City to the Graph object given a String(city name) and Boolean(if the
     * city has a firewall)
     * 
     * @param name Name of the city
     * @param fire If the city has a firewall or not
     */
    public void addCity(String name, Boolean fire) {
        addCity(new City(name, fire));
    }

    /**
     * Add a Route object to the Graph
     * 
     * @param r ROute object to be added
     */
    public void addRoute(Route r) {
        this.routes.add(r);
    }

    /**
     * Attempts find a path between two cities
     * 
     * @param c1
     * @param c2
     * @return
     */
    public String hasPath(String c1, String c2) {
        return "";
    }

    /**
     * Queries the Graph for all City objects that have been infected
     * 
     * @return String of all cities that have been infected
     */
    public String isInfected() {
        return "";
    }

    /**
     * Quries the Graph for all City objects that have a Firewall, i.e.
     * city.firewall = true
     * 
     * @return String of all cities that have a firewall
     */
    public String hasFirewall() {
        return "";
    }

    /**
     * Queries the Graph for all City objects that have a firewall and have had an
     * attempted attack
     * 
     * @return String of a cities that have a firewall and have been attacked
     */
    public String hasAttackedFirewall() {
        return "";
    }

    /**
     * Queries the Graph for all City objects that have had an outbreak
     * 
     * @return String of all the cities that have had an outbreak
     */
    public String outbreaks() {
        return "";
    }

    /**
     * Queries the Graph for all City objects that have been set to Inactive
     * 
     * @return String of all the cities that have been set to Inactive
     */
    public String inactive() {
        return "";
    }

    public String toString() {
        return "";
    }
}
