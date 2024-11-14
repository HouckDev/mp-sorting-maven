package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import edu.grinnell.csc207.util.ArrayUtils;

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
    values = split(values);
  } // sort(T[])

  T[] split(T[] values) {
    if (values.length <= 1) {
      return values;
    }
    T[] valuesA = (T[]) new Object[((int) (values.length / 2))];
    for (int i = 0; i < valuesA.length; i++) {
      valuesA[i] = values[i];
    }
    T[] valuesB = (T[]) new Object[values.length - ((int) (values.length / 2))];

    for (int i = valuesA.length; i < values.length; i++) {
      valuesB[i - valuesA.length] = values[i];
    }
    valuesA = split(valuesA);
    valuesB = split(valuesB);
    T[] merged = merge(valuesA, valuesB);
    return merged;
  }

  T[] merge(T[] valuesA, T[] valuesB) {
    T[] merged = (T[]) new Object[valuesA.length + valuesB.length];
    int sort = 0;
    for (int compare = 0; compare < valuesA.length; compare++) {
      for (int i = 0; i < valuesB.length; i++) {
        if (order.compare(valuesA[compare], valuesB[i]) >= 0) {
          merged[sort] = valuesB[i];
          sort++;
        } else {
          merged[sort] = valuesA[compare];
          sort++;
          break;
        }
      }
    }
    return merged;

  }
} // class MergeSorter
