package com.targetindia.programs;
import java.util.Arrays;
class Array {

    private int[] numbers;
    private int size = 0;
    private int capacity = 5;

    public Array() {
        numbers = new int[capacity];
    }

    public void append(int data) {
        if (size == capacity) { // O(1)
            capacity *= 2; // O(1)
            var newNumbers = new int[capacity]; // O(1)
            for (int i = 0; i < size; i++) { // O(n)
                newNumbers[i] = numbers[i]; // O(1)
            }
            numbers = newNumbers; // O(1) // old object will be garbage collected eventually
        }
        numbers[size++] = data; // O(1)
    } // O(n) --> Linear time complexity

    public void display() {
        StringBuffer sb = new StringBuffer(); // O(1)
        sb.append("[");
        for (int i = 0; i < size; i++) { // O(n)
            sb.append(numbers[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        System.out.println("Array (raw): " + Arrays.toString(numbers)); // O(n)
        System.out.println("Array content: " + sb);
    } // O(n) --> Linear time complexity

    public void prepend(int data) {
        if (size == capacity) {
            capacity *= 2;
            int[] newNumbers = new int[capacity];
            newNumbers[0] = data;  // Set the new data at the start
            for (int i = 0; i < size; i++) {
                newNumbers[i + 1] = numbers[i];  // Shift elements to the right
            }
            numbers = newNumbers;
        } else {
            // Shift elements right
            for (int i = size; i > 0; i--) {
                numbers[i] = numbers[i - 1];
            }
            numbers[0] = data;  // Insert the new element at the beginning
        }
        size++;
    }


    public void deleteAtIndex(int index) {
        if (size == 0) {
            throw new RuntimeException("Empty array; nothing to delete");
        }
        if (index < 0 || index >= size) {
            throw new RuntimeException("Invalid index. Must be >=0 and <" + size);
        }

        int[] newNumbers = new int[capacity];
        for (int i = 0; i < index; i++) {
            newNumbers[i] = numbers[i];
        }
        for (int i = index + 1; i < size; i++) {
            newNumbers[i - 1] = numbers[i];
        }
        numbers = newNumbers;
        size--;
    }

    public void deleteValue(int data){
        if (size == 0) {
            throw new RuntimeException("Empty array; nothing to delete");
        }
            int index = -1;
            int[] newNumbers = new int[capacity];
            for (int i = 0; i < size; i++) {
                if (numbers[i] == data)
                    index = i;
            }
        if (index == -1) {
            throw new RuntimeException("Element not found in the array");
        }
            for (int i = 0; i < index; i++) {
                newNumbers[i] = numbers[i];
            }
            for (int i = index + 1; i < size; i++) {
                newNumbers[i - 1] = numbers[i];
            }
            numbers = newNumbers;
            size--;
    }

    public void insertAtIndex(int data,int index){
        if (size == 0) {
            throw new RuntimeException("Empty array; nothing to delete");
        }
        if (index < 0 || index > size) {
            throw new RuntimeException("Invalid index. Must be >=0 and <" + size);
        }
        if (size == capacity) {
            capacity *= 2;
        }

        int[] newNumbers = new int[capacity];
        for (int i = 0; i < index; i++) {
            newNumbers[i] = numbers[i];
        }

        newNumbers[index] = data;

        for (int i = index + 1; i <= size; i++) {
            newNumbers[i] = numbers[i -1];
        }
      
        numbers = newNumbers;
        size++;
    }

    public void reverse(){
        if (size == 0) {
            throw new RuntimeException("Empty array; nothing to reverse");
        }
        int i=0;
        int j=size - 1;
        int temp=0;
        while(i < j){
            temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
            i++;
            j--;
        }
    }

    public void rotate() {
        if (size == 0) {
            throw new RuntimeException("Empty array; nothing to rotate");
        }
        if (size == 1) {
            return;
        }
        int first = numbers[0];
        for (int i = 1; i < size; i++) {
            numbers[i - 1] = numbers[i];  // Shift every element to the left
        }
        numbers[size - 1] = first;  // Set the first element at the end
    }


}
class Main {
    static Array array = new Array();


    public static void main(String[] args) {
        for (int i : Arrays.asList(19, 40, 29, 58, 55, 38, 31)) {
            array.append(i);  //Java's standard Arrays class do not have an append method.
        }

        int choice;
        int data;
        int index;

        do {
            array.display();
            System.out.println("*** Main Menu ***");
            System.out.println("0. Exit");
            System.out.println("1. Append");
            System.out.println("2. Prepend");
            System.out.println("3. Delete at a given index");
            // Assignment tasks:
            System.out.println("4. Delete a given value");
            System.out.println("5. Insert at a given index");
            System.out.println("6. Reverse");
            System.out.println("7. Rotate"); // first element is removed and appended to the end
            choice = KeyboardUtil.getInt("Enter your choice: ");

            switch (choice) {
                case 0:
                    System.out.println("exiting...");
                    break;
                case 1:
                    data = KeyboardUtil.getInt("Enter data to append: ");
                    array.append(data);
                    break;
                case 2:
                    data = KeyboardUtil.getInt("Enter data to prepend: ");
                    array.prepend(data);
                    break;
                case 3:
                    index = KeyboardUtil.getInt("Enter index to delete value at: ");
                    try {
                        array.deleteAtIndex(index);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 4:
                    data = KeyboardUtil.getInt("Enter the value to delete:");
                    try{
                        array.deleteValue(data);
                    } catch (Exception e){
                        System.err.println(e.getMessage());
                    }
                    break;
                case 5:
                    data = KeyboardUtil.getInt("Enter the value to insert: ");
                    index = KeyboardUtil.getInt("Enter index to insert value at: ");
                    try {
                        array.insertAtIndex(data,index);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 6:
                    try {
                        array.reverse();
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 7:
                    try {
                        array.rotate();
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }


        } while (choice != 0);
    }
}
