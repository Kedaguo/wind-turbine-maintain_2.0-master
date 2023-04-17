package com.ruoyi.quartz.util;

public class DistanceCalculator {
    private static final double EARTH_RADIUS = 6371.0; // 地球半径，单位为公里

    /**
     * 计算两点间距离
     *
     * @param lat1 第一个点的纬度
     * @param lon1 第一个点的经度
     * @param lat2 第二个点的纬度
     * @param lon2 第二个点的经度
     * @return 两点间距离，单位为公里
     *
     *
     * 大圆距离公式(Haversine formula)
     * a = sin²(Δlat/2) + cos(lat1) * cos(lat2) * sin²(Δlong/2)
     * c = 2 * atan2( √a, √(1−a) )
     * d = R * c
     */
    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        double deltaLat = Math.toRadians(lat2 - lat1);
        double deltaLon = Math.toRadians(lon2 - lon1);
        double a = Math.pow(Math.sin(deltaLat / 2), 2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.pow(Math.sin(deltaLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS * c;
        return distance;
    }
}
