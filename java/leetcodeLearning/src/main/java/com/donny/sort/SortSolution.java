package com.donny.sort;

public class SortSolution {
    private int[] sortArray;

    public int[] getSortArray() {
        return sortArray;
    }

    public void setSortArray(int[] sortArray) {
        this.sortArray = sortArray;
    }

    /**
     * 冒泡排序
     *
     * @param array 要排序的数组
     * @param type  排序方式    ASC -- 正序 DESC -- 倒序排序
     * @return 返回排序好的数组
     */
    public void bubbleSort(int[] array, String type) {

        for (int i = 0; i < array.length; i++) {
            // 循环每一个与后面所有相比
            for (int j = 0; j < array.length - i - 1; j++) {
                // 根据输入的排序选择排序方式
                if ("ASC".equals(type)) {
                    if (array[j] > array[j + 1]) {
                        int tmp = array[j + 1];
                        array[j + 1] = array[j];
                        array[j] = tmp;
                    }
                }
                if ("DESC".equals(type)) {
                    if (array[j] < array[j + 1]) {
                        int tmp = array[j + 1];
                        array[j + 1] = array[j];
                        array[j] = tmp;
                    }
                }

            }
        }

    }

    /**
     * 快速排序
     *
     * @param array 未排序的数组
     * @param low   更小的数
     * @param high  更大的数
     * @return 返回排好序的数组
     */
    public static void quickSort(int[] array, int low, int high) {
        int benchmark = 0;
        int i = 0;
        int j = 0;

        if (low >= high) {
            return;
        }
        // benchmark 就是基准数,这里就是每个数组的第一个
        benchmark = array[low];
        i = low;
        j = high;
        while (i < j) {
            //右边当发现小于p的值时停止循环
            while (array[j] >= benchmark && i < j) {
                j--;
            }
            //左边当发现大于p的值时停止循环
            while (array[i] <= benchmark && i < j) {
                i++;
            }
            int temp = array[j];
            array[j] = array[i];
            array[i] = temp;
        }
        array[low] = array[i];//这里的arr[i]一定是停小于 benchmark 的，经过i、j交换后i处的值一定是小于p的(j先走)
        array[i] = benchmark;
        quickSort(array, low, j - 1);  //对左边快排
        quickSort(array, j + 1, high); //对右边快排

    }
}
