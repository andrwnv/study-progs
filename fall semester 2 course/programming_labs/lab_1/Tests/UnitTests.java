package Tests;

import BinaryTree.BinaryTree;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UnitTests {

    @Test
    @DisplayName("Test add item func")
    void test_add_item() {
        BinaryTree<Integer> binaryTree = new BinaryTree<Integer>();

        binaryTree.addItem(10);
        binaryTree.addItem(5);
        binaryTree.addItem(6);
        binaryTree.addItem(130);
        binaryTree.addItem(58);
        binaryTree.addItem(14);

        assertSame(6, binaryTree.getSize());
    }

    @Test
    @DisplayName("Test remove item func")
    void test_remove_item() {
        BinaryTree<Integer> binaryTree = new BinaryTree<Integer>();

        binaryTree.addItem(10);
        binaryTree.addItem(5);
        binaryTree.addItem(6);
        binaryTree.addItem(130);
        binaryTree.addItem(58);
        binaryTree.addItem(14);

        binaryTree.deleteItem(10);
        binaryTree.deleteItem(10);
        binaryTree.deleteItem(5);

        assertEquals(4, binaryTree.getSize());
    }

    @Test
    @DisplayName("Test search item func")
    void test_search_item() {
        BinaryTree<Integer> binaryTree = new BinaryTree<Integer>();

        binaryTree.addItem(10);
        binaryTree.addItem(5);
        binaryTree.addItem(6);
        binaryTree.addItem(130);
        binaryTree.addItem(58);
        binaryTree.addItem(14);

        assertEquals(130, binaryTree.searchItem(130));
    }

    @Test
    @DisplayName("Test empty func")
    void test_empty_func() {
        BinaryTree<Integer> binaryTree = new BinaryTree<Integer>();

        assertSame(true, binaryTree.empty());
    }
}
