import java.util.ArrayList;

public class SortedArrayList<E extends Comparable<E>> extends ArrayList<E> {
    /*
     * REFERENCES:
     * Insertion Sort example found here: https://ncl.instructure.com/courses/39910/files/4911744?wrap=1
     * Sorts generic ArrayList parameter based on the integer compareTo method.
     * Original author: Konrad Dabrowski
     * Modifying author: Victoria Kundu
     */
    public ArrayList<E> insertionSort(ArrayList<E> a) {
        for (int i = 1; i < a.size(); i++) {
            E value = a.get(i);
            int j;
            for (j = i; j > 0; j--) {
                if (a.get(j-1).compareTo(value) < 0) {
                    break;
                } else {
                    a.set(j, a.get(j - 1));
                }
            } a.set(j, value);
        } return a;
    }
}
