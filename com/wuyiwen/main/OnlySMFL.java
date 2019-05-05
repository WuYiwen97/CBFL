package com.wuyiwen.main;

import com.wuyiwen.IO.ReadFile;
import com.wuyiwen.SMFL.Metallaxis;
import com.wuyiwen.handle.*;

import java.util.List;

import static com.wuyiwen.main.Main.DOWNLOAD_PATH;
import static com.wuyiwen.main.Main.FLDPATH;

public class OnlySMFL {
    public static void main(String[] args){


        //得到spectralist
        String workpathSpectra=FLDPATH+"gzoltar/out/gzoltars/Lang/3/spectra";
        List<String> stringListSpectra= ReadFile.readSpectraFileByLines(workpathSpectra);
        List<Spectra> spectraList= SpectraHandler.fileInSpectra(stringListSpectra);


        //mutants
        List<String> stringListMutants=ReadFile.readMutantsFileByLines(DOWNLOAD_PATH+"killmaps/Lang/3/mutants.log");
        List<Mutants> mutantsList= MutantsHandler.fileToMutants(stringListMutants);

        //killmap
        List<String> stringListKillmap=ReadFile.readKillmapFileByLines("/Users/wuyiwen/Documents/GraduationProject/fldata/fault-localization.cs.washington.edu/data/Lang/3/killmaps/Lang/3/killmap.csv");
        List<Killmap> killmapList=KillmapHandler.fileToKillmap(stringListKillmap);



        //new 只跑变异的
        /**
         * 只跑变异是什么问题呢
         * 变异体不一定能找到行
         *
         *
         */
        List<Mutants> onlyMetallsuspicious= Metallaxis.calculateMetallaxis(killmapList,mutantsList);
        //排序他
        System.out.println("sort mutants suspicious");
        List<Mutants> onlyMutantsAfterSort = LineSort.sortMutants(onlyMetallsuspicious);
        for (Mutants a:onlyMutantsAfterSort){
            System.out.println(a.getID()+"\t"+a.getFilename()+"\t"+a.getCodeline()+"\t"+a.getSuspicion());
        }
        List<Spectra> spectraOnlyMetallMutants= SpectraHandler.getSpectraFinall(onlyMutantsAfterSort,spectraList);
        List<Spectra> spectraOnlyMetallMutantsFinall=LineSort.sortSpectra(spectraOnlyMetallMutants);
        //只输出几行
        /*for (int index=0;index<11;index++){
            System.out.println("only metall"+spectraOnlyMetallMutantsFinall.get(index).getFilename()+spectraOnlyMetallMutantsFinall.get(index).getLine()+"\t"+spectraOnlyMetallMutantsFinall.get(index).getSuspicious());
        }*/
        for (Spectra spectra:spectraOnlyMetallMutantsFinall){
            System.out.println(spectra.getFilename()+spectra.getLine()+"\t"+spectra.getSuspicious());
        }

    }
}
