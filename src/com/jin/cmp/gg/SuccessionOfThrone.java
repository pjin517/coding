package com.jin.cmp.gg;

import com.sun.deploy.net.proxy.pac.PACFunctions;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 王位继承。初始只有一个king，king会有孩子，孩子还能生孩子。实现几个function:
 * void birth(String parent, String name) 父亲名字和孩子名字，生个娃
 * void death(String name) 此人要死
 * List<String> getOrder() 返回当前的继承顺序，string array/list.
 * 讨论得知，每个人的名字是唯一的，继承顺序符合如下规律:
 * 假设王有大皇子二皇子三皇子，大皇子有长子次子三子，那么继承顺序是
 * 王->大皇子->大皇子长子->大皇子次子->大皇子三子->二皇子->三皇子
 * 死掉的人不能出现在继承顺序里，但是如果上面例子中大皇子死了，只需把大皇子移除，原始继承顺序保持不变：王->大皇子长子->大皇子次子->大皇子三子->二皇子->三皇子
 *
 * 三个function会被反复调用，实现function细节。
 *
 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=429208&ctid=483
 */

class RoyalMember {
    String name;
    RoyalMember parent;
    LinkedList<RoyalMember> children;

    public RoyalMember(String name) {
        this.name = name;
        this.parent = null;
        this.children = new LinkedList<>();
    }

    public RoyalMember(String name, RoyalMember parent) {
        this.name = name;
        this.parent = parent;
        this.children = new LinkedList<>();
    }

    public static List<String> preOrderTraverse(RoyalMember root, List<String> result) {
        if (root == null)
            return result;
        result.add(root.name);
        for (RoyalMember m: root.children)
            preOrderTraverse(m, result);
        return result;
    }

    public void addChild(String childName) {
        this.children.addLast(new RoyalMember(childName, this));
    }
}



public class SuccessionOfThrone {
    HashMap<String, RoyalMember> royalFamily = new HashMap<>();
    RoyalMember king;

    public void birth(String parent, String name) {
        if (parent == null) {
            king = new RoyalMember(name);
            royalFamily.put(name, king);
        } else {
            RoyalMember p = royalFamily.get(parent);
            if (p == null) {
                System.out.println(parent + " is not a royal family member!");
            }
            else {
                RoyalMember child = new RoyalMember(name, p);
                p.children.addLast(child);
                royalFamily.put(name, child);
            }
        }
    }

    public void death(String name) {
        RoyalMember member = royalFamily.get(name);
        if (member == null)
            return;
        RoyalMember parent = member.parent;
        if (parent == null) { // member is the king
            if (king.children.size()>0) {
                king = king.children.getFirst();
                for (RoyalMember m: member.children.subList(1, member.children.size())) {
                    king.children.addLast(m);
                }
            }
            else
                king = null;
        } else {
            int idx = parent.children.indexOf(member);
            parent.children.remove(member);
            for (RoyalMember m: member.children) {
                parent.children.add(idx++, m);
            }
        }
    }

    public List<String> getOrder() {
        List<String> res = new LinkedList<>();
        return RoyalMember.preOrderTraverse(king, res);
    }

    public static void main(String args[]) {
        SuccessionOfThrone throne = new SuccessionOfThrone();
        throne.birth(null, "King");
        throne.birth("King", "A");
        throne.birth("King", "B");
        throne.birth("King", "C");
        throne.birth("A", "AA");
        throne.birth("A", "AB");
        throne.birth("A", "AC");
        throne.birth("B", "BA");
        throne.birth("B", "BB");
        System.out.println(throne.getOrder().toString());
        throne.death("A");
        System.out.println(throne.getOrder().toString());
        throne.death("King");
        System.out.println(throne.getOrder().toString());

    }
}
