package com.jin.leet;

/**
 * 393. UTF-8 Validation
 * A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:
 *
 * For 1-byte character, the first bit is a 0, followed by its unicode code.
 * For n-bytes character, the first n-bits are all one's, the n+1 bit is 0, followed by n-1 bytes with most significant 2 bits being 10.
 * This is how the UTF-8 encoding would work:
 *
 *    Char. number range  |        UTF-8 octet sequence
 *       (hexadecimal)    |              (binary)
 *    --------------------+---------------------------------------------
 *    0000 0000-0000 007F | 0xxxxxxx
 *    0000 0080-0000 07FF | 110xxxxx 10xxxxxx
 *    0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
 *    0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 * Given an array of integers representing the data, return whether it is a valid utf-8 encoding.
 *
 * Note:
 * The input is an array of integers. Only the least significant 8 bits of each integer is used to store the data.
 * This means each integer represents only 1 byte of data.
 *
 * Example 1:
 *
 * data = [197, 130, 1], which represents the octet sequence: 11000101 10000010 00000001.
 *
 * Return true.
 * It is a valid utf-8 encoding for a 2-bytes character followed by a 1-byte character.
 * Example 2:
 *
 * data = [235, 140, 4], which represented the octet sequence: 11101011 10001100 00000100.
 *
 * Return false.
 * The first 3 bits are all one's and the 4th bit is 0 means it is a 3-bytes character.
 * The next byte is a continuation byte which starts with 10 and that's correct.
 * But the second continuation byte does not start with 10, so it is invalid.
 *
 */
public class ValidUTF8 {
    public boolean validUtf8(int[] data) {
        if(data==null || data.length==0) return false;
        for (int i=0; i<data.length; i++) {
            if(data[i]>255) return false;
            int numOfBytes = numOfBytes(data[i]);
            if (numOfBytes<=0)
                return false;
            for (int j=1; j<numOfBytes; j++) {
                if (i+j >= data.length||  !isValidByte(data[i+j]))
                    return false;
            }
            i = i+numOfBytes -1;
        }
        return true;
    }

    private int numOfBytes(int data) {
        // 10000000: 128
        if ((data & 128) == 0)
            return 1;
        // 110xxxxxx: 192
        if ((data & 224) == 192)
            return 2;
        // 1110xxxx
        if ((data & 240) == 224)
            return 3;
        if ((data & 248) == 240)
            return 4;
        return -1;
    }

    private boolean isValidByte(int data) {
        return (data & 192) == 128;
    }

    public static void main(String args[]) {
        ValidUTF8 v = new ValidUTF8();
        //tre
        System.out.println(v.validUtf8(new int[]{197,130,1}));
        // true
        System.out.println(v.validUtf8(new int[]{240,162,138,147,17}));
        // false
        System.out.println(v.validUtf8(new int[]{240,162,138,147,145}));
    }
}
