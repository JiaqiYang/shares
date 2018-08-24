package com.yjq.shares.util;

import java.math.BigDecimal;

public class DistanceUtil {
    //地球赤道半径(km)
    private final static double EARTH_RADIUS = 6378.137;

    private static final double x_pi = 3.14159265358979324 * 3000.0 / 180.0;

    /**
     * 转化为弧度(rad)
     *
     * @param d
     * @return
     */
    public static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 求两经纬度距离(google maps源码中)
     *
     * @param lon1 第一点的经度
     * @param lat1 第一点的纬度
     * @param lon2 第二点的经度
     * @param lat2 第二点的纬度
     * @return 两点距离，单位km
     */
    public static double GetDistance(double lon1, double lat1, double lon2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;

        return new   BigDecimal(s).setScale(4,   BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 谷歌经纬 转 百度 坐标
     * @param gg_lat
     * @param gg_lon
     * @return
     */
    public static String google_bd_encrypt(double gg_lat, double gg_lon){
        double x = gg_lon, y = gg_lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
        double bd_lon = z * Math.cos(theta) + 0.0065;
        double bd_lat = z * Math.sin(theta) + 0.006;
        return bd_lat+","+bd_lon;
    }

    /**
     * 百度坐标 转 谷歌 经纬
     * @param bd_lat
     * @param bd_lon
     * @return
     */
    public static  String bd_google_encrypt(double bd_lat, double bd_lon){
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
        double theta =Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
        double gg_lon = z * Math.cos(theta);
        double gg_lat = z * Math.sin(theta);
        return gg_lat+","+gg_lon;
    }



    public static void main(String[] args) {
        String lon_lat1 = "116.39,00";
        String lon_lat2 = "120.67242,40";//4.67242
        String[] lls =lon_lat1.split(",");
        String[] lls2 = lon_lat2.split(",");

        String xy  = google_bd_encrypt(Double.valueOf(lls[0]),Double.valueOf(lls[1]));
        System.out.println(xy);
//        double distance = GetDistance(Double.valueOf(lls[0]),Double.valueOf(lls[1]),Double.valueOf(lls[0])+4.67242,Double.valueOf(lls2[1]));
//        System.out.println("距离："+distance+"km");
    }

}
