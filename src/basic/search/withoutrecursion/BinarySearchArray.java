package basic.search.withoutrecursion;

/**
 * Created by siege on 2017/8/23.
 */
public class BinarySearchArray {
    private int ar[];
    private int index;

    public BinarySearchArray(int maxSize) {
        ar = new int[maxSize];
    }

    public void insert(int value){
        int j;
        for ( j = 0; j <index; j++) {
            if (ar[j]>value)
                break;
        }
        for (int x = index; x >j; x--) {
            ar[x]=ar[x-1];
        }
        ar[j]=value;
        index++;
    }

    public Integer binarySearch(int key){
        int low=0;
        int up=index-1;
        int mid=0;

        while (true){
            mid=(low+up)/2;
            if (low>up){
                System.out.print("\nElement=" + key + " not found");
                return index;
            }else if(ar[mid]==key){
                System.out.print("\nElement=" + key + " found at position="   + mid);
                return mid;
            }else if(ar[mid]>key){
                up=mid-1;
            }else {
                low=mid+1;
            }
        }
    }

    public void display(){
        System.out.print("Displaying Array: ");
        for (int i = 0; i < index; i++) {
            System.out.print(ar[i] + " ");
        }
    }

    public static void main(String[] args) {
        int maxSize=10;
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
