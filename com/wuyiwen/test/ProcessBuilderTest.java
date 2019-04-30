package com.wuyiwen.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;

import static com.wuyiwen.test.RunShell.FLDPATH;


public class ProcessBuilderTest {

    /**
     * 功能：执行外部命令
     * @param
     * @return 执行结果
     */
    public static String test(String cmd) throws Exception {
        if (StringUtils.isEmpty(cmd)) {
            return null;
        }
        //使用"sh -c 命令字符串"的方式解决管道和重定向的问题
        List<String> cmds = new LinkedList<String>();
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
        return result;
    }


    public static String killmaptest(String cmd) throws Exception{
        if (StringUtils.isEmpty(cmd)) {
            return null;
        }
        //使用"sh -c 命令字符串"的方式解决管道和重定向的问题
        List<String> cmds = new LinkedList<String>();
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
        return result;
    }
    public static void main(String[] args){
        try {
            String aaa=FLDPATH+"killmap/scripts/generate-matrix.sh Lang 1 /tmp/Lang-1 "+FLDPATH+"killmap/out/Lang-1-mutants.log >"+FLDPATH+"killmap/out/lang-1-err.txt";
            String  a=ProcessBuilderTest.killmaptest(aaa);
            System.out.println(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
