package cn.iwbr.rescue.grammar.entity;

/**
 * @description: 节点
 * @author: <a href="mailto:wangbaorui@supermap.com">wangbaorui</a>
 * @date: 2025/02/07
 */
public class Node<E> {
    E val;
    Node<E> next;
    Node<E> prev;

    public Node(Node<E> prev, E element, Node<E> next) {
        this.val = element;
        this.next = next;
        this.prev = prev;
    }

    public Node(E val) {
        this.val = val;
    }

    public Node() {
    }

    public E getVal() {
        return val;
    }

    public Node<E> getNext() {
        return next;
    }

    public Node<E> getPrev() {
        return prev;
    }

    public void setVal(E val) {
        this.val = val;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public void setPrev(Node<E> prev) {
        this.prev = prev;
    }
}
