package com.targetindia.queues;

public interface Queue<T> {
    public void enqueue(T item);

    public T peek();

    public T dequeue();

    public boolean isEmpty();

}
