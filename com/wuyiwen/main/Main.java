package com.wuyiwen.main;

import com.wuyiwen.IO.ReadFile;
import com.wuyiwen.SBFL.Dstar;
import com.wuyiwen.SBFL.Ochiai;
import com.wuyiwen.SMFL.Metallaxis;
import com.wuyiwen.handle.*;

import java.util.*;

import static com.wuyiwen.test.RunShell.GZOLTARS_OUTPUT_PATH;
import static com.wuyiwen.test.RunShell.dorun_gzoltars;

//97
public class Main {

    public static final String DOWNLOAD_PATH="/Users/wuyiwen/Documents/GraduationProject/fldata/fault-localization.cs.washington.edu/data/Chart/3/";

    public static void main(String args[]){

       // dorun_gzoltars();




        List<String []> stringListMatrix=new ArrayList<>();
        //按行读取
        String workpathMatrix=DOWNLOAD_PATH+"gzoltars/Chart/3/matrix";
        stringListMatrix=MatrixHandler.readMatrixFileByLines(workpathMatrix);
        System.out.println("size is === it means ???测试用例    "+stringListMatrix.size());
        String[] first=stringListMatrix.get(1);
        //多少行程序  -1 最后是 +/- 号 需要删除
        System.out.println("row is ====it means ???多少行程序    "+(first.length-1));
        /*for (String a:first){
           System.out.print(a+"\t");
        }*/

        //转换成矩阵
        String matrix[][]=MatrixHandler.changeToMatrix(stringListMatrix);

        /*Matrix.printm(matrix);*/

        //SBFl处理矩阵计算可疑度 Ochiai or Dstar
        //List<Double> resultList = Ochiai.calculateOchiai(matrix);
        List<Double> resultList= Dstar.calculateDstar(matrix);

        //可疑度对象
        List<Suspicious> suspiciousList=SuspiciousHandler.listToObject(resultList);


        //给出前50最高可疑度的代码行  排序
        // 从小到大 怀疑度 增加
        List<Suspicious> suspiciousListAfterSort= LineSort.sortLine(suspiciousList);
        /*for(Suspicious suspicious:suspiciousListAfterSort){
            System.out.println("line="+suspicious.getId()+"suspicious"+suspicious.getResult());
        }*/

        //得到spectralist
        String workpathSpectra=DOWNLOAD_PATH+"gzoltars/Chart/3/spectra";
        List<String> stringListSpectra=ReadFile.readSpectraFileByLines(workpathSpectra);
        List<Spectra> spectraList= SpectraHandler.fileInSpectra(stringListSpectra);

        // 在spectra文件里面找到对应的行   VIC
        /**
         * @param suspiciousListAfterSort spectraList
         * @return 返回变异的行+文件
         */
        List<Spectra> spectraID=SpectraHandler.ochiaiToSpectra(spectraList,suspiciousListAfterSort,10);
        for (Spectra spectra:spectraID){
            System.out.println("需要变异的是"+spectra.getFilename()+"\t"+spectra.getLine());
        }


        //TODO EXAM评分？？？

        // spectraID 去找变异的
        // 对应行找变异 没找到说明突变体没有影响到测试用例
        //mutants
        List<String> stringListMutants=ReadFile.readMutantsFileByLines(DOWNLOAD_PATH+"killmaps/Chart/3/mutants.log");
        List<Mutants> mutantsList=MutantsHandler.fileToMutants(stringListMutants);

        /**
         * 行对应变异 要找到变异的ID
         */
        System.out.println("===========");
        List<Mutants> mutantsIDList=MutantsHandler.spectraToMutants(spectraID,mutantsList);
        System.out.println(mutantsIDList.size());
        for (Mutants a:mutantsIDList){
            System.out.println(a.getID()+"\t"+a.getFilename()+"\t"+a.getCodeline());
        }


        //TODO killmap
        List<String> stringListKillmap=ReadFile.readKillmapFileByLines("/Users/wuyiwen/Documents/GraduationProject/fldata/fault-localization.cs.washington.edu/data/Chart/3/killmaps/Chart/3/killmap.csv");
        List<Killmap> killmapList=KillmapHandler.fileToKillmap(stringListKillmap);




        //TODO killmaplist 和 mustantsIDList 一起
        List<Mutants> mutantsSuspiciousList= Metallaxis.calculateMetallaxis(killmapList,mutantsIDList);

        for (Mutants a:mutantsSuspiciousList){
            System.out.println(a.getID()+"\t"+a.getFilename()+"\t"+a.getCodeline()+"\t"+a.getSuspicion());
        }

        System.out.println("sort mutants suspicious");
        List<Mutants> mutantsAfterSort = LineSort.sortMutants(mutantsSuspiciousList);
        for (Mutants a:mutantsAfterSort){
            System.out.println(a.getID()+"\t"+a.getFilename()+"\t"+a.getCodeline()+"\t"+a.getSuspicion());
        }

        //变异体对应到行
        List<Spectra> spectraFinall=SpectraHandler.getSpectraFinall(mutantsAfterSort,spectraID);
        List<Spectra> spectrasFinallSort=LineSort.sortSpectra(spectraFinall);
        for (Spectra spectra:spectrasFinallSort){
            System.out.println("变异后的排行"+spectra.getFilename()+"\t"+spectra.getLine());
        }

    }






}

