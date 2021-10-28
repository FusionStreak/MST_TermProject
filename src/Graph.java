import java.util.ArrayList;

/**
 * Graph class
 *
 * @author Team MST
 * @version 0.1
 * @since 27/10/2021
 */

public class Graph {
    private ArrayList<Route> routes = new ArrayList<Route>();
    private ArrayList<City> cities = new ArrayList<City>();
    private int nb_cities = 0;

    Graph() {

    }

    /**
     * Constructs graph based on an input file
     *
     * @param file path to graph file
     */
    Graph(String file) {

    }

    public void attack(Attack a) {

    }

    public void addCity(City c) {
        this.cities.add(c);
        this.nb_cities++;
    }

    public void addCity(String name, Boolean fire) {
        addCity(new City(name, fire));
    }

    public void addRoute(Route r) {
        this.routes.add(r);
    }

    public String infectedNodes() {
        return "";
    }

    public String firewall() {
        return "";
    }

    public String firewallAttacked() {
        return "";
    }

    public String outbreaks() {
        return "";
    }

    public String inactive() {
        return "";
    }

    public String toString() {
        return "";
    }
}
