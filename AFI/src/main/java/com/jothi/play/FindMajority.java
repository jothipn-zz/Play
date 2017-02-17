package com.jothi.play;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Find all words that occur more than n/k times in a given stream of n words. Constant memory O(k)
 */

public class FindMajority {
    public static void main(String[] args) {
        String[] stream = new String[] {
                "a",
                "a",
                "b",
                "b",
                "c",
                "a",
                "a",
                "e"
        };

        FindMajority.findMajority(stream, 3);
    }

    /**
     * Discard k distinct words at a time. What is left is a super set of words that occur more
     * than n/k times. In the second pass, do the actual count and discard the extra to get the
     * exact set. Problem 1.15 in AFI.
     * @param stream
     * @param k
     */
    static void findMajority(String[] stream, int k) {
        HashMap<String, Integer> map = new HashMap<>();

        for (String s: stream) {
            Integer count = map.get(s);
            if (count == null) {
                if (map.size() == k) {
                    Iterator<Map.Entry<String,Integer>> iter = map.entrySet().iterator();
                    while (iter.hasNext()) {
                        Map.Entry<String,Integer> entry = iter.next();
                        Integer tmp = entry.getValue();
                        if (tmp == 1) {
                            iter.remove();
                        } else {
                            tmp --;
                            map.put(entry.getKey(), tmp);
                        }
                    }
                } else {
                    map.put(s, 1);
                }
            } else {
                map.put(s, count + 1);
            }
        }

        Map<String, Integer> finalCounts = new HashMap<>();

        for (String s: stream) {
            if (map.get(s) != null) {
                Integer i = finalCounts.get(s);
                if (i != null) {
                    finalCounts.put(s, ++i);
                } else {
                    finalCounts.put(s, 1);
                }
            }
        }

        for (Map.Entry<String, Integer> e: finalCounts.entrySet()) {
            if (e.getValue() > stream.length/k) {
                System.out.println(e.getKey());
            }
        }
    }
}
