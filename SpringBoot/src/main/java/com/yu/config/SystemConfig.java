package com.yu.config;

import java.time.LocalTime;

public class SystemConfig {
    public static LocalTime AccessControlTime = LocalTime.of(23,0,0);

    public static boolean isAccessControlTime() {
        return LocalTime.now().isAfter(AccessControlTime);
    }
    public static String getTime(){
        return AccessControlTime.toString();
    }
}
