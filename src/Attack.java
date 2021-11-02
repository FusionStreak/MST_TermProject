/**
 * Attack class
 *
 * @author Team MST
 * @version 0.1
 * @since 27/10/2021
 */

public class Attack {
    private Type type;
    private String date;
    private String time;
    private String city;

    public Attack(Type type, String date, String time, String city) {
        this.type = type;
        this.date = date;
        this.time = time;
        this.city = city;
    }

    public Attack(String type, String date, String time, String city) {
        if (type.equals("BLACK")) this.type = Type.BLACK;
        if (type.equals("BLUE")) this.type = Type.BLUE;
        if (type.equals("RED")) this.type = Type.RED;
        if (type.equals("YELLOW")) this.type = Type.YELLOW;
        this.date = date;
        this.time = time;
        this.city = city;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDateTime() {
        return date;
    }

    public void setDateTime(String date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Attack{" +
                "type=" + type +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public enum Type {
        BLACK,
        BLUE,
        RED,
        YELLOW
    }
}
