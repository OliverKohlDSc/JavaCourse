import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {

        // Create an unsorted integer array with the values 0 ... 9
        int[] array = {5,9,1,3,7,0,4,8,2,6};

        System.out.print("Unsorted array: ");
	    for (int meineZahlen : array) {
            System.out.print(meineZahlen+" ");
        }

	    Arrays.parallelSort(array);
        System.out.print("\nSorted array: ");

        for (int meineZahlen : array) {
            System.out.print(meineZahlen+" ");
        }






        // Create an unsorted integer array with the values 0 ... 9
        int[] array2 = {5,9,1,3,7,0,4,8,2,6,50,-3};

        System.out.print("Unsorted array: ");
        for (int meineZahlen : array2) {
            System.out.print(meineZahlen+" ");
        }

        Arrays.parallelSort(array2, 0, 4);
        System.out.print("\nSorted array: ");

        for (int meineZahlen : array2) {
            System.out.print(meineZahlen+" ");
        }

        Collection<Integer> elements = new LinkedList<Integer>();
        for (int i = 0; i < 40; i++) {
            elements.add(i);
        }

        Parallel.For(elements, parameter -> System.out.println(parameter));

        Parallel.ForEach(elements, parameter -> System.out.println(parameter));
    }
}
