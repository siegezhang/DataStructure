package basic.sort.heapsort;

import java.util.Arrays;

/**
 * Created by siege on 2017/9/2.
 */
public class HeapSortTest {
    public static void main(String[] args) {
        int arr[]={51, 46, 20, 18, 65, 97, 82, 30, 77, 50};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int[] arr){
        int i;
        for ( i = arr.length/2-1; i >=0 ; i--) {
            adjustHeap(arr,i,arr.length-1);
        }
        for (i= arr.length-1;i>=0;i--){
            int temp=arr[0];
            arr[0]=arr[i];
            arr[i]=temp;
            adjustHeap(arr,0,i-1);

        }
    }

    private static void adjustHeap(int[] arr,int i, int len) {
        int temp,j;
        temp=arr[i];
        for ( j = 2*i; j <len ; j*=2) {
            if (j<len&&arr[j]<arr[j+1])
                ++j;
                if (temp>=arr[j])
                    break;
                arr[i]=arr[j];
                i=j;
            arr[i]=temp;
        }
    }
}
