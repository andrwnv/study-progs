package LinkedList;

import LinkedList.ListExceptions.List_TryingDeleteFromEmptyContain;

public class DoubleListNode<T> implements INode<T> {

    private T                 _data;
    private DoubleListNode<T> _next;
    private DoubleListNode<T> _prev;

    public DoubleListNode() {
        _data = null;

        _next = null;
        _prev = null;
    }

    public DoubleListNode(T value) {
        _data = value;

        _next = null;
        _prev = null;
    }

    public DoubleListNode(T value, DoubleListNode<T> nextNode, DoubleListNode<T> prevNode) {
        _data = value;

        _next = nextNode;
        _prev = prevNode;
    }

    public void setNext(DoubleListNode<T> node) {
        _next = node;
    }

    public void setPrev(DoubleListNode<T> node) {
        _prev = node;
    }

    public DoubleListNode<T> getNext() {
        return _next;
    }

    public DoubleListNode<T> getPrev() {
        return _prev;
    }

    @Override
    public T getValue() {
        return _data;
    }

    @Override
    public void setValue(T data) {
        _data = data;
    }

}
