package com.targetindia.dll;
import java.util.NoSuchElementException;
public class DoublyLinkedList {
    class ListNode {
        ListNode prev;
        int data;
        ListNode next;

        public ListNode(int data) {
            this.data = data;
            this.prev = null; 
            this.next = null; 
        }

    }

    private ListNode head;
    private ListNode tail;
    private int length;


    public int size() {
        return length;
    }

    public void prepend(int data) {
        length++;
        var newNode = new ListNode(data);
        if (head == null) {
            head = tail = newNode;
            return;
        }

        newNode.next = head;
        head.prev = newNode;
        head = newNode;
    }

    public void append(int data) {
        length++;
        var newNode = new ListNode(data);
        if (head == null) { // or tail==null
            head = tail = newNode;
            return;
        }

        newNode.prev = tail;
        tail.next = newNode;
        tail = newNode;
    }

    public int indexOf(int data) {
        int index = 0;
        for (var curr = head; curr != null; curr = curr.next) {
            if (data == curr.data) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public void displayForward() {
        var curr = head;
        System.out.print("HEAD <-> ");
        while (curr != null) {
            System.out.print(curr.data);
            System.out.print(" <-> ");
            curr = curr.next;
        }
        System.out.println("TAIL");
    }


    public void displayBackward() {
        var curr = tail;
        System.out.print("TAIL <-> ");
        while (curr != null) {
            System.out.print(curr.data);
            System.out.print(" <-> ");
            curr = curr.prev;
        }
        System.out.println("HEAD");
    }

    public int deleteFirst() throws NoSuchElementException {
        if (head == null) { // or tail==null
            throw new NoSuchElementException("List is empty!");
        }

        length--;

        if (head == tail) {
            // only element in the list
            int data = head.data;
            head = tail = null;
            return data;
        }

        var nodeToDelete = head;
        head = head.next;
        head.prev = null;
        nodeToDelete.next = null;
        return nodeToDelete.data;
    }

    public int deleteLast() throws NoSuchElementException{
        if(tail==null){  // or head==null
            throw new NoSuchElementException("List is empty!");
        }

        length--;

        if(head == tail){
          // only element in the list
            int data = head.data;
            head = tail = null;
            return data;
        }

        var nodeToDelete = tail;
        tail = tail.prev;
        tail.next = null;
        nodeToDelete.prev = null;
        return nodeToDelete.data;
    }

    public void insertAt(int index, int data) throws IndexOutOfBoundsException{
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        if (index == 0) {
            this.prepend(data);
            return;
        }
        if (index == length) {
            this.append(data);
            return;
        }
        var curr = head;
        for (int i = 0; i < index - 1; i++) {
            curr = curr.next;
        }
        var newNode = new ListNode(data);
        newNode.prev = curr;
        newNode.next = curr.next;
        curr.next.prev = newNode;
        curr.next = newNode;
        length++;
    }
    public int deleteAt(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        if(index == 0){
            return this.deleteFirst();
        }
        if(index == length-1){
            return this.deleteLast();
        }

        var curr = head;
        for(int i = 0; i< index-1 ;i++){
            curr = curr.next;
        }
        var nodeToDelete = curr.next;
        curr.next = nodeToDelete.next;
        nodeToDelete.next.prev = curr;
        nodeToDelete.prev=null;
        nodeToDelete.next=null;
        length--;
        return nodeToDelete.data;
    }
}
