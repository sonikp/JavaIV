package week1ass;

public class Assign1 {

/*
2^31 = 2147483648

fix
http://www.codecodex.com/wiki/Binary_search#Java


*/
	
public static void main(String[] args) {
	
	int [] numbers = { 1,2,3,4,123,211 };
	int key = 5;
	int index = binarySearch(numbers, key);
	System.out.println(index);
	
	}

     public static int binarySearch(int[] a, int key) 
     {
         int low = 0;
         int high = a.length - 1;

         while (low <= high) 
         {
             int mid = (low + high) / 2;
             int midVal = a[mid];

             if (midVal < key)
                 low = mid + 1;
             else if (midVal > key)
                 high = mid - 1;
             else
                 return mid; // key found
         }
         return -(low + 1);  // key not found.
     }
}