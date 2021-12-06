import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * City class
 *
 * @author Team MST
 * @version 0.1
 * @since 27/10/2021
 */

public class City {
    private String name;
    private boolean firewall;
    private ArrayList<Attack> attacks = new ArrayList<>();
    private final Set<Attack.Type> attackTypes = new HashSet<>();
    private Point position = new Point(0, 0);
    private Status currStatus = Status.SAFE;

    public City(String name, boolean firewall, Point point) {
        this.name = name;
        this.firewall = firewall;
        this.position = point;
    }

    private boolean putDown() {
        return this.attackTypes.size() >= 2 && this.attacks.size() >= 6;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getFirewall() {
        return firewall;
    }

    public void setFirewall(boolean firewall) {
        this.firewall = firewall;
    }

    public ArrayList<Attack> getAttacks() {
        return attacks;
    }

    public void setAttacks(ArrayList<Attack> attacks) {
        this.attacks = attacks;
    }

    /**
     * Add{@code Attack a} to city. Parses through attacks in this city.
     * 
     * If 2 or more of the same type as{@code a} within 2 minutes an alert is sent
     * to{@code System.out}.
     * 
     * If 4 or more of the same type as{@code a} within 4 minutes, return true for
     * outbreak
     * 
     * Determines if this city should be put offline, 6 or more attacks of at least
     * 2 types
     * 
     * Defaults to{@code false} if {@code currStatus == OFFLINE}
     * 
     * @param a attack to be added
     * @return {@code true} if outbreak,{@code false} otherwise
     */
    public boolean addAttack(Attack a) {
        this.attacks.add(a);
        if (this.currStatus == Status.OFFLINE || this.firewall == true)
            return false;
        this.attackTypes.add(a.getType());
        int within4 = 0;
        int within2 = 0;
        for (Attack attack : this.attacks) {
            if (attack.getType() == a.getType()) {
                long comp = a.compare(attack);
                if (comp <= 120) {
                    within2++;
                }
                if (comp <= 240) {
                    within4++;
                }
            }
            if (within4 >= 4) {
                this.currStatus = Status.OUTBREAK;
                return true;
            }
        }
        if (this.currStatus != Status.OUTBREAK) {
            this.currStatus = Status.UNSAFE;
        }
        if (within2 >= 2)
            this.alert(a.getType());
        if (this.putDown())
            this.currStatus = Status.OFFLINE;
        return false;
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

    private void alert(Attack.Type type) {
        System.out.println(this.name + " has been attacked by " + type + " multiple times within 2 minutes!");
    }

    @Override
    public String toString() {
        return name + ":\nfirewall= " + firewall + "\nattacks= " + attacks.size() + "\nposition= " + position
                + "\ncurrStatus= " + currStatus;
    }

    public enum Status {
        OFFLINE, SAFE, UNSAFE, OUTBREAK;
    }

    public static class Point {
        float xPos;
        float yPos;

        public Point(float x, float y) {
            this.xPos = x;
            this.yPos = y;
        }

        public Point(String pos) {
            String[] p = pos.replace("(", "").replace(")", "").split("[,][ ]");
            System.out.println(pos);
            this.xPos = Float.parseFloat(p[0]);
            this.yPos = Float.parseFloat(p[1]);
        }

        @Override
        public String toString() {
            return "Point{" + xPos + ", " + yPos + '}';
        }
    }
}
