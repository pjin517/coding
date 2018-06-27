package com.jin.leet;

/**
 * LeetCode157
 *
 * API: int read4(char *buf) reads 4 characters at a time from a file.
 *
 * The return value is the actual number of characters read.
 * For example, it returns 3 if there is only 3 characters left in the file.
 *
 * By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
 *
 * Note:
 * The read function will only be called once for each test case.
 *
 * TODO: attempt submit
 */

public class ReadNCharsGivenRead4 {
    public int read(char[] buf, int n) {
        int numRead = 0;
        char[] buf4 = new char[4];

        boolean eof = false;
        while (!eof && numRead < n) {
            int num = read4(buf4);
            if (num < 4) {
                eof = true;
            }
            if (num + numRead > n) {
                num = n - numRead;
            }
            System.arraycopy(buf4, 0, buf, numRead, num);
            numRead += num;
        }
        return numRead;
    }

    int read4(char[] buf) {
        buf = new char[] {'a', 'b', 'c', 'd'};
        return 4;
    }

    public static void main(String args[]) {

    }
}
