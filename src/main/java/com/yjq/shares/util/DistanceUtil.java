package com.yjq.shares.util;

import java.math.BigDecimal;

public class DistanceUtil {
    //地球赤道半径(km)
    private final static double EARTH_RADIUS = 6378.137;

    /**
     * 求两经纬度距离(google maps源码中)
     *
     * @param lon1 第一点的经度
     * @param lat1 第一点的纬度
     * @param lon2 第二点的经度
     * @param lat2 第二点的纬度
     * @return 两点距离，单位km
     */
    public static double getDistance(double lon1, double lat1, double lon2, double lat2) {
        double radLat1 = lat1 * (Math.PI / 180.0);
        double radLat2 = lat2 * (Math.PI / 180.0);
        double a = radLat1 - radLat2;
        double b = lon1 * (Math.PI / 180.0) - lon2 * (Math.PI / 180.0);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));

        return new BigDecimal(s * EARTH_RADIUS).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    public static void main(String[] args) {
        String lon_lat1 = "106.149845,38.492309";
        String lon_lat2 = "106.18999700,38.49665500";//4.67242
        String[] lls = lon_lat1.split(",");
        String[] lls2 = lon_lat2.split(",");

        double distance = getDistance(Double.valueOf(lls[0]), Double.valueOf(lls[1]), Double.valueOf(lls2[0]), Double.valueOf(lls2[1]));
        System.out.println("距离：" + distance + "km");
    }

}
