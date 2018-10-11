package tech.bmatch.meituan.model;

import java.util.List;

/**
 * 商家类 有id、名字、经度、纬度、距离、菜品
 * @author lcy
 * @data 2018年10月11日 18:13:24
 */
public class Merchant {
    private String id;
    private String name;
    private double lon;
    private double lat;
    private double distance;
    private List<Dishes> dishes;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public List<Dishes> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dishes> dishes) {
        this.dishes = dishes;
    }
}
