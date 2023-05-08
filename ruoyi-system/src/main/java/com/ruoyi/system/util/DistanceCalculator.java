package com.ruoyi.system.util;

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
    public static String Location(double lat1, double lon1, double lat2, double lon2, double speed, Double time){
        // 计算方向角度
        double y = Math.sin(lon2-lon1) * Math.cos(lat2);
        double x = Math.cos(lat1)*Math.sin(lat2) - Math.sin(lat1)*Math.cos(lat2)*Math.cos(lon2-lon1);
        double direction = (Math.toDegrees(Math.atan2(y, x)) + 360) % 360;
        double distance = speed * time;
        double directionTnDistance = Math.toRadians(direction);
        double newLon = distance * Math.sin(directionTnDistance);
        double newLat = distance * Math.cos(directionTnDistance);
        return newLon + "," + newLat;
    }
//    public static List<double[]> calculateCoordinates(double startLatitude, double startLongitude, double endLatitude,
//                                                      double endLongitude, double distance, double interval) {
//        List<double[]> coordinates = new ArrayList<>();
//        double[] start = { Math.toRadians(startLatitude), Math.toRadians(startLongitude) };
//        double[] end = { Math.toRadians(endLatitude), Math.toRadians(endLongitude) };
//        double delta = distance / EARTH_RADIUS;
//        double heading = calculateHeading(startLatitude, startLongitude, endLatitude, endLongitude);
//        double intervalDistance = interval / EARTH_RADIUS;
//        double bearing = Math.toRadians(heading);
//        double lat1 = start[0];
//        double lng1 = start[1];
//        while (true) {
//            double lat2 = Math.asin(Math.sin(lat1) * Math.cos(intervalDistance) + Math.cos(lat1) * Math.sin(intervalDistance) * Math.cos(bearing));
//            double lng2 = lng1 + Math.atan2(Math.sin(bearing) * Math.sin(intervalDistance) * Math.cos(lat1),
//                    Math.cos(intervalDistance) - Math.sin(lat1) * Math.sin(lat2));
//            coordinates.add(new double[] { Math.toDegrees(lat2), Math.toDegrees(lng2) });
//            if (distance(startLatitude, startLongitude, Math.toDegrees(lat2), Math.toDegrees(lng2)) >= distance) {
//                break;
//            }
//            lat1 = lat2;
//            lng1 = lng2;
//        }
//        return coordinates;
//    }

}
