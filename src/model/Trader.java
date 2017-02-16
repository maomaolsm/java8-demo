package model;

/**
 * Created by maomao on 16/12/29.
 * 5.5.1 交易员
 */
public class Trader {
    private final String name;
    private final String city;

    public Trader(String n, String c) {
        this.name = n;
        this.city = c;
    }

    public String getName() {
        return this.name;
    }

    public String getCity() {
        return this.city;
    }

    public String toString() {
        return "model.Trader : " + this.name + " in " + this.city;
    }
}
