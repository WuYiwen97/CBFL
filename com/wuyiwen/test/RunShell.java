package com.wuyiwen.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RunShell {

    public  static final String RUN_GZOLTARS="/Users/wuyiwen/Documents/GraduationProject/fault-localization-data/gzoltar/run_gzoltar.sh";
    public static final String GZOLTARS_OUTPUT_PATH="/Users/wuyiwen/Documents/GraduationProject/ProjectCode/src/com/wuyiwen/out/";


    public static final String FLDPATH="/Users/wuyiwen/Documents/GraduationProject/fault-localization-data/";
    public static void main(String[] args){
        //dorun_killmap();
        dorun_gzoltars();
    }

    public static void dorun_gzoltars(){
        try {
            String shpath="/Users/wuyiwen/Documents/GraduationProject/fault-localization-data/gzoltar/run_gzoltar.sh";
            Process ps = Runtime.getRuntime().exec(shpath+" "+"Lang"+" "+"1"+" "+"/Users/wuyiwen/Documents/GraduationProject/ProjectCode/src/com/wuyiwen/out/"+" "+"developer");
            ps.waitFor();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ./killmap/scripts/generate-matrix.sh Lang 1 /tmp/Lang-1 Lang-1-mutants.log >lang-1-err.txt
    public static void dorun_killmap(){
        try {
            String killmapPath=FLDPATH+"killmap/scripts/generate-matrix.sh";
            Process ps1=Runtime.getRuntime().exec(killmapPath+" "+"Lang"+" "+"1"+" "+"/tmp/Lang-1"+" "+FLDPATH+"/killmap/out/Lang-1-mutants.log");

            ps1.waitFor();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}