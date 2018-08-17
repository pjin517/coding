package com.jin.cmp.pin;

/**
 * 	有一道单选题，有L个选项，拿去问N个人，把大家的答案存在一个array，这个array的size会等于N，且array element可能的值为 {1, 2, ..., L}
 * 	求大家对问题的答案有共识的概率，如果不用概率closed-form解，要怎么写code估计这个概率？
 *
 * 	有共识的定义如下：
 * 	如果有大于等于N/2个人，给了一样的答案，则定义为有共识
 * 	.1point3acres网
 *
 * From <http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=371197&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D33%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
 */
public class VotingConsensus {
    int N;
    int options;
    int total;
    int consensus;

    public VotingConsensus(int n, int options) {
        N = n;
        this.options = options;
    }

    double votingConsensusProbability() {
        int[] votes = new int[N];
        vote(votes, 0);
        System.out.println("Total: " + total + "; consensus: " + consensus);
        return (Double.valueOf(consensus)/Double.valueOf(total));
    }

    void vote(int[] votes, int index) {
        if (index == votes.length) {
            for (int i: votes)
             System.out.print(i+ " ");
            System.out.println();
            total++;
            int[] result = new int[options];
            for (int v: votes)
                result[v-1] ++;
            for (int r: result)
                if (r >= N/2+1)
                    consensus++;
        } else {
            for (int i=1; i<=options; i++) {
                votes[index] = i;
                vote(votes, index+1);
            }
        }
    }
    public static void main(String args[]) {
        VotingConsensus votingConsensus = new VotingConsensus(4, 4);
        System.out.println(votingConsensus.votingConsensusProbability());
    }
}
