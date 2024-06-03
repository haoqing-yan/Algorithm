package com.donny.datastruct.abstractstruct;

/**
 * @author Your Name
 * @date 2024/5/31
 */
public interface MyList {

    void add(int element);

    int get(int index);

    void set(int index, int element);

    int remove(int index);

    int size();

    boolean isEmpty();

    void printList();
}
