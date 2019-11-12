package Tests;

import BinaryTree.BinaryTree;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UnitTests {

    @Test
    @DisplayName("Test add item func")
    void test_add_item() {
        BinaryTree<Integer> test_bt = new BinaryTree<Integer>();

        test_bt.addItem(10);
        test_bt.addItem(5);
        test_bt.addItem(6);
        test_bt.addItem(130);
        test_bt.addItem(58);
        test_bt.addItem(14);

        assertSame(6, test_bt.getSize());
    }

    @Test
    @DisplayName("Test remove item func")
    void test_remove_item() {
        BinaryTree<Integer> test_bt = new BinaryTree<Integer>();

        test_bt.addItem(10);
        test_bt.addItem(5);
        test_bt.addItem(6);
        test_bt.addItem(130);
        test_bt.addItem(58);
        test_bt.addItem(14);

        test_bt.deleteItem(10);
        test_bt.deleteItem(10);
        test_bt.deleteItem(5);

        assertEquals(4, test_bt.getSize());
    }

    @Test
    @DisplayName("Test search item func")
    void test_search_item() {
        BinaryTree<Integer> test_bt = new BinaryTree<Integer>();

        test_bt.addItem(10);
        test_bt.addItem(5);
        test_bt.addItem(6);
        test_bt.addItem(130);
        test_bt.addItem(58);
        test_bt.addItem(14);

        assertEquals(130, test_bt.searchItem(130));
    }

    @Test
    @DisplayName("Test empty func")
    void test_empty_func() {
        BinaryTree<Integer> test_bt = new BinaryTree<Integer>();

        assertSame(true, test_bt.empty());
    }
}
