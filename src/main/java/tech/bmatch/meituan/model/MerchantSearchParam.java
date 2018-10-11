package tech.bmatch.meituan.model;
/**
 * 搜索对象的类，包含搜索商家的名字、自己的经度纬度
 * @author lcy
 * @data 2018年10月11日 18:16:47*/

public class MerchantSearchParam {
    private String name;

    private double lon;
    private double lat;

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
}
