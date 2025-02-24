package cn.iwbr.rescue.grammar.datastructure;

import java.util.NoSuchElementException;

/**
 * @description: 自定义双向链表并封装增删改查的API
 * @author: <a href="mailto:wangbaorui@supermap.com">wangbaorui</a>
 * @date: 2025/02/08
 */
public class MyLinkedList<T> {

    // 虚拟头尾节点（能够使得链表首尾位置的增删改查的操作的时间复杂度变成O(1)）
    final private Node<T> head, tail;

    private int size;

    // 双链表节点
    private static class Node<E> {
        E val;
        Node<E> next;
        Node<E> prev;

        Node(E val) {
            this.val = val;
        }
    }

    public MyLinkedList() {
        this.head = new Node<>(null);
        this.tail = new Node<>(null);
        head.next = tail;
        tail.prev = head;
        this.size = 0;
    }

    public void addList(T e) {
        Node<T> add = new Node<>(e);
        Node<T> prev = this.tail.prev;

        prev.next = add;
        add.prev = prev;
        add.next = this.tail;

        this.tail.prev = add;
        this.size++;
    }

    public void addFirst(T e) {
        Node<T> add = new Node<>(e);
        Node<T> next = this.head.next;

        next.prev = add;
        add.next = next;
        add.prev = this.head;

        this.head.next = add;
        this.size++;
    }

    public void add(int index, T t){
        // 检查索引的可用性
        checkPositionIndex(index);

        // 如果是最后一个节点则往末尾插入
        if(index == size){
            addList(t);
            return;
        }

        // 插入数据
        Node<T> node = getNode(index);
        Node<T> prev = node.prev;

        Node<T> x = new Node<>(t);
        x.next = node;
        x.prev = prev;

        prev.next = x;
        node.prev = x;

        this.size++;
    }

    public T reomveFirst(){
        if (size < 1) {
            throw new NoSuchElementException();
        }

        // head <-> x <-> temp
        Node<T> next = this.head.next;
        Node<T> next1 = next.next;

        this.head.next = next1;
        next1.prev = this.head;

        next.prev = null;
        next.next = null;

        this.size--;
        return next.val;
    }

    public T reomveLast(){
        if (size < 1) {
            throw new NoSuchElementException();
        }

        Node<T> delete = this.tail.prev;
        Node<T> prev = delete.prev;

        this.tail.prev = prev;
        prev.next = this.tail;

        delete.next = null;
        delete.prev = null;

        this.size--;
        return delete.val;
    }

    public T remove(int index){
        checkElementIndex(index);
        Node<T> node = getNode(index);

        Node<T> next = node.next;
        Node<T> prev = node.prev;

        next.prev = prev;
        prev.next = next;

        node.prev = null;
        node.next = null;

        this.size--;

        return node.val;
    }

    public T set(int index, T value){
        checkElementIndex(index);
        Node<T> node = getNode(index);

        T val = node.val;
        node.val = value;
        return val;
    }

    public T get(int index) {
        checkElementIndex(index);
        Node<T> node = getNode(index);
        return node.val;
    }

    public T getFirst(){
        if (size < 1) {
            throw new NoSuchElementException();
        }
        return this.head.next.val;
    }

    public T getLast(){
        if (size < 1) {
            throw new NoSuchElementException();
        }
        return this.head.prev.val;
    }


    private Node<T> getNode(int index){
        checkElementIndex(index);
        Node<T> next = this.head.next;
        for (int i = 0; i < index; i++) {
            next = next.next;
        }
        return next;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= this.size;
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < this.size;
    }

    // 检查 index 索引位置是否可以存在元素
    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    // 检查 index 索引位置是否可以添加元素
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
}
