package edu.grinnell.csc207.sorting;

import org.junit.jupiter.api.BeforeAll;

/**
 * Tests of our SelectionSorter.
 */
public class TestHybridSort extends TestSorter {
  /**
   * Set up the sorters.
   */
  @BeforeAll
  static void setup() {
    stringSorter = new HouckPadenSort<String>((x,y) -> x.compareTo(y));
    intSorter = new HouckPadenSort<Integer>((x,y) -> x.compareTo(y));
  } // setup()

} // class TestSelectionSorter
