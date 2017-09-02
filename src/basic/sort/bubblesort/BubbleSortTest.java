package basic.sort.bubblesort;

import java.util.Arrays;

/**
 * Created by siege on 2017/9/2.
 */
public class BubbleSortTest {
    public static void main(String[] args) {
        int arr[]={1,3,2,4,9,5,6,7,8};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr){
        int n=arr.length;
        int temp;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <n-i-1; j++) {
                if (arr[j+1]<arr[j]){
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
    }
}
