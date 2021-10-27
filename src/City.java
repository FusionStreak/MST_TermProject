import java.util.ArrayList;

/**
 * City class
 *
 * @author Team MST
 * @version 0.1
 * @since 27/10/2021
 */

public class City {
    private String name;
    private Boolean firewall;
    private ArrayList<Attack> attacks = new ArrayList<>();
    private ArrayList<City> neighbours;
    private Point position = new Point(0, 0);
    private Status currStatus = Status.SAFE;

    public City(String name, Boolean firewall) {
        this.name = name;
        this.firewall = firewall;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getFirewall() {
        return firewall;
    }

    public void setFirewall(Boolean firewall) {
        this.firewall = firewall;
    }

    public ArrayList<Attack> getAttacks() {
        return attacks;
    }

    public void setAttacks(ArrayList<Attack> attacks) {
        this.attacks = attacks;
    }

    public ArrayList<City> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(ArrayList<City> neighbours) {
        this.neighbours = neighbours;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Status getCurrStatus() {
        return currStatus;
    }

    public void setCurrStatus(Status currStatus) {
        this.currStatus = currStatus;
    }

    private enum Status {
        OFFLINE,
        SAFE,
        UNSAFE,
    }

    public static class Point {
        float xPos;
        float yPos;

        public Point(float x, float y) {
            this.xPos = x;
            this.yPos = y;
        }
    }
}
