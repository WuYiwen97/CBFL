package com.wuyiwen.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
public class ACM_main2 {
    public static void main(String args[]) throws Exception {
        int[] arr1={-3,75,12,-3};
        int[] arr2=arr1.clone();
        Map<Integer,Integer> result=new HashMap<Integer,Integer>();
// System.out.println("排序前"+Arrays.toString(arr1));
        Arrays.sort(arr2);
// System.out.println("排序后"+Arrays.toString(arr2));
        int index=1;
        for (int data : arr2) {
            if(result.get(data)==null) {
                result.put(data,index++);
            }
        }
        for (int data : arr1) {
            System.out.print(result.get(data)+" ");
        }
        System.out.println();
    }
}