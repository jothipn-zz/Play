package com.jothi.play;

import java.util.ArrayList;

/**
 * Created by jothi.padmanabhan on 4/18/17.
 */
public class Subset {
    public static void main(String[] args) {
        ArrayList<String> set = new ArrayList<>();
        set.add("a");
        set.add("b");
        set.add("c");
        //set.add(4);

        ArrayList<ArrayList<String>> results = getSubsets2(set);
        for (ArrayList<String> al: results) {
            for (String i : al) {
                System.out.printf("%s%s", i, "\t");
            }
            System.out.println("\n");
        }
    }

    static ArrayList<ArrayList<String>> getSubsets2(ArrayList<String> set) {
        ArrayList<ArrayList<String>> allsubsets = new ArrayList<>();
        int max = 1 << set.size();
        for (int i = 0; i < max; i++) {
            System.out.println(" I is " + i);
            ArrayList<String> subset = new ArrayList<>();
            int k = i;
            int index = 0;
            while (k > 0) {
                //System.out.println("K : " + k);
                //System.out.printf("%s %d\n", " K&1 : " , k & 1);

                if ((k & 1) > 0) {
                    System.out.println("Index: " + index + ", Adding " + set.get(index));
                    subset.add(set.get(index));
                } else {
                    System.out.println("Index: " + index + ", Ignoring " + set.get(index));
                }
                k >>= 1;
                index++;
            }
            allsubsets.add(subset);
        }
        return allsubsets;
    }
}