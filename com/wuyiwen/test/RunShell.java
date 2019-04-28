package com.wuyiwen.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RunShell {

    public  static final String RUN_GZOLTARS="/Users/wuyiwen/Documents/GraduationProject/fault-localization-data/gzoltar/run_gzoltar.sh";
    public static final String GZOLTARS_OUTPUT_PATH="/Users/wuyiwen/Documents/GraduationProject/ProjectCode/src/com/wuyiwen/out/";
    public static void main(String[] args){
        try {
            Process ps = Runtime.getRuntime().exec(RUN_GZOLTARS+" "+"Lang"+" "+"7"+" "+GZOLTARS_OUTPUT_PATH+" "+"developer");
            ps.waitFor();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dorun_gzoltars(){
        try {
            String shpath="/Users/wuyiwen/Documents/GraduationProject/fault-localization-data/gzoltar/run_gzoltar.sh";
            Process ps = Runtime.getRuntime().exec(shpath+" "+"Chart"+" "+"3"+" "+"/Users/wuyiwen/Documents/GraduationProject/ProjectCode/src/com/wuyiwen/out/"+" "+"developer");
            ps.waitFor();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}