package com.jin.leet;

import java.util.BitSet;

/**
 * LeetCode 379 - Design Phone Directory
 *
 * Design a Phone Directory which supports the following operations:
 *
 * get: Provide a number which is not assigned to anyone.
 * check: Check if a number is available or not.
 * release: Recycle or release a number.
 * Example:
 * // Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
 * PhoneDirectory directory = new PhoneDirectory(3);
 *
 * // It can return any available phone number. Here we assume it returns 0.
 * directory.get();
 *
 * // Assume it returns 1.
 * directory.get();
 *
 * // The number 2 is available, so return true.
 * directory.check(2);
 *
 * // It returns 2, the only number that is left.
 * directory.get();
 *
 * // The number 2 is no longer available, so return false.
 * directory.check(2);
 *
 * // Release number 2 back to the pool.
 * directory.release(2);
 *
 * // Number 2 is available again, return true.
 * directory.check(2);
 *
 * ToDO: submit
 */
public class PhoneDirectory {
    int maxNumber;
    BitSet bitSet;
    int smallestFreeNumber;

    public PhoneDirectory(int maxNumber) {
        this.maxNumber = maxNumber;
        bitSet = new BitSet(maxNumber);
        smallestFreeNumber = 0;
    }

    public int get() {
        if (smallestFreeNumber > maxNumber)
            return -1;
        int number = smallestFreeNumber;
        bitSet.set(number);
        smallestFreeNumber = bitSet.nextClearBit(smallestFreeNumber);
        return number;
    }

    public boolean check(int number) {
        return bitSet.get(number) == false;
    }

    public void release(int number) {
        if (bitSet.get(number) == false)
            return;
        bitSet.clear(number);
        if (number < smallestFreeNumber) {
            smallestFreeNumber = number;
        }
    }

    public static void main(String[] args) {
        PhoneDirectory directory = new PhoneDirectory(3);
        System.out.println(directory.get());
        System.out.println(directory.get());
        System.out.println(directory.check(2));
        System.out.println(directory.get());
        System.out.println(directory.check(2));
        directory.release(2);
        System.out.println(directory.check(2));
    }
}
