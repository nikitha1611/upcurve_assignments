package com.targetindia.queues;

import com.targetindia.stacks.KeyboardUtil;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class Main {

    public static void main(String[] args) {

        Queue<Integer> queue = new ArrayQueue<>();

        int choice;
        while (true) {
            System.out.println("Queue content: " + queue);

            System.out.println("*** Main Menu ***");
            System.out.println("0. Exit");
            System.out.println("1. Enqueue");
            System.out.println("2. Peek");
            System.out.println("3. Dequeue");
            try {
                choice = KeyboardUtil.getInt("Enter your choice: ");
            } catch (InputMismatchException e) {
                choice = -1;
            }

            if (choice == 0) {
                System.out.println("Exiting...");
                break;
            }

            if (choice == 1) {
                try {
                    var data = KeyboardUtil.getInt("Enter data to enqueue: ");
                    queue.enqueue(data);
                } catch (InputMismatchException e) {
                    System.out.println("Invalid data type. Try only with integers.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (choice == 2) {
                try {
                    System.out.println("Front of the queue contains: " + queue.peek());
                } catch (NoSuchElementException e) {
                    System.out.println("Queue is empty, nothing to peek!");
                }
            } else if (choice == 3) {
                try {
                        var poppedData = queue.dequeue();
                        System.out.println("Dequeued data is " + poppedData);
                } catch (Exception e) {
                    System.out.println("Queue is empty, nothing to dequeue!");
                }

            } else {
                System.out.println("Invalid choice. Please try again.");
            }

        }
    }
}
