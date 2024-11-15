# mp-sorting-maven

An exploration of sorting in Java.

Authors

* Paden Houck.
* Samuel A. Rebelsky (starter code)

Acknowledgements

* _Forthcoming_.

This code may be found at <https://github.com/USERNAME/mp-sorting-maven>. The original code may be found at <https://github.com/Grinnell-CSC207/mp-sorting-maven>.

Description of custom sorting algorithm
---------------------------------------
Algorithm uses a hybrid of quicksort and insertion sort. Quicksort is most efficent with larger arrays and pivots that are close to the median, insertion sort is most efficient for small arrays. Using quicksort's divide-and-conquer strategy we can choose which algorithmn to use on each partition. Through manual testing it has been determined that arrays of less than length 10 are most efficiently sorted by insertion sort. This optimization alone makes the HybridQuickSort ~50ms faster on average than the standard quicksort implementation. Additionally, the variation of quicksort used in this algorithmn determines a median more accurately by sample 3 random values from the array and mathematically computing their median, saving an additional ~10ms on average.
