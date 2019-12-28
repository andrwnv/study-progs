package Tests.DoubleLinkedList;

import LinkedList.DoubleLinkedList;
import LinkedList.DoubleListNode;

import LinkedList.ListExceptions.List_ElementNotFoundException;
import LinkedList.ListExceptions.List_TryingDeleteFromEmptyContain;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UnitTest {
    @Test
    @DisplayName("Test push_back function")
    void testPushBackFunction(){
        DoubleLinkedList<Integer> linkedList = new DoubleLinkedList<>();

        int elemCount = 100;
        for (int i = 0; i < elemCount; ++i){
            linkedList.push_back(i);
        }

        DoubleListNode<Integer> iterator = linkedList.getHead();
        for (int i = 0; i < elemCount || iterator != null; ++i, iterator = iterator.getNext()){
            assertSame(i, iterator.getValue());
        }
    }

    @Test
    @DisplayName("Test push_front function")
    void testPushFrontFunction(){
        DoubleLinkedList<Integer> linkedList = new DoubleLinkedList<>();

        int elemCount = 100;
        for (int i = 0; i < elemCount; ++i){
            linkedList.push_front(i);
        }

        DoubleListNode<Integer> iterator = linkedList.getTail();
        for (int i = 0; i < elemCount || iterator != null; ++i, iterator = iterator.getPrev()){
            assertSame(i, iterator.getValue());
        }
    }

    @Test
    @DisplayName("Test size function")
    void testSizeFunction(){
        DoubleLinkedList<Integer> linkedList = new DoubleLinkedList<>();

        int elemCount = 100;
        for (int i = 0; i < elemCount; ++i){
            linkedList.push_back(i);
        }

        assertEquals(linkedList.size(), elemCount);
    }

    @Test
    @DisplayName("Test clear function")
    void testClearFunc(){
        DoubleLinkedList<Integer> linkedList = new DoubleLinkedList<>();

        int elemCount = 100;
        for (int i = 0; i < elemCount; ++i){
            linkedList.push_back(i);
        }

        linkedList.clear();
        assertNull(linkedList.getHead());
    }

    @Test
    @DisplayName("Test pop_back for empty contain")
    void testPopBackFuncException(){
        DoubleLinkedList<Integer> linkedList = new DoubleLinkedList<>();

        assertThrows(List_TryingDeleteFromEmptyContain.class, () -> {
            try {
                linkedList.pop_back();
            } catch (Exception ex) {
                throw new List_TryingDeleteFromEmptyContain();
            }
        });
    }

    @Test
    @DisplayName("Test pop_front for empty contain")
    void testPopFrontFunc(){
        DoubleLinkedList<Integer> linkedList = new DoubleLinkedList<>();

        assertThrows(List_TryingDeleteFromEmptyContain.class, () -> {
            try {
                linkedList.pop_front();
            } catch (Exception ex) {
                throw new List_TryingDeleteFromEmptyContain();
            }
        });
    }

    @Test
    void testInsertFunction(){
        DoubleLinkedList<Integer> linkedList = new DoubleLinkedList<>();

        int elemCount = 100;
        for (int i = 0; i < elemCount; ++i){
            linkedList.push_back(i);
        }

        try {
            linkedList.insert(linkedList.size(), (int) Math.pow(linkedList.size(), 2));
        } catch (Exception ex) {
            return;
        }

        assertEquals(linkedList.getTail().getValue(), (int) Math.pow(linkedList.size() - 1, 2));
    }

    @Test
    @DisplayName("Test erase with undeclared position")
    void testEraseFunction(){
        DoubleLinkedList<Integer> linkedList = new DoubleLinkedList<>();

        int elemCount = 100;
        for (int i = 0; i < elemCount; ++i){
            linkedList.push_back(i);
        }

        assertThrows(List_ElementNotFoundException.class, () -> {
            try {
                linkedList.erase(120);
            } catch (Exception ex) {
                throw new List_ElementNotFoundException();
            }
        });
    }
}
