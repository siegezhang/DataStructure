package basic.sort.quicksort;

import java.util.Arrays;

/**
 * Created by siege on 2017/8/30.
 */
public class QuickSort {
    static int a[],n;
    public static void main(String[] args) {
        a=new int[]{6,1,2,7,9,3,4,5,10,8};
        quickSort(0,9);
        System.out.println(Arrays.toString(a));
    }

    public static void quickSort(int left,int right){
        int i,j,t,temp;
        if (left>right)
            return;
        temp=a[left];
        i=left;
        j=right;
        while (i!=j){
            while (a[j]>=temp&&i<j)
                j--;
            while (a[i]<=temp&&i<j)
                i++;
            if (i<j){
                t=a[i];
                a[i]=a[j];
                a[j]=t;
            }
        }
        a[left]=a[i];
        a[i]=temp;
        quickSort(left,i-1);
        quickSort(i+1,right);
        return;
    }
}
