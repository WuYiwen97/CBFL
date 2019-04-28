package com.wuyiwen.IO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author wuyiwen
 * @date 2019.4.19
 * 读取各种文件
 * 最好不要在此类输出
 */
public class ReadFile {
    /**
     * 按行读取Matrix文件按行处理数据
     * @param fileName
     * @return List
     */
    public static List<String []> readMatrixFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        //List<String> stringlastCharList=new ArrayList<>();
        List<String []> stringList=new ArrayList<>();
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                //System.out.println("第" + line + "行：" +"（"+ tempString.substring(tempString.length() - 1 )+"）");
                //String aa=tempString.substring(tempString.length()-1);
                //stringlastCharList.add(aa);

                //按照空格分割
                String [] arrStrings=tempString.split(" ");
                stringList.add(arrStrings);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return stringList;
    }

    /**
     * 按行读取Spectra文件按行处理数据
     * @param fileName
     * @return List
     */
    public static List<String> readSpectraFileByLines(String fileName){
        File file = new File(fileName);
        BufferedReader reader = null;
        List<String> stringSpectraList=new ArrayList<>();
        try {
            System.out.println("Spectra文件以行为单位读取文件内容，一次读一整行：reading......");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                //System.out.println("第" + line + "行：" +"（"+ tempString+"）");
                stringSpectraList.add(tempString);
                line++;
            }
            //System.out.println(line);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return stringSpectraList;
    }

    /**
     *
     * 处理Mutants.log文件
     */
    public static List<String> readMutantsFileByLines(String fileName){
        File file = new File(fileName);
        BufferedReader reader = null;
        List<String> stringMutantsList=new ArrayList<>();
        try {
            System.out.println("Mutants.log文件以行为单位读取文件内容，一次读一整行：reading......");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                //System.out.println("第" + line + "行：" +"（"+ tempString+"）");
                stringMutantsList.add(tempString);
                line++;
            }
            //System.out.println(line);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return stringMutantsList;
    }

    /**
     *
     * 处理Killmap.csv文件
     */
    public static List<String> readKillmapFileByLines(String fileName){
        File file = new File(fileName);
        BufferedReader reader = null;
        List<String> stringKillmapList=new ArrayList<>();
        try {
            System.out.println("killmap.csv文件以行为单位读取文件内容，一次读一整行：reading......");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                //System.out.println("第" + line + "行：" +"（"+ tempString+"）");
                stringKillmapList.add(tempString);
                line++;
            }
            //System.out.println(line);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return stringKillmapList;
    }

    /**
     * 处理任何文件按行读取
     * @param fileName
     */
    public static void readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                System.out.println("第" + line + "行：" + tempString);
                line++;
            }
            System.out.print("line is ===="+(line-1));
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
}
