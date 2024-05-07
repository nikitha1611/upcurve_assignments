package com.targetindia.programs;

import com.targetindia.utils.KeyboardUtil;

import java.util.InputMismatchException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int choice;
        int data;
        BinarySearchTree bst = new BinarySearchTree();

        Random r = new Random();
        for (int i = 0; i < 15; i++) {
            bst.insert(r.nextInt(500));
        }

        while ((choice = menu()) != 0) {
            switch (choice) {
                case 1:
                    data = KeyboardUtil.getInt("Enter data to insert: ");
                    bst.insert(data);
                    break;
                case 2:
                    bst.display();
                    break;
                case 3:
                    bst.preorderDisplay();
                    break;
                case 4:
                    bst.inorderDisplay();
                    break;
                case 5:
                    bst.postorderDisplay();
                    break;
                case 6:
                    try {
                        data = KeyboardUtil.getInt("Enter data to delete: ");
                        bst.delete(data);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid type for data. Please try with integers only.");
                    }
                    break;
                case 7:
                    System.out.println("Smallest value: " + bst.smallest(bst.root));
                    break;
                case 8:
                    System.out.println("Largest value: " + bst.largest(bst.root));
                    break;
                case 9:
                    data = KeyboardUtil.getInt("Enter data to find its depth: ");
                    int depth = bst.findDepth(data);
                    if (depth == -1) {
                        System.out.println("Value not found in the tree.");
                    } else {
                        System.out.println("Depth of the value: " + depth);
                    }
                    break;
                case 10:
                    System.out.println("Height of the tree: " + bst.treeHeight());
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Exiting...");
    }

    private static int menu() {
        System.out.println("*** Main menu ***");
        System.out.println("0. Exit");
        System.out.println("1. Insert");
        System.out.println("2. Display");
        System.out.println("3. Preorder traversal");
        System.out.println("4. Inorder traversal");
        System.out.println("5. Postorder traversal");
        System.out.println("6. Delete");
        // TODO: tasks for assignment
        System.out.println("7. Find the smallest value");
        System.out.println("8. Find the largest value");
        System.out.println("9. Find the depth of a value");
        System.out.println("10. Find the height of the tree");

        try {
            return KeyboardUtil.getInt("Enter your choice: ");
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid integer.");
            return -1; // default to a safe exit condition
        }
    }
}
