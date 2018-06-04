package com.jin.leet;

import java.util.Iterator;

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class Leet284PeekingIterator<T> implements Iterator<T> {
    T peekValue;
    Iterator<T> itr;
    public Leet284PeekingIterator(Iterator<T> iterator) {
        // initialize any member here.
        this.itr = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public T peek() {
        if (peekValue != null) {
            return peekValue;
        }
        if (hasNext()) {
            peekValue =  itr.next();
        }
        return peekValue;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public T next() {
        if (peekValue != null) {
            T tmp = peekValue;
            peekValue = null;
            return tmp;
        }
        return itr.next();
    }

    @Override
    public boolean hasNext() {
        if (peekValue != null) {
            return true;
        }
        return itr.hasNext();
    }
}