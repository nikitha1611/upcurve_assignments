package com.targetindia.queues;

import java.util.NoSuchElementException;

public class ArrayQueue<T> implements Queue<T> {
    private int capacity = 5;
    private Object[] elements;
    private int front = -1;
    private int rear = -1;

    public ArrayQueue() {
        elements = new Object[capacity]; // O(1)
    } // O(1)

    public ArrayQueue(int capacity) {
        this.capacity = capacity; // O(1)
        elements = new Object[capacity]; // O(1)
    } // O(1)

    @Override
    public void enqueue(T item) {
        if (rear == capacity - 1) { // O(1)
            grow();  // O(n)
        }
        else if (front == -1 && rear == -1) {
            front = 0;
            rear = 0;
            elements[rear] = item;
        }
        else {
            rear++;
            elements[rear] = item;
        }
    }

    private void grow() {
        int newCapacity = capacity * 2;
        Object[] newElements = new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        elements = newElements;
        capacity = newCapacity;
    }

    @Override
    public T peek() {
        if (front == -1) { // O(1)
            throw new NoSuchElementException("Queue is empty!"); // O(1)
        }
        return (T) elements[front]; // O(1)
    } // O(1)

    @Override
    public T dequeue() {
        if ((front == -1 && rear == -1) || front == rear+1) { // O(1)
            throw new NoSuchElementException("Queue is empty!"); // O(1)
        }
        var data = elements[front]; // O(1)
        elements[front] = null; // O(1)
        front++; // O(1)
        return (T) data; // O(1)
    } // O(1)

    @Override
    public boolean isEmpty() {
        return front == -1; // O(1)
    } // O(1)

    @Override
    public String toString() {
        if (front == -1 && rear == -1) { // O(1)
            return "[]"; // O(1)
        }

        StringBuilder sb = new StringBuilder(); // O(1)
        sb.append("["); // O(M)
        for (int i = front; i < rear; i++) { // O(N)
            sb.append(elements[i]); // O(M)
            sb.append(", "); // O(M)
        }// O(M)
        sb.append(elements[rear]);
        sb.append("]"); // O(M)
        return sb.toString(); // O(1)
    } // O(N)
}

