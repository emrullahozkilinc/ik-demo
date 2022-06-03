package hafta1;

import java.util.*;
import java.io.*;
import java.util.stream.IntStream;

class TwoSum {

    public static int TwoSum(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int sum1 = arr[i];
            int[] subarr = System.arraycopy(arr,3,);
            int[] subarr1 = Arrays.stream(arr).filter(x -> x != sum1).toArray();
            for (int j = 0; j < subarr.length; j++) {


            }
        }
        return arr[0];
    }

    public static void main (String[] args) {
        // keep this function call here
        Scanner s = new Scanner(System.in);
        System.out.print(TwoSum(new int[] {17, 4, 5, 6, 10, 11, 4, -3, -5, 3, 15, 2, 7}));
    }

}
