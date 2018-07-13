package com.jin.leet;

/**
 * LeetCode 838
 * There are N dominoes in a line, and we place each domino vertically upright.
 *
 * In the beginning, we simultaneously push some of the dominoes either to the left or to the right.
 *
 *
 *
 * After each second, each domino that is falling to the left pushes the adjacent domino on the left.
 *
 * Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.
 *
 * When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.
 *
 * For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.
 *
 * Given a string "S" representing the initial state. S[i] = 'L', if the i-th domino has been pushed to the left; S[i] = 'R', if the i-th domino has been pushed to the right; S[i] = '.', if the i-th domino has not been pushed.
 *
 * Return a string representing the final state.
 *
 * Example 1:
 *
 * Input: ".L.R...LR..L.."
 * Output: "LL.RR.LLRRLL.."
 * Example 2:
 *
 * Input: "RR.L"
 * Output: "RR.L"
 * Explanation: The first domino expends no additional force on the second domino.
 * Note:
 *
 * 0 <= N <= 10^5
 * String dominoes contains only 'L', 'R' and '.'
 *
 * Tips:
 * Keep track of last seen 'L' and 'R' as indices (variables L and R).
 *
 * If you see 'R' and R > L, you have R....R, turn everything to 'R'.
 * If you see 'R' and R < L, you have L...R and you don't need to do anything.
 * If you see 'L' and L > R, you have L....L, turn everything to 'L'.
 * if you see 'L' and L < R, you have R....L, have to pointers from both sides, lo and hi, turn a[lo]='R' and a[hi] = 'L', increment lo, decrement hi, make sure you do nothing when lo=hi
 * Watch out for edge cases. Note i<=dominoes.length(), this is to deal with L.. Also note L and R are initialized to -1, not 0.
 */
public class PushDominoes {
    public String pushDominoes(String dominoes) {
        char[] a = dominoes.toCharArray();
        int len = dominoes.length();
        int R = -1;
        int L = -1;

        for (int i=0; i<=len; i++) {
            if (i==len || a[i] == 'R') {
                if (R > L) { // we have R....R
                    while(R<i) {
                        a[R++] = 'R';
                    }
                }
                R = i;
            } else if (a[i] == 'L'){
                if (L>R || (L==-1 && R==-1)) { // we have L....L
                    while (++L<i) {
                        a[L] = 'L';
                    }
                } else { //we have R....L
                    L = i;
                    int r = R + 1;
                    int l = i - 1;
                    while (r<l){
                        a[r++] = 'R';
                        a[l--] = 'L';
                    }
                }
            }
        }
        return new String(a);
    }

    public static void main(String[] args) {
        PushDominoes p = new PushDominoes();
        System.out.println(".L.R...LR..L.. TO " + p.pushDominoes(".L.R...LR..L.."));
    }
}
