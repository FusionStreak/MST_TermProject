import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Graph {
    private Map<City, List<City>> graph;

    void addCity(String name, Boolean firewall, float x, float y) {
        graph.putIfAbsent(new City(name, firewall, new City.Point(x, y)), new ArrayList<City>());
    }

    
}
