package com.targetindia.programs;
import java.util.Arrays;

interface Sortable
{
    void sort(int[] arr);
}
class BubbleSort implements Sortable
{
    public void sort(int[] arr)
    {
        boolean swapped;
        for(int i=0;i<arr.length-1;i++)
        {
            swapped=false;
            for(int j=0;j<arr.length-1;j++)
            {
                if(arr[j]>arr[j+1])
                {
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                    swapped=true;
                }
            }
            if(!swapped) break;
        }

    }
}

class SelectionSort implements Sortable
{
    public void sort(int[] arr)
    {
        for(int i=0;i< arr.length-1;i++)
        {
            int small=i;
            for(int j=i+1;j<arr.length;j++)
            {
                if(arr[j]<arr[small])
                {
                    small=j;
                }
            }
            int temp=arr[small];
            arr[small]=arr[i];
            arr[i]=temp;
        }
    }
}

class MergeSort implements Sortable {
    private void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0; i < n1; ++i) {
            L[i] = arr[l + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = arr[m + 1 + j];
        }
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    private void sort(int arr[], int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            sort(arr, l, m);
            sort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }
    @Override
    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }
}
class Sorter
{
    public void sort(Sortable algorithm,int[] arr)
    {
        algorithm.sort(arr);
    }
}
public class SortingTest {
    public static void main(String[] args)
    {
        Sorter s=new Sorter();

        //Bubble sort
        int[] bubbleSortArray=new int[]{6,2,4,1,7};
        System.out.println("Using Bubble Sort:");
        System.out.println("Original array: "+ Arrays.toString(bubbleSortArray));
        s.sort(new BubbleSort(),bubbleSortArray);
        System.out.println("Sorted array: "+Arrays.toString(bubbleSortArray));

        //Selection sort
        int[] selectionSortArray=new int[]{8,3,6,4,2};
        System.out.println("Using Selection Sort:");
        System.out.println("Original array: "+Arrays.toString(selectionSortArray));
        s.sort(new SelectionSort(),selectionSortArray);
        System.out.println("Sorted array: "+Arrays.toString(selectionSortArray));

        //Merge sort
        int[] mergeSortArray=new int[]{10,6,3,8,1};
        System.out.println("Using Merge Sort:");
        System.out.println("Original array: "+Arrays.toString(mergeSortArray));
        s.sort(new MergeSort(),mergeSortArray);
        System.out.println("Sorted array: "+Arrays.toString(mergeSortArray));
    }
}
