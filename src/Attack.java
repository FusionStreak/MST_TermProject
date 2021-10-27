/**
 * Attack class
 *
 * @author Team MST
 * @version 0.1
 * @since 27/10/2021
 */

public class Attack {
    private Type type;
    private String dateTime;
    private String city;

    public Attack(Type type, String dateTime, String city) {
        this.type = type;
        this.dateTime = dateTime;
        this.city = city;
    }

    public Attack(String type, String dateTime, String city) {
        if (type.equals("BLACK")) new Attack(Type.BLACK, dateTime, city);
        if (type.equals("BLUE")) new Attack(Type.BLUE, dateTime, city);
        if (type.equals("RED")) new Attack(Type.RED, dateTime, city);
        if (type.equals("YELLOW")) new Attack(Type.YELLOW, dateTime, city);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public enum Type {
        BLACK,
        BLUE,
        RED,
        YELLOW
    }
}
