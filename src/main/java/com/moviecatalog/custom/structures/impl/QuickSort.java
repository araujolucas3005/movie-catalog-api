package com.moviecatalog.custom.structures.impl;

import com.moviecatalog.custom.structures.QuickSortInter;

public class QuickSort implements QuickSortInter {
    public void sort(int[] array, int left, int right) {
        int i = left;
        int j = right;

        int pivot = array[(left + right) / 2];

        while (i < j) {
            while (array[i] < pivot) {
                i++;
            }
            while (array[j] > pivot) {
                j--;
            }

            if (i <= j) {
                int aux = array[i];
                array[i] = array[j];
                array[j] = aux;
                i++;
                j--;
            }
        }

        if (j > left) {
            sort(array, left, j);
        }
        if (i < right) {
            sort(array, i, right);
        }
    }
}
