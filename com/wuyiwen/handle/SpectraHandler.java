package com.wuyiwen.handle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 处理spectra
 */
public class SpectraHandler {

    /**
     *  读取文件 必须把每一行转换成对象 继续处理
     * @param stringListSpectra
     * @return
     */
    public static List<Spectra> fileInSpectra(List<String> stringListSpectra){
        List<Spectra> spectraList=new ArrayList<>();
        //从 000000开始
        for (int index=0;index<stringListSpectra.size();index++){
            String[] strings=stringListSpectra.get(index).split("#");
            Spectra spectra=new Spectra();
            spectra.setId(index+1);
            spectra.setFilename(strings[0]);
            spectra.setLine(Integer.parseInt(strings[1]));
            spectra.setSuspicious(0.0);
            spectraList.add(spectra);
        }
        return spectraList;


    }

    /**
     *  得到想要变异的行 必须找到可疑度高的行 是哪一行？？？
     * @param spectraList
     * @param suspiciousList
     * @param  line ------行数
     * @return
     */
    public static List<Spectra> ochiaiToSpectra(List<Spectra> spectraList, List<Suspicious> suspiciousList,int line){
        List<Spectra> spectraList1=new ArrayList<>();
        for(int index=0;index<line;index++){
            int suspLine=suspiciousList.get(index).getId();
            for(int indexTwo=0;indexTwo<spectraList.size();indexTwo++){
                if (suspLine==spectraList.get(indexTwo).getId()){
                    spectraList1.add(spectraList.get(indexTwo));
                    }
                }
            }

        return spectraList1;

    }


    public  static List<Spectra> getSpectraFinall(List<Mutants> mutantsList,List<Spectra> spectraList ){
        for (int index=0;index<spectraList.size();index++){
            for (int index2=0;index2<mutantsList.size();index2++){
                Spectra spectraA=spectraList.get(index);
                Mutants mutantsA=mutantsList.get(index2);
                if (spectraA.getFilename().equals(mutantsA.getFilename()) && spectraA.getLine().equals(mutantsA.getCodeline())){
                    if (spectraA.getSuspicious()<mutantsA.getSuspicion()){
                        spectraA.setSuspicious(mutantsA.getSuspicion());
                    }
                }

            }
        }

        return spectraList;
    }

}
