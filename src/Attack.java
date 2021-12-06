import java.util.Date;
import java.text.ParseException;

/**
 * Attack class
 *
 * @author Team MST
 * @version 0.1
 * @since 27/10/2021
 */

public class Attack {
    /** The Type of the attack */
    private Type type;
    /** The date and time of the attack, stored as a java.util.Date object */
    private Date dateTime;
    /** Name of the target city */
    private String city;

    /**
     * Basic constructor of Attack object. Mainly used for outbreaks
     * 
     * @param type     Type of attack
     * @param dateTime {@code Date} object of date and time of attack
     * @param city     String name of the target city
     * @throws ParseException Throws exception if parsing dateTime String fails
     */
    public Attack(Type type, Date dateTime, String city) {
        this.type = type;
        this.dateTime = dateTime;
        this.city = city;
    }

    /**
     * Basic constructor of Attack object. The{@code dateTime String} will be parsed
     * into a {@code Date} object. The {@code type String} will be parsed into a the
     * correct {@code Type enum}
     * 
     * @param type     String of the type of attack. (BLACK|BLUE|RED|YELLOW)
     * @param dateTime String of date and time of attack (yyyy-MM-dd HH:mm:ss)
     * @param city     String name of the target city
     * @throws ParseException Throws exception if parsing dateTime String fails
     */
    public Attack(String type, String dateTime, String city) throws ParseException {
        if (type.equals("BLACK"))
            this.type = Type.BLACK;
        if (type.equals("BLUE"))
            this.type = Type.BLUE;
        if (type.equals("RED"))
            this.type = Type.RED;
        if (type.equals("YELLOW"))
            this.type = Type.YELLOW;
        this.dateTime = toDate(dateTime);
        this.city = city;
    }

    /**
     * Parses a date string into a{@code java.util.Date} object.
     * 
     * @param dT String of date and time (yyyy-MM-dd HH:mm:ss)
     * @return {@code Date} object from string
     * @throws ParseException Throws exception if parse fails
     */
    private Date toDate(String dT) throws ParseException {
        return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dT);
    }

    /**
     * Translates a{@code Date} object to the{@code long} epoch time in
     * seconds(since 1970)
     * 
     * @param dT {@code Date} object to be translated
     * @return {@code long} epoch time
     */
    public long toEpoch() {
        return this.dateTime.getTime() / 1000;
    }

    /**
     * Get the number of seconds between this attack and{@code a}
     * 
     * @param a {@code Attack} object to be comapred with
     * @return long integer number of seconds
     */
    public long compare(Attack a) {
        return this.toEpoch() - a.toEpoch();
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Date getDateTime() {
        return this.dateTime;
    }

    public void setDate(String dateTime) throws ParseException {
        this.dateTime = toDate(dateTime);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Attack{" + type + ", " + this.city + ", " + dateTime + '\'' + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj instanceof Attack) {
            Attack a = (Attack) obj;
            return a.type == this.type && a.dateTime.equals(this.dateTime) && a.city.equals(this.city);
        }
        return false;
    }

    /** The types of attacks possible */
    public enum Type {
        BLACK, BLUE, RED, YELLOW
    }
}
