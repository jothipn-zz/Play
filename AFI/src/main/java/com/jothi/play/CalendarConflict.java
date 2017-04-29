package com.jothi.play;


import java.util.ArrayList;

/**
 * Created by jothi.padmanabhan on 4/2/17.
 */
public class CalendarConflict {
    public static void main(String[] args) {

        CalendarEntry[] calendar = new CalendarEntry[7];
        calendar[0] = new CalendarEntry(1, 3, "A");
        calendar[1] = new CalendarEntry(3,5, "B");
        calendar[2] = new CalendarEntry(4,6, "C");
        calendar[3] = new CalendarEntry(5,8, "D");
        calendar[4] = new CalendarEntry(6,7, "E");
        calendar[5] = new CalendarEntry(10,12, "F");
        calendar[6] = new CalendarEntry(11,12, "G");


        String[] s = CalendarConflict.findConflicts(calendar);
        for (String a: s)
            System.out.println(a);
    }

    private static String[] findConflicts(CalendarEntry[] calendar) {
        int length = calendar.length;

        int prevEnd = calendar[0].endTime;
        String prevId = calendar[0].name;
        ArrayList<String> result = new ArrayList<>();
        StringBuffer temp = new StringBuffer();

        for (int i = 1; i < length; i++) {
            if (calendar[i].startTime < prevEnd) { //conflict
                if (prevId != null) {
                    temp.append(prevId);
                    prevId = null;
                }
                temp.append(calendar[i].name);
                prevEnd = Math.max(prevEnd, calendar[i].endTime);
            } else { // no conflict
                if (temp.length() > 0) {
                    result.add(temp.toString());
                    temp.setLength(0);
                }
                prevEnd = calendar[i].endTime;
                prevId = calendar[i].name;
            }
        }
        if (temp.length() > 0) {
            result.add(temp.toString());
        }
        String[] r = new String[result.size()];
        return result.toArray(r);
    }

    private static class CalendarEntry {
        int startTime;
        int endTime;
        String name;

        CalendarEntry(int s, int e, String n) {
            startTime = s;
            endTime = e;
            name = n;
        }
    }
}
