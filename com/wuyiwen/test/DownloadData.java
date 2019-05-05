package com.wuyiwen.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * wget --recursive --no-parent http://fault-localization.cs.washington.edu/data/Time/3
 */
public class DownloadData {



    public static final String downloadpath="/Users/wuyiwen/Documents/GraduationProject/fldata";

    public static void main(String[] args){
        try {
            startDownload("Closure",22);
            //startDownload("Lang",22);
            //startDownload("Math",22);
            //startDownload("Time",22);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void startDownload(String projectid,int bugid) throws Exception {
        for (int i=1;i<=bugid;i++){
            Download(projectid,i);
        }

    }

    public static void Download(String projectid,int bugid) throws Exception {
        StringBuffer msgDL=new StringBuffer("");
        msgDL.append("wget --recursive --no-parent http://fault-localization.cs.washington.edu/data/");
        msgDL.append(projectid+"/"+bugid);
        String cmd=msgDL.toString();
        //使用"sh -c 命令字符串"的方式解决管道和重定向的问题
        List<String> cmds = new LinkedList<String>();
        System.out.println(cmd);
        cmds.add("sh");
        cmds.add("-c");
        cmds.add(cmd);
        ProcessBuilder pb = new ProcessBuilder(cmds);
        //重定向到标准输出
        pb.redirectErrorStream(true);
        Process p = pb.start();
        p.waitFor(3, TimeUnit.SECONDS);
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        String result = sb.toString();
        System.out.println(result);

    }
}
