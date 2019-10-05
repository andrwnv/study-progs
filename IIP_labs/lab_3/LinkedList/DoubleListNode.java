package LinkedList;

import LinkedList.ListExceptions.List_TryingDeleteFromEmptyContain;

public class DoubleListNode<T> implements INode<T> {

    private T                 m_data;
    private DoubleListNode<T> m_next;
    private DoubleListNode<T> m_prev;

    public DoubleListNode() {
        m_data = null;

        m_next = null;
        m_prev = null;
    }

    public DoubleListNode(T value) {
        m_data = value;

        m_next = null;
        m_prev = null;
    }

    public DoubleListNode(T value, DoubleListNode<T> next_node, DoubleListNode<T> prev_node) {
        m_data = value;

        m_next = next_node;
        m_prev = prev_node;
    }

    public void setNext(DoubleListNode<T> node) {
        m_next = node;
    }

    public void setPrev(DoubleListNode<T> node) {
        m_prev = node;
    }

    public DoubleListNode<T> getNext() {
        return m_next;
    }

    public DoubleListNode<T> getPrev() {
        return m_prev;
    }

    @Override
    public T getValue() {
        return m_data;
    }

    @Override
    public void setValue(T data) {
        m_data = data;
    }

}
