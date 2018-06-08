package com.jin.cmp.gg;

/**
 * 给了一堆log，log里有用户id，resource id以resource在某个起始时间和终止时间的使用量，比如用户abc在1到5秒钟使用了CPU的数量是2，
 * 用户abc在2到3秒使用的CPU数量是4，也就是一个用户对某个resource的使用在某个时间是可以叠加的，给定一个resource id，
 * 根据用户对这个resource的peak使用量，找到top k的用户。上面的例子中abc的CPU的peak使用量是2+4=6
 *
 * Similar to Meeting Room II
 *
 * @see com.jin.leet.MeetingRoomII
 */
public class TopKResourceUsage {

    public static void main(String args[]) {

    }
}
