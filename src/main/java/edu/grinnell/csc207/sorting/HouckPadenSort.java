package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import edu.grinnell.csc207.util.ArrayUtils;

/**
 * Something that sorts using a hybrid of quicksort and insertion sort.
 *
 * @param <T> The types of values that are sorted.
 * 
 * @author Paden Houck.
 * @author Samuel A. Rebelsky
 */

public class HouckPadenSort<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter using a particular comparator.
   *
   * @param comparator The order in which elements in the array should be ordered after sorting.
   */
  public HouckPadenSort(Comparator<? super T> comparator) {
    this.order = comparator;
  } // InsertionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using a variation of count sort.
   *
   * @param values an array to sort.
   *
   * @post The array has been sorted according to some order (often one given to the constructor).
   * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    T[] sorted = hybridSort(values,0,values.length);
    for (int i = 0; i < sorted.length; i++) {
      values[i] = sorted[i];
    } // for
  } // sort(T[])

  public T[] hybridSort(T[] values, int lb, int ub) {
    if (ub - lb <= 10) {
      return insertionSort(values, lb, ub);
    } else {
      return quickSort(values, lb, ub);
    }
  }

  public T[] insertionSort(T[] values, int lb, int ub) {
    for (int i = lb + 1; i < ub; i++) {
      if (order.compare(values[i - 1], values[i]) > 0) {
        ArrayUtils.swap(values, i - 1, i);
        i = Math.max(i - 2, lb);
      } // if
    } // for
    return values;
  } // sort(T[])

  /**
   * (Quicksort) Sorts and subdivide an array.
   * @param values
   * @param lb
   * @param ub
   * @return Sorted array
   */
  T[] quickSort(T[] values, int lb, int ub) {
    if (lb == ub) {
      return values;
    } // if
    int l = lb;
    int e = lb;
    int u = lb;
    int pa = (int) (Math.random() * values.length);
    int pb = (int) (Math.random() * values.length);
    int pc = (int) (Math.random() * values.length);
    T pivot = values[Math.max(Math.min(pa, pb), Math.min(Math.max(pa, pb), pc))];
    while (u < ub) {
      int compare = order.compare(values[u], pivot);
      if (compare > 0) {
        u++;
      } else if (compare == 0) {
        ArrayUtils.swap(values, u, e);
        u++;
        e++;
      } else {
        ArrayUtils.swap(values, u, e);
        ArrayUtils.swap(values, e, l);
        u++;
        e++;
        l++;
      } // if else
    } // while
    values = hybridSort(values, lb, l);
    values = hybridSort(values, e, ub);

    return values;
  } // quicksort

} // class InsertionSorter
