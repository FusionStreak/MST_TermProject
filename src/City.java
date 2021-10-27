import java.util.ArrayList;

public class City {
    private String name = "";
    private Boolean firewall = false;
    private ArrayList<Attack> attacks = new ArrayList<>();
    private ArrayList<City> neighbours;
    private Point position = new Point(0, 0);
    private Status currStatus = Status.SAFE;

    public City(String name, Boolean firewall, Point position) {
        this.name = name;
        this.firewall = firewall;
        this.position = position;
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
