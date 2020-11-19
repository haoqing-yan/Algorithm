package SortSolution;

/**
 * 排序算法
 *
 * @author donny yan
 * Create 2020/11/12
 */
public class SortSolution {

    public static void main(String[] args) {
        int[] array = {1, 52, 525, 23, 57, 7, 68, 8, 6, 97, 12, 5445, 4};
        int[] sortArray = bubbleSort(array, "DESC");
        for (int one : sortArray) {
            System.out.print(" " + one);
        }
    }

    /**
     * 冒泡排序
     *
     * @param array 要排序的数组
     * @param type  排序方式    ASC -- 正序 DESC -- 倒序排序
     * @return 返回排序好的数组
     */
    public static int[] bubbleSort(int[] array, String type) {

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
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

        return array;
    }

    /**
     * 快速排序
     *
     * @param array 要排序的数组
     * @param type  排序方式    ASC -- 正序 DESC -- 倒序排序
     * @return 返回排序好的数组
     */
    public static int[] quickSort(int[] array, String type) {

        return array;
    }

}
