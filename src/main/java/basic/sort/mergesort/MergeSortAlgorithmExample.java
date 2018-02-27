package basic.sort.mergesort;

/**
 * Created by siege on 2017/8/24.
 */
public class MergeSortAlgorithmExample {
    static int[] arr = {54, 13, 24, 19, 11, 3, 71, 8};

    public static void main(String[] args) {
        System.out.print("Display array before Merge sorting: ");
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        int[] ar2 = new int[arr.length];
        mergeSort(ar2, 0, arr.length - 1);
        System.out.print("\nDisplay array after Merge sort: ");
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");

    }

    static void mergeSort(int[] workSpace, int left, int right) {
        if (left == right)
            return;
        int middle = (left + right) / 2;
        mergeSort(workSpace, left, middle);
        mergeSort(workSpace, middle + 1, right);
        merging(workSpace, left, middle, right);


    }

    // 合并两个有序子序列 arr[left, ..., middle] 和 arr[middle+1, ..., right]。temp是辅助数组。
    private static void merging(int[] temp, int left, int middle, int right) {
        int i = left;
        int j = middle + 1;
        int k = 0;
        while (i <= middle && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= middle) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        //把数据复制回原数组
        for (i = 0; i < k; ++i) {
            arr[left + i] = temp[i];
        }
    }
}
