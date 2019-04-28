package com.wuyiwen.handle;

import java.util.ArrayList;
import java.util.List;

public class KillmapHandler {
    public static List<Killmap> fileToKillmap(List<String> stringListKillmap){
        List<Killmap> killmapList=new ArrayList<>();
        //从 000000开始
        for (int index=0;index<stringListKillmap.size();index++){
            String[] strings=stringListKillmap.get(index).split(",",-1);
            //System.out.println("length is ==="+strings.length+"read line ===="+index);
            Killmap killmap=new Killmap();
            killmap.setTestcase(strings[0]);
            killmap.setMutantid(Integer.parseInt(strings[1]));
            killmap.setTimeout(Integer.parseInt(strings[2]));
            killmap.setOutcome(strings[3]);
            killmap.setRuntime(Integer.parseInt(strings[4]));
            killmap.setHash(strings[5]);
            //第6个为覆盖行 可能没有 绝大部分情况是没有的
            if (!strings[6].equals("")){
                String[] coverline=strings[6].split(" ");
                int[] coverlineInt = new int[coverline.length];
                for(int i=0;i<coverline.length;i++){
                    coverlineInt[i] = Integer.parseInt(coverline[i]);
                }
                killmap.setCoveredmutants(coverlineInt);
            }
            else{
               // killmap.setCoveredmutants();
            }
            killmapList.add(killmap);
        }
        return killmapList;
    }

}
