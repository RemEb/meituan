package tech.bmatch.meituan.util;
/**
 * 计算两点间的距离
 * @author lcy
 * @data 2018年10月11日
 * */
public class DistanceUtil {
    private static double rad(double d) {
        return d * Math.PI / 180;
    }

    public static double getDistance(double lon1, double lat1, double lon2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * 6371000;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    public static void main(String[] args) {
        double lon1 =120.026208;
        double lat1 =30.279212;
        double lon2 =111.011091;
        double lat2 =30.290126;
        double dist;
        dist = getDistance(lon1,lat1,lon2,lat2);
        System.out.println("两点相距："+dist+"米");
    }
}
