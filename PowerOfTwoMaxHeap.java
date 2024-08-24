import java.util.ArrayList;
import java.util.List;

public class PowerOfTwoMaxHeap {
    private List<Integer> heap;
    private int powerOfTwoChildren;

    public PowerOfTwoMaxHeap(int k) {
        this.heap = new ArrayList<>();
        this.powerOfTwoChildren = (int) Math.pow(2, k);
    }

    public void insert(int value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }

    public int popMax() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        int maxValue = heap.get(0);
        int lastValue = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, lastValue);
            heapifyDown(0);
        }

        return maxValue;
    }

    private void heapifyUp(int index) {
        int parentIndex = (index - 1) / powerOfTwoChildren;

        while (index > 0 && heap.get(index) > heap.get(parentIndex)) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / powerOfTwoChildren;
        }
    }

    private void heapifyDown(int index) {
        int largestIndex = index;

        while (true) {
            int firstChildIndex = powerOfTwoChildren * index + 1;

            for (int i = 0; i < powerOfTwoChildren; i++) {
                int currentChildIndex = firstChildIndex + i;
                if (currentChildIndex < heap.size() && heap.get(currentChildIndex) > heap.get(largestIndex)) {
                    largestIndex = currentChildIndex;
                }
            }

            if (largestIndex == index) {
                break;
            }

            swap(index, largestIndex);
            index = largestIndex;
        }
    }

    private void swap(int index1, int index2) {
        int temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public static void main(String[] args) {
        // Test cases
        PowerOfTwoMaxHeap heap = new PowerOfTwoMaxHeap(1); // Binary heap
        heap.insert(10);
        heap.insert(20);
        heap.insert(5);
        heap.insert(30);
        heap.insert(15);

        System.out.println("Max element: " + heap.popMax()); // 30
        System.out.println("Max element: " + heap.popMax()); // 20

        PowerOfTwoMaxHeap largeHeap = new PowerOfTwoMaxHeap(3); // 8-ary heap
        largeHeap.insert(100);
        largeHeap.insert(200);
        largeHeap.insert(300);
        largeHeap.insert(50);

        System.out.println("Max element: " + largeHeap.popMax()); // 300
    }
}

// import java.util.ArrayList;

// public class Heap<T extends Comparable<T>> {
//     private final int childCount;
//     private final ArrayList<T> data;

//     public Heap(int childCount) {
//         this.validateChildCount(childCount);
//         this.childCount = childCount;
//         this.data = new ArrayList<T>();
//     }

//     private void validateChildCount(int childCount) {
//         // ensure childCount is valid
//         // ensure childCount is greater than zero
//         if (childCount <= 0) {
//             throw new IllegalArgumentException("childCount must be greater than zero");
//         }
//         // ensure childCount is a power of 2
//         double logChildCount = Math.log(childCount) / Math.log(2);
//         if (Math.ceil(logChildCount) != Math.floor(logChildCount)) {
//             throw new IllegalArgumentException("childCount must be a power of 2");
//         }
//     }

//     public void insert(T item) {
//         // insert an item into the heap
//         data.add(item);
//         int itemIndex = data.size() - 1;
//         while (itemIndex > 0) {
//             itemIndex = this.swapUp(itemIndex);
//         }
//     }

//     private int swapUp(int childIndex) {
//         // check a child against its parent, and swap it if necessary to satisfy heap
//         // property
//         T childValue = data.get(childIndex);
//         int parentIndex = (int) Math.floor((float) (childIndex - 1) / childCount);
//         if (parentIndex >= 0) {
//             T parentValue = data.get(parentIndex);
//             if (childValue.compareTo(parentValue) > 0) {
//                 data.set(parentIndex, childValue);
//                 data.set(childIndex, parentValue);
//                 return parentIndex;
//             }
//         }
//         return -1;
//     }

//     public T popMax() {
//         // pop the max value off the heap, return null if none remain
//         if (data.size() > 0) {
//             T maxItem = data.get(0);
//             if (data.size() > 1) {
//                 T lastItem = data.remove(data.size() - 1);
//                 data.set(0, lastItem);
//                 int itemIndex = 0;
//                 while (itemIndex >= 0) {
//                     itemIndex = this.swapDown(itemIndex);
//                 }
//             }
//             return maxItem;
//         } else {
//             return null;
//         }
//     }

//     private int swapDown(int parentIndex) {
//         // check a parent against all children and swap it with the highest child if
//         // necessary to satisfy heap property
//         T parentValue = data.get(parentIndex);
//         // determine largest child
//         int largestChildIndex = 0;
//         T largestChildValue = null;
//         for (int i = 0; i < childCount; i++) {
//             int childIndex = childCount * parentIndex + i + 1;
//             if (childIndex < data.size() - 1) {
//                 T childValue = data.get(childIndex);
//                 if (largestChildValue == null || childValue.compareTo(largestChildValue) > 0) {
//                     largestChildIndex = childIndex;
//                     largestChildValue = childValue;
//                 }
//             }
//         }
//         // perform swap if necessary
//         if (largestChildValue != null && parentValue.compareTo(largestChildValue) < 0) {
//             data.set(parentIndex, largestChildValue);
//             data.set(largestChildIndex, parentValue);
//             return largestChildIndex;
//         }
//         return -1;
//     }
// }