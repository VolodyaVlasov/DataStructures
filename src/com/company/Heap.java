package com.company;

class Heap {
    public int[] HeapArray;
    public int last;

    public Heap() {
        HeapArray = null;
        last = 0;
    }

    public void MakeHeap(int[] a, int depth) {
        HeapArray = new int[(int) (Math.pow(2, depth + 1) - 1)];
        for (int value : a) {
            Add(value);
        }
    }

    public int GetMax() {
        if (last == 0) {
            return -1;
        }
        int result = HeapArray[0];
        HeapArray[0] = HeapArray[last - 1];
        HeapArray[last - 1] = 0;
        int index = 0;
        while (2 * index + 2 < HeapArray.length) {
            int cheak = HeapArray[2 * index + 1] > HeapArray[2 * index + 2] ? 2 * index + 1 : 2 * index + 2;
            if (HeapArray[index] < HeapArray[cheak]) {
                int temp = HeapArray[index];
                HeapArray[index] = HeapArray[cheak];
                HeapArray[cheak] = temp;
                index = cheak;
            } else {
                break;
            }

        }
        last--;
        return result;
    }

    public boolean Add(int key) {
        if (last >= HeapArray.length) {
            return false;
        }
        int i = last;
        int p = (i - 1) / 2;
        HeapArray[i] = key;
        while (i > 0 && HeapArray[p] < HeapArray[i]) {
            int temp = HeapArray[i];
            HeapArray[i] = HeapArray[p];
            HeapArray[p] = temp;
            i = p;
            p = (i - 1) / 2;
        }
        last++;
        return true;
    }
}