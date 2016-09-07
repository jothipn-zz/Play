package com.jothi.play;

public class GrayCodeConvertor
{
    public static void main( String[] args )
    {
        for (int i: getGrayCode(4)) {
            System.out.print(String.format("%4s\t",Integer.toBinaryString(i)).replace(' ','0'));
        }
    }

    public GrayCodeConvertor() {}

    public static int[] getGrayCode(int size) {
        int[] retVal = new int[(int) Math.pow(2, size)];
        if (size == 1) {
            retVal[0] = 0;
            retVal[1] = 1;
            return retVal;
        } else {
            int prevArrSize = (int) Math.pow(2, size - 1);
            int currArrSize = (int) Math.pow(2, size);
            int[] prev = getGrayCode(size - 1);
            for (int i = 0; i < currArrSize; i++) {
                if (i < prevArrSize) {
                    retVal[i] = prev[i];
                } else {
                    int offset = i - prevArrSize;
                    retVal[i] = (int)Math.pow(2,size - 1) + prev[prevArrSize - offset - 1];
                }
            }
        }
        return retVal;
    }
}
