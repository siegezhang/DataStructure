package basic.sort.insertsort;

import java.util.Arrays;

/**
 * Created by siege on 2017/9/2.
 */
public class InsertSortTest {
    public static void main(String[] args) {
        int arr[]={1,8,2,4,77,23,90,2,9};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    public static void insertSort(int[] arr){
        int i,j;
        int n=arr.length;
        int target;
        for (i = 1; i < n; i++) {
            j=i;
            target=arr[i];
            while (j>0&&target<arr[j-1]){
                arr[j]=arr[j-1];
                j--;
            }
            arr[j]=target;
        }
    }

}
