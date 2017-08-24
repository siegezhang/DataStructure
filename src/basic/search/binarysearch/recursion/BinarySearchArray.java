package basic.search.binarysearch.recursion;

/**
 * Created by siege on 2017/8/21.
 */
public class BinarySearchArray {
    private int ar[];
    private int index;

    public BinarySearchArray(int  maxSize) {
        ar=new int[maxSize];
        index=0;
    }

    /**
     *Insert values into array (Array must remain sorted, because binary search
     * works on sorted array)
     */
    public void insert(int value){
        int j;
        for (j=0;j<index;j++)
            if (ar[j]>value)
                break;
        for (int x = index; x >j ; x--)
            ar[x]=ar[x-1];
        ar[j]=value;
        index++;
    }

    public Integer binarySearch(int key){
        int lowerBound=0;
        int upperBound=index-1;
        return binarySearchRecursive(key,lowerBound,upperBound);
    }

    public Integer binarySearchRecursive(int key, int lowerBound, int upperBound) {
        int mid;
        mid=(lowerBound+upperBound)/2;
        if (ar[mid]==key){
            System.out.print("\nElement=" + key + " found at position="+ mid);
            return mid;
        }else if (lowerBound>upperBound){
            System.out.print("\nElement=" + key + " not found");
            return index;
        }else if(ar[mid]>key){
            upperBound=mid-1;
            return binarySearchRecursive(key,lowerBound,upperBound);
        }else{
            lowerBound=mid+1;
            return binarySearchRecursive(key,lowerBound,upperBound);
        }
    }

    public void display(){
        System.out.print("Displaying Array: ");
        for (int i = 0; i <index; i++) {
            System.out.print(ar[i] + " ");
        }
    }

    public static void main(String[] args) {
        int maxSize = 10; // Initial size of array
        BinarySearchArray bSearchArray = new BinarySearchArray(maxSize);
        bSearchArray.insert(11);
        bSearchArray.insert(22);
        bSearchArray.insert(33);
        bSearchArray.insert(99);
        bSearchArray.insert(55);
        bSearchArray.insert(77);
        bSearchArray.insert(66);
        bSearchArray.insert(88);
        bSearchArray.insert(44);
        bSearchArray.insert(1);

        bSearchArray.display();

        bSearchArray.binarySearch(88); // FOUND
        bSearchArray.binarySearch(-1); // NOT FOUND
        bSearchArray.binarySearch(73); // NOT FOUND
    }

}
