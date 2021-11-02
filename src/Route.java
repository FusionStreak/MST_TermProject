import java.util.Arrays;

/**
 * Route class
 *
 * @author Team MST
 * @version 0.1
 * @since 27/10/2021
 */

public class Route {
    private City[] cities = new City[2];

    public Route(City city1, City city2) {
        this.cities[0] = city1;
        this.cities[1] = city2;
    }

    public Boolean isCityInRoute(City city) {
        if (Arrays.asList(cities).contains(city)){
            return true;
        }
        return false;
    }
}
