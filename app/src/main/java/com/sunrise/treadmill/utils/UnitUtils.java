package com.sunrise.treadmill.utils;

/**
 * Created by ChuHui on 2017/10/30.
 */

public class UnitUtils {
    private static final float A1 = 0.6213f;
    private static final float A2 = 1.6093f;
    private static final float A3 = 2.2046f;
    private static final float A4 = 0.4535f;

    /**
     * 公里转英里
     *
     * @return
     */
    public static float km2mile(float km) {
        return km * A1;
    }

    /**
     * 公里转英里
     *
     * @return
     */
    public static float km2mile(String km) {
        try {
            float f = Float.valueOf(km);
            return km2mile(f);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 英里转公里
     *
     * @return
     */
    public static float mile2km(float mile) {
        return mile * A2;
    }

    /**
     * 英里转公里
     *
     * @return
     */
    public static float mile2km(String mile) {
        try {
            float f = Float.valueOf(mile);
            return mile2km(f);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 千克转磅数
     *
     * @return
     */
    public static float kg2lb(float kg) {
        return kg * A3;
    }

    /**
     * 千克转磅数
     *
     * @return
     */
    public static float kg2lb(String kg) {
        try {
            float f = Float.valueOf(kg);
            return kg2lb(f);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 磅数转千克
     *
     * @return
     */
    public static float lb2kg(float lb) {
        return lb * A4;
    }

    /**
     * 磅数转千克
     *
     * @return
     */
    public static float lb2kg(String lb) {
        try {
            float f = Float.valueOf(lb);
            return lb2kg(f);
        } catch (Exception e) {
            return -1;
        }
    }
}
