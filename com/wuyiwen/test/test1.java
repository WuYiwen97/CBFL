package com.wuyiwen.test;

import com.wuyiwen.IO.ReadFile;
import com.wuyiwen.handle.Killmap;
import com.wuyiwen.handle.KillmapHandler;

import java.util.List;

public class test1 {
    public  static void main(String [] args){
        String a=".lang3.LocaleUtils";
        //String a="org,15,688,PASS,80,da39,,";
        String[] aa=a.split("@",-1);
        System.out.println(aa.length);

        for (int index=0;index<aa.length;index++) {
            if (aa[index].equals("")){
                aa[index]="1";
            }
            System.out.print("("+aa[index]+"),");
        }


       /* List<String> stringListKillmap= ReadFile.readKillmapFileByLines("/Users/wuyiwen/Documents/GraduationProject/fldata/fault-localization.cs.washington.edu/data/Lang/5/killmaps/Lang/5/killmap.csv");
        List<Killmap> killmapList= KillmapHandler.fileToKillmap(stringListKillmap);
        Killmap killmap=new Killmap();
        killmap.printmutants(killmapList.get(0).getCoveredmutants());*/
    }
}
