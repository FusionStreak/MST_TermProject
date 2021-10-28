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

    public void addAttack(Attack a) {
        this.attacks.add(a);
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

    public void alert(Attack.Type type) {
        System.out.println(this.name + " has been attacked by " + type + " twice within 2 minutes!");
    }

    @Override
    public String toString() {
        return name +
                ":\nfirewall= " + firewall +
                "\nattacks= " + attacks.size() +
                "\nposition= " + position +
                "\ncurrStatus= " + currStatus;
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

        @Override
        public String toString() {
            return "Point{" + xPos + ", " + yPos + '}';
        }
    }
}
