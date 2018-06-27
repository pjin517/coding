package com.jin.leet;

/**
 * LeetCode158
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 *
 * The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
 *
 * By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
 *
 * Note:
 * The read function may be called multiple times.
 *
 */
public class ReadNCharsGivenRead4II {
    char[] buf4;
    int index;

    public ReadNCharsGivenRead4II() {
        buf4 = new char[4];
        index = 0;
    }

    public int read(char[] buf, int n) {
        boolean eof = false;
        int charsRead = 0;

        if (buf4.length > 0 && index < 4) {
            System.arraycopy(buf4, index, buf, charsRead, 4-index);
        }

        while (!eof && charsRead<n) {
            int size = read4(buf4);
            if (size < 4) {
                eof = true;
            }
            if (size + charsRead > n) {
                size = n - charsRead;
                index = size;
            }
            System.arraycopy(buf4, 0, buf, charsRead, size);
            charsRead += size;
        }

        return charsRead;
    }

    int read4(char[] buf) {
        buf = new char[] {'a', 'b', 'c', 'd'};
        return 4;
    }

    public static void main(String args[]) {

    }
}
