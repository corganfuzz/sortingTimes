package sorting;

import java.util.ArrayList;

public class Main {

    static void selectionSort(int[] list) {
        for (int i=0; i<list.length - 1; i++){
            int min = list[i];
            int minIndex = i;

            for (int j = i + 1; j < list.length; j++) {
                if (min > list[j]) {
                    min = list[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                list[minIndex] = list[i];
                list[i] = minIndex;
            }
        }
    }


    static void bubble(int[] list) {
        boolean needNextPass = true;
        for (int k = 1; k < list.length && needNextPass; k++) {
            needNextPass = false;
            for (int i = 0; i < list.length - k; i++) {
                if (list[i] > list[i + 1]) {
                    int temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                    needNextPass = true;
                }
            }
        }
    }

    static void merge(int[] list1, int[] secondList, int[] temp) {
        int current1 = 0;
        int current2 = 0;
        int current3 = 0;

        while (current1 < list1.length && current2 < secondList.length) {
            if (list1[current1] < secondList[current2])
                temp[current3++] = list1[current1++];
            else
                temp[current3++] = secondList[current2++];
        }
        while (current1 < list1.length)
            temp[current3++] = list1[current1++];

        while (current2 < secondList.length)
            temp[current3++] = secondList[current2++];
    }


     static void mergeSort(int[] list) {
        if (list.length > 1) {
            int[] firstHalf = new int[list.length / 2];
            System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
            mergeSort(firstHalf);

            int secondHalfLength = list.length - list.length / 2;
            int[] secondHalf = new int[secondHalfLength];
            System.arraycopy(list, list.length / 2, secondHalf, 0,
                    secondHalfLength);
            mergeSort(secondHalf);
            merge(firstHalf, secondHalf, list);
        }
    }

    static void quick(int[] list) {
        quick(list, 0, list.length - 1);
    }

    static void quick(int[] list, int first, int last) {
        if (last > first) {
            int pivotIndex = partition(list, first, last);
            quick(list, first, pivotIndex - 1);
            quick(list, pivotIndex + 1, last);
        }
    }

    static int partition(int[] list, int first, int last) {
        int pivot = list[first];
        int low = first + 1;
        int high = last;

        while (high > low) {
            while (low <= high && list[low] <= pivot)
                low++;

            while (low <= high && list[high] > pivot)
                high--;

            if (high > low) {
                int temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }

        while (high > first && list[high] >= pivot)
            high--;

        if (pivot > list[high]) {
            list[first] = list[high];
            list[high] = pivot;
            return high;
        } else {
            return first;
        }
    }

    public static void heapSort(int[] list) {
        Heap<Integer> heap = new Heap<>();
        for (int j : list) heap.add(j);
        for (int i = list.length - 1; i >= 0; i--)
            list[i] = heap.remove();
    }

    static class Heap<E extends Comparable<E>> {
        private final java.util.ArrayList<E> list = new java.util.ArrayList<>();

        public void add(E newObject) {
            list.add(newObject);
            int currentIndex = list.size() - 1;

            while (currentIndex > 0) {
                int parentIndex = (currentIndex - 1) / 2;
                if (list.get(currentIndex).compareTo(list.get(parentIndex)) > 0) {
                    E temp = list.get(currentIndex);
                    list.set(currentIndex, list.get(parentIndex));
                    list.set(parentIndex, temp);
                } else
                    break;
                currentIndex = parentIndex;
            }
        }

        public E remove() {
            if (list.size() == 0)
                return null;

            E removedObject = list.get(0);
            list.set(0, list.get(list.size() - 1));
            list.remove(list.size() - 1);

            int currentIndex = 0;
            while (currentIndex < list.size()) {
                int leftChildIndex = 2 * currentIndex + 1;
                int rightChildIndex = 2 * currentIndex + 2;

                if (leftChildIndex >= list.size())
                    break;
                int maxIndex = leftChildIndex;
                if (rightChildIndex < list.size()) {
                    if (list.get(maxIndex).compareTo(list.get(rightChildIndex)) < 0) {
                        maxIndex = rightChildIndex;
                    }
                }

                if (list.get(currentIndex).compareTo(list.get(maxIndex)) < 0) {
                    E temp = list.get(maxIndex);
                    list.set(maxIndex, list.get(currentIndex));
                    list.set(currentIndex, temp);
                    currentIndex = maxIndex;
                } else
                    break;
            }

            return removedObject;
        }
    }

    static void radix(int[] list) {
        for (int order = 1; order < 1000000; order *= 10) {
            @SuppressWarnings("unchecked")
            ArrayList<Integer>[] bucket = new ArrayList[10];

            for (int i = 0; i < bucket.length; i++) {
                bucket[i] = new java.util.ArrayList<>();
            }

            for (int j : list) {
                bucket[(j / order) % 10].add(j);
            }

            int k = 0;
            for (ArrayList<Integer> integers : bucket) {
                if (integers != null) {
                    for (Integer integer : integers) list[k++] = integer;
                }
            }
        }
    }

    static void bubbleExecutioner(int width, int[] list, int arraySize) {
        int[] secondList = new int[arraySize];
        System.arraycopy(list, 0, secondList, 0, list.length);
        long startTime = System.currentTimeMillis();
        bubble(secondList);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.printf("%" + width + "ds|", executionTime);
    }

    static void mergeExecutioner(int width, int[] list, int arraySize) {
        int[] secondList = new int[arraySize];
        System.arraycopy(list, 0, secondList, 0, list.length);
        long startTime = System.currentTimeMillis();
        mergeSort(secondList);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.printf("%" + width + "ds|", executionTime);
    }

    static void quickExecutioner(int width, int[] list, int arraySize) {
        int[] secondList = new int[arraySize];
        System.arraycopy(list, 0, secondList, 0, list.length);
        long startTime = System.currentTimeMillis();
        quick(secondList);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.printf("%" + width + "ds|", executionTime);
    }

    static void heapExecutioner(int width, int[] list, int arraySize) {
        int[] secondList = new int[arraySize];
        System.arraycopy(list, 0, secondList, 0, list.length);
        long startTime = System.currentTimeMillis();
        heapSort(secondList);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.printf("%" + width + "ds|", executionTime);
    }

    static void radixExecutioner(int width, int[] list, int arraySize) {
        int[] secondList = new int[arraySize];
        System.arraycopy(list, 0, secondList, 0, list.length);
        long startTime = System.currentTimeMillis();
        radix(secondList);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.printf("%" + width + "ds|", executionTime);
    }

    static void valuePrinted(int arraySize) {
        int width = 15;
        int[] list = new int[arraySize];
        for (int i =0; i <list.length; i++) {
            list[i] = (int)(Math.random() * 1000000);
        }

        System.out.print("\n| ");

        for (int i =0; i < 7; i++) {
            for (int j = 0; j <width; j++) {
                System.out.print("-");
            }
            System.out.print("| ");
        }

        System.out.printf("\n|%" + width + "d |", arraySize);

        int[] secondList = new int[arraySize];
        System.arraycopy(list, 0, secondList, 0, list.length);

        long startTime = System.currentTimeMillis();
        selectionSort(secondList);

        long endTime = System.currentTimeMillis();

        long executionTime = endTime - startTime;
        System.out.printf("%" + width + "ds|", executionTime);

        bubbleExecutioner(width, list, arraySize);
        mergeExecutioner(width, list, arraySize);
        quickExecutioner(width, list, arraySize);
        heapExecutioner(width, list, arraySize);
        radixExecutioner(width, list, arraySize);
    }

    public static void main(String[] args) {
        System.out.print("|Array size      |Selection Sort  |Bubble Sort     |Merge Sort      |Quick Sort      |Heap Sort       |Radix Sort      |");
        for (int i = 50000; i <=300000; i += 50000) {
            valuePrinted(i);
        }
    }
}
