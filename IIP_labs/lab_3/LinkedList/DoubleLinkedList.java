package LinkedList;

import LinkedList.ListExceptions.List_ElementNotFoundException;
import LinkedList.ListExceptions.List_TryingDeleteFromEmptyContain;

public final class DoubleLinkedList<T> implements ILinkedList<T> {

    private DoubleListNode<T> m_head_node;
    private DoubleListNode<T> m_tail_node;
    private int               m_size;

    public DoubleLinkedList() {
        m_size = 0;
    }

    public DoubleLinkedList(DoubleListNode<T> head_node) {
        m_head_node = head_node;
        m_tail_node = head_node;

        m_size      = 1;
    }

    private boolean isFirstNode(DoubleListNode<T> node) {
        if (m_head_node == null) {
            m_head_node = node;
            m_tail_node = m_head_node;

            return true;
        }

        return false;
    }

    @Override
    public int size() {
        return m_size;
    }

    @Override
    public void clear() {
        m_head_node = null;
        m_tail_node = null;
    }

    @Override
    public boolean empty() {
        return m_head_node == null && m_tail_node == null;
    }

    private DoubleListNode<T> searchNode(int position) {
        DoubleListNode<T> node_iter = m_head_node;

        int i = 0;
        while (i < position && node_iter != null) {
            ++i;
            node_iter = node_iter.getNext();
        }

        return node_iter;
    }

    @Override
    public boolean insert(int position, T value) throws Exception {
        if (position == m_size) {
            this.push_back(value);
            return true;
        }

        DoubleListNode<T> new_node = new DoubleListNode<>(value);

        var searched_node = searchNode(position);

        if (searched_node == null) {
            throw new List_ElementNotFoundException();
        }

        m_size++;

        if ( isFirstNode(new_node) ) {
            return true;
        }

        new_node.setNext(searched_node.getNext());
        new_node.setPrev(searched_node);
        searched_node.getNext().setPrev(new_node);
        searched_node.setNext(new_node);

        return true;
    }

    @Override
    public boolean erase(int position) throws Exception {
        DoubleListNode<T> node_iter = searchNode(position);

        if (m_head_node == null){
            throw new List_TryingDeleteFromEmptyContain();
        }

        if (node_iter == null) {
            throw new List_ElementNotFoundException();
        }

        m_size--;

        node_iter.getPrev().setNext( node_iter.getNext() );
        node_iter.getNext().setPrev( node_iter.getPrev() );

        return true;
    }

    @Override
    public void push_back(T value) {

        DoubleListNode<T> new_node = new DoubleListNode<>(value);
        m_size++;

        if ( isFirstNode(new_node) )
            return;

        new_node.setPrev(m_tail_node);
        m_tail_node.setNext(new_node);
        m_tail_node = new_node;
    }

    @Override
    public void push_front(T value) {

        DoubleListNode<T> new_node = new DoubleListNode<>(value);
        m_size++;

        if ( isFirstNode(new_node) )
            return;

        new_node.setNext(m_head_node);
        m_head_node.setPrev(new_node);
        m_head_node = new_node;
    }

    @Override
    public T pop_back() throws Exception {
         T deleted_value = m_tail_node.getValue();

         if (m_head_node == null){
             throw new List_TryingDeleteFromEmptyContain();
         }

         m_size--;

         if (m_head_node == m_tail_node) {
             m_head_node = null;
             m_tail_node = null;

             return deleted_value;
         }

         m_tail_node = m_tail_node.getPrev();
         m_tail_node.setNext(null);

         return deleted_value;
    }

    @Override
    public T pop_front() throws Exception {
        T deleted_value = m_head_node.getValue();

        if (m_head_node == null){
            throw new List_TryingDeleteFromEmptyContain();
        }

        m_size--;

        if (m_head_node == m_tail_node) {
            m_head_node = null;
            m_tail_node = null;

            return deleted_value;
        }

        m_head_node = m_head_node.getNext();
        m_head_node.setPrev(null);

        return deleted_value;
    }

    @Override
    public DoubleListNode<T> getHead(){
        return m_head_node;
    }

    public DoubleListNode<T> getTail(){
        return m_tail_node;
    }
}
