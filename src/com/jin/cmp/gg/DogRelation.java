package com.jin.cmp.gg;

import java.util.*;

/**
 *  设计一个api，求解2个狗是否有血缘关系，每个狗在记录里都有可能有爸爸妈妈，然后如果共享，就是一家人了
 *
 */
public class DogRelation {
    private Map<String, Dog> dogs;

    public DogRelation() {
        this.dogs = new HashMap<>();
    }

    public void addDog(String name, String fatherName, String motherName) {
        Dog father = null;
        if (fatherName!=null) {
            father = dogs.get(fatherName);
            if (father == null) {
                father = new Dog(fatherName);
                dogs.put(fatherName, father);
            }
        }
        Dog mother = null;
        if (motherName!=null) {
            mother = dogs.get(motherName);
            if (mother == null) {
                mother = new Dog(motherName);
                dogs.put(motherName, mother);
            }
        }
        Dog dog = new Dog(name, father, mother);
        dogs.put(name, dog);
    }

    public boolean isRelated(String dog1, String dog2) {
        Dog d1 = dogs.get(dog1);
        Dog d2 = dogs.get(dog2);
        if (d1 == null || d2 == null)
            return false;
        HashSet<Dog> ancestorsOfD1 = new HashSet();
        LinkedList<Dog> q1 = new LinkedList<>();
        q1.offer(d1);
        while (!q1.isEmpty()) {
            Dog d = q1.poll();
            ancestorsOfD1.add(d);
            if (d.father!=null) q1.offer(d.father);
            if (d.mother!=null) q1.offer(d.mother);
        }

        LinkedList<Dog> q2 = new LinkedList<>();
        q2.offer(d2);
        while (!q2.isEmpty()) {
            Dog d = q2.poll();
            if (ancestorsOfD1.contains(d))
                return true;
            if (d.father!=null) q2.offer(d.father);
            if (d.mother!=null) q2.offer(d.mother);
        }
        return false;
    }


    private class Dog {
        String name;
        Dog father;
        Dog mother;

        public Dog(String name, Dog father, Dog mother) {
            this.name = name;
            this.father = father;
            this.mother = mother;
        }

        public Dog(String name) {
            this(name, null, null);
        }
    }


}
