package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import edu.grinnell.csc207.util.ArrayUtils;

/**
 * Something that sorts using Quicksort.
 *
 * @param <T> The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class Quicksorter<T> implements Sorter<T> {
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
  public Quicksorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // Quicksorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using Quicksort.
   *
   * @param values an array to sort.
   *
   * @post The array has been sorted according to some order (often one given to the constructor).
   * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {

    T[] sorted = subdivide(values, 0, values.length);
    for (int i = 0; i < sorted.length; i++) {
      values[i] = sorted[i];
    } // for
  } // sort(T[])

  /**
   * Sorts and subdivide an array.
   * @param values
   * @param lb
   * @param ub
   * @return Sorted array
   */
  T[] subdivide(T[] values, int lb, int ub) {
    if (lb == ub) {
      return values;
    } // if
    int l = lb;
    int e = lb;
    int u = lb;
    T pivot = values[(int) (Math.random() * values.length)];
    while (u < ub) {
      if (order.compare(values[u], pivot) > 0) {
        u++;
      } else if (order.compare(values[u], pivot) == 0) {
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
    values = subdivide(values, lb, l);
    values = subdivide(values, e, ub);

    return values;
  } // subdivide
} // class Quicksorter
