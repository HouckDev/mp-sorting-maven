package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using merge sort.
 *
 * @param <T> The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class MergeSorter<T> implements Sorter<T> {
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
  public MergeSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // MergeSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using merge sort.
   *
   * @param values an array to sort.
   *
   * @post The array has been sorted according to some order (often one given to the constructor).
   * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    T[] sorted = split(values);
    for (int i = 0; i < sorted.length; i++) {
      values[i] = sorted[i];
    } // for
  } // sort(T[])

  /**
   * Split and sort an array.
   * @param values
   * @return sorted array
   */
  T[] split(T[] values) {
    if (values.length <= 1) {
      return values;
    } // if
    T[] valuesA = (T[]) new Object[((int) (values.length / 2))];
    for (int i = 0; i < valuesA.length; i++) {
      valuesA[i] = values[i];
    } // for
    T[] valuesB = (T[]) new Object[values.length - ((int) (values.length / 2))];

    for (int i = valuesA.length; i < values.length; i++) {
      valuesB[i - valuesA.length] = values[i];
    } // for
    valuesA = split(valuesA);
    valuesB = split(valuesB);
    T[] merged = merge(valuesA, valuesB);
    return merged;
  } // split

  /**
   * Merge two sub arrays and sort.
   * @param valuesA
   * @param valuesB
   * @return merged array
   */
  T[] merge(T[] valuesA, T[] valuesB) {
    T[] merged = (T[]) new Object[valuesA.length + valuesB.length];

    int a = 0;
    int b = 0;
    int sort = 0;
    while (a < valuesA.length || b < valuesB.length) {
      if (a >= valuesA.length) {
        merged[sort] = valuesB[b];
        b++;
        sort++;
        continue;
      } // if
      if (b >= valuesB.length) {
        merged[sort] = valuesA[a];
        a++;
        sort++;
        continue;
      } // if
      if (order.compare(valuesB[b], valuesA[a]) > 0) {
        merged[sort] = valuesA[a];
        a++;
        sort++;
      } else {
        merged[sort] = valuesB[b];
        b++;
        sort++;
      } // if else
    } // while
    return merged;

  } // merge
} // class MergeSorter
