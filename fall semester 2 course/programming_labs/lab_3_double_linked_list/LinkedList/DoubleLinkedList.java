package LinkedList;

import LinkedList.ListExceptions.List_ElementNotFoundException;
import LinkedList.ListExceptions.List_TryingDeleteFromEmptyContain;

public final class DoubleLinkedList<T> implements ILinkedList<T> {

    private DoubleListNode<T> _headNode;
    private DoubleListNode<T> _tailNode;
    private int               _size;

    public DoubleLinkedList() {
        _size = 0;
    }

    public DoubleLinkedList(DoubleListNode<T> headNode) {
        _headNode = headNode;
        _tailNode = headNode;

        _size = 1;
    }

    private boolean isFirstNode(DoubleListNode<T> node) {
        if (_headNode == null) {
            _headNode = node;
            _tailNode = _headNode;

            return true;
        }

        return false;
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public void clear() {
        _headNode = null;
        _tailNode = null;
    }

    @Override
    public boolean empty() {
        return _headNode == null && _tailNode == null;
    }

    private DoubleListNode<T> searchNode(int position) {
        DoubleListNode<T> node_iter = _headNode;

        int i = 0;
        while (i < position && node_iter != null) {
            ++i;
            node_iter = node_iter.getNext();
        }

        return node_iter;
    }

    @Override
    public boolean insert(int position, T value) throws Exception {
        if (position == _size) {
            this.push_back(value);
            return true;
        }

        DoubleListNode<T> new_node = new DoubleListNode<>(value);

        var searched_node = searchNode(position);

        if (searched_node == null) {
            throw new List_ElementNotFoundException();
        }

        _size++;

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
        DoubleListNode<T> nodeIter = searchNode(position);

        if (_headNode == null){
            throw new List_TryingDeleteFromEmptyContain();
        }

        if (nodeIter == null) {
            throw new List_ElementNotFoundException();
        }

        _size--;

        nodeIter.getPrev().setNext( nodeIter.getNext() );
        nodeIter.getNext().setPrev( nodeIter.getPrev() );

        return true;
    }

    @Override
    public void push_back(T value) {

        DoubleListNode<T> newNode = new DoubleListNode<>(value);
        _size++;

        if ( isFirstNode(newNode) )
            return;

        newNode.setPrev(_tailNode);
        _tailNode.setNext(newNode);
        _tailNode = newNode;
    }

    @Override
    public void push_front(T value) {

        DoubleListNode<T> newNode = new DoubleListNode<>(value);
        _size++;

        if ( isFirstNode(newNode) )
            return;

        newNode.setNext(_headNode);
        _headNode.setPrev(newNode);
        _headNode = newNode;
    }

    @Override
    public T pop_back() throws Exception {
         T deleted_value = _tailNode.getValue();

         if (_headNode == null){
             throw new List_TryingDeleteFromEmptyContain();
         }

         _size--;

         if (_headNode == _tailNode) {
             _headNode = null;
             _tailNode = null;

             return deleted_value;
         }

         _tailNode = _tailNode.getPrev();
         _tailNode.setNext(null);

         return deleted_value;
    }

    @Override
    public T pop_front() throws Exception {
        T deleted_value = _headNode.getValue();

        if (_headNode == null){
            throw new List_TryingDeleteFromEmptyContain();
        }

        _size--;

        if (_headNode == _tailNode) {
            _headNode = null;
            _tailNode = null;

            return deleted_value;
        }

        _headNode = _headNode.getNext();
        _headNode.setPrev(null);

        return deleted_value;
    }

    @Override
    public DoubleListNode<T> getHead(){
        return _headNode;
    }

    public DoubleListNode<T> getTail(){
        return _tailNode;
    }
}
