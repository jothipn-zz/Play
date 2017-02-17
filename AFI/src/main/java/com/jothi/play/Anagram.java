package com.jothi.play;

import java.util.*;

public class Anagram
{
    public static void main( String[] args )
    {
        String [] input = new String [] { "dog", "god", "cat", "act", "adfadfw"};
        for (String s: getAnagrams(input)){
            System.out.println(s);
        }
    }

    static String[] getAnagrams(String[] input) {
        ArrayList<String> result = new ArrayList<>();
        Map<String, String> words = new HashMap<>();

        for (String s: input) {
            String sortedS = sort(s);
            if (words.get(sortedS) != null) {
                String val = words.get(sortedS);
                words.put(sortedS, val.concat(",").concat(s));
            } else {
                words.put(sortedS, s);
            }
        }

        for (Map.Entry<String, String> e: words.entrySet()) {
            result.add(e.getValue());
        }
        String[] rv = new String[result.size()];
        result.toArray(rv);
        return rv;
    }

    static String sort(String input) {
        char[] chars = new char[input.length()];
        input.getChars(0, input.length(), chars, 0);
        Arrays.sort(chars);
        return new String(chars);
    }


}
