package com.jothi.play;


import java.util.HashMap;
import java.util.Map;

public class LongestNonDecreasingSubsequence
{
    public static void main( String[] args )
    {
        int [] input = new int [] { 1,2,5,4,7,9,10,1,2,3};
        findLNDS(input);
    }

    static void findLNDS(int[] input) {
        Map<Integer, Integer> seqMap = new HashMap<>();
        int seqStart = 0;
        int seqIndex = 0;
        int i = 1;
        while (i < input.length) {
            if (input[i] >= input[i - 1]) {
                seqIndex++;
            } else {
                seqMap.put(seqStart, (seqIndex - seqStart) + 1);
                seqStart = i;
                seqIndex = i;
            }
            i++;
        }
        // Once you are past the end of the array, register the last one
        seqMap.put(seqStart, (i - seqStart));

        int maxSize = 0;
        int startIndex = 0;
        for (Map.Entry<Integer, Integer> e : seqMap.entrySet()) {
            if (e.getValue() > maxSize) {
                startIndex = e.getKey();
                maxSize = e.getValue();
            }
        }

        System.out.println("LNDS is at index: " + startIndex + ", length is " + maxSize);

    }
}
