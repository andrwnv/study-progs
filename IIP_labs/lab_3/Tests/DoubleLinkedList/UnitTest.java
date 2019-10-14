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
    void test_push_back_function(){
        DoubleLinkedList<Integer> test_list = new DoubleLinkedList<>();

        int elem_count = 100;
        for (int i = 0; i < elem_count; ++i){
            test_list.push_back(i);
        }

        DoubleListNode<Integer> iterator = test_list.getHead();
        for (int i = 0; i < elem_count || iterator != null; ++i, iterator = iterator.getNext()){
            assertSame(i, iterator.getValue());
        }
    }

    @Test
    @DisplayName("Test push_front function")
    void test_push_front_function(){
        DoubleLinkedList<Integer> test_list = new DoubleLinkedList<>();

        int elem_count = 100;
        for (int i = 0; i < elem_count; ++i){
            test_list.push_front(i);
        }

        DoubleListNode<Integer> iterator = test_list.getTail();
        for (int i = 0; i < elem_count || iterator != null; ++i, iterator = iterator.getPrev()){
            assertSame(i, iterator.getValue());
        }
    }

    @Test
    @DisplayName("Test size function")
    void test_size_function(){
        DoubleLinkedList<Integer> test_list = new DoubleLinkedList<>();

        int elem_count = 100;
        for (int i = 0; i < elem_count; ++i){
            test_list.push_back(i);
        }

        assertEquals(test_list.size(), elem_count);
    }

    @Test
    @DisplayName("Test clear function")
    void test_clear_func(){
        DoubleLinkedList<Integer> test_list = new DoubleLinkedList<>();

        int elem_count = 100;
        for (int i = 0; i < elem_count; ++i){
            test_list.push_back(i);
        }

        test_list.clear();
        assertNull(test_list.getHead());
    }

    @Test
    @DisplayName("Test pop_back for empty contain")
    void test_pop_back_func_exception(){
        DoubleLinkedList<Integer> test_list = new DoubleLinkedList<>();

        assertThrows(List_TryingDeleteFromEmptyContain.class, () -> {
            try {
                test_list.pop_back();
            } catch (Exception ex) {
                throw new List_TryingDeleteFromEmptyContain();
            }
        });
    }

    @Test
    @DisplayName("Test pop_front for empty contain")
    void test_pop_front_func(){
        DoubleLinkedList<Integer> test_list = new DoubleLinkedList<>();

        assertThrows(List_TryingDeleteFromEmptyContain.class, () -> {
            try {
                test_list.pop_front();
            } catch (Exception ex) {
                throw new List_TryingDeleteFromEmptyContain();
            }
        });
    }

    @Test
    void test_insert_function(){
        DoubleLinkedList<Integer> test_list = new DoubleLinkedList<>();

        int elem_count = 100;
        for (int i = 0; i < elem_count; ++i){
            test_list.push_back(i);
        }

        try {
            test_list.insert(test_list.size(), (int) Math.pow(test_list.size(), 2));
        } catch (Exception ex) {
            return;
        }

        assertEquals(test_list.getTail().getValue(), (int) Math.pow(test_list.size() - 1, 2));
    }

    @Test
    @DisplayName("Test erase with undeclared position")
    void test_erase_function(){
        DoubleLinkedList<Integer> test_list = new DoubleLinkedList<>();

        int elem_count = 100;
        for (int i = 0; i < elem_count; ++i){
            test_list.push_back(i);
        }

        assertThrows(List_ElementNotFoundException.class, () -> {
            try {
                test_list.erase(120);
            } catch (Exception ex) {
                throw new List_ElementNotFoundException();
            }
        });
    }
}
