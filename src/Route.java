/**
 * Route class
 *
 * @author Team MST
 * @version 0.1
 * @since 27/10/2021
 */

public class Route {
    private City c1;
    private City c2;

    public Route(City city1, City city2) {
        this.c1 = city1;
        this.c2 = city2;
    }

    public City getC1() {
        return c1;
    }

    public void setC1(City c1) {
        this.c1 = c1;
    }

    public City getC2() {
        return c2;
    }

    public void setC2(City c2) {
        this.c2 = c2;
    }
}
