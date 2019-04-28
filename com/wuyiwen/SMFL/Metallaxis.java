package com.wuyiwen.SMFL;

import com.wuyiwen.handle.Killmap;
import com.wuyiwen.handle.Mutants;

import java.util.ArrayList;
import java.util.List;

/**
 * SMFL 基于突变的覆盖定位
 * Metallaxis m(i)=mf/sqrt( F*(mf+mp) )
 *
 */
public class Metallaxis {
    /**
     * 根据killmap.csv 和 mutants.log 找到 突变体 并计算其的可疑度
     *
     * @param killmapList
     * @param mutantsList
     * @return
     */
    public static List<Mutants> calculateMetallaxis(List<Killmap> killmapList,List<Mutants> mutantsList){
        List<Mutants> mutantsList1=new ArrayList<>();
        int totalF=0; //未执行变异体 总的失败数字
        int totalP=0; //未执行变异体 总的成功的
        //TODO 先算 F 覆盖的行 --0 outcome----FAIL
        for(int index=0;index<killmapList.size();index++){
            Killmap killmapA=killmapList.get(index);
            if (killmapA.getMutantid()==0 && killmapA.getOutcome().endsWith("FAIL")){
                totalF++;
            }
            else if(killmapA.getMutantid()==0 && killmapA.getOutcome().endsWith("PASS")){
                totalP++;
            }
        }
        System.out.println("echo ===========fail or pass"+totalF+"\t"+totalP);
        // TODO 找变化的值
        int mf=0; //fail -> pass
        int mp=0; //pass -> fail
        for(int index=0;index<mutantsList.size();index++){
            //System.out.println("===========");
            Mutants mutantsA=mutantsList.get(index);
            for (int index2=0;index2<killmapList.size();index2++){
                Killmap killmapB=killmapList.get(index2);
                //找到mutants 的突变情况
                if (mutantsA.getID()==killmapB.getMutantid()){
                    /*System.out.println("找到了");*/
                    if (killmapB.getOutcome().equals("PASS")){
                        if(findOldOutCome(killmapB,killmapList).equals("FAIL")){
                            mf++;
                        }
                    }
                    //如果不是pass 可能有3种情况
                    else if (!killmapB.getOutcome().equals("PASS")){
                        if(findOldOutCome(killmapB,killmapList).equals("PASS")){
                            mp++;
                        }
                    }
                    //计算
                    double susp =0.0;
                    if (mf==0){
                        //System.out.println(mutantsA.getID());
                        mutantsA.setSuspicion(1.2);
                    }
                    else {
                        /*System.out.println("mf =?0");*/
                        susp=(double) mf/Math.sqrt((double)totalF*((double)mf+mp));
                        mutantsA.setSuspicion(susp);
                        //mutantsA.setSuspicion(1.2);
                    }

                }

            }
        }
        return  mutantsList;
    }

    /**
     * 找寻未执行mutant之前的outcome
     * @param killmap
     * @param killmapList
     * @return
     */
    public static String findOldOutCome(Killmap killmap,List<Killmap> killmapList){
        int mutantid =killmap.getMutantid();
        String testcase=killmap.getTestcase();
        //System.out.println(testcase);
        for (int index=0;index<killmapList.size();index++){
            Killmap killmapC=killmapList.get(index);
            //mutants ===0 testcase 相等
            //System.out.println(killmapC.getMutantid()+"\t"+killmapC.getTestcase());
            if(killmapC.getMutantid()==0 && killmapC.getTestcase().equals(testcase)){
                for(int index2=0;index2<killmapC.getCoveredmutants().length;index2++){
                    //找到突变体  一定能找到
                    if (killmapC.getCoveredmutants()[index2]==mutantid){
                        return killmapC.getOutcome();
                    }
                }
            }
        }
        return null;    //error


    }
}
