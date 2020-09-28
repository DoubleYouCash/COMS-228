package edu.iastate.cs228.hw2;



import javax.swing.plaf.InsetsUIResource;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Comparator;


/**
 * An class that compares various methods of sorting.
 * 
 * @author Wyatt Duberstein and Mike Petersen
 */
public class SorterFramework {
  /**
   * Loads data necessary to run the sorter statistics output, and runs it.
   * The only logic within this method should be that necessary to use the
   * given file names to create the {@link AlphabetComparator},
   * {@link WordList}, and sorters to use, and then using them to run the
   * sorter statistics output.
   * 
   * @param args
   *   an array expected to contain two arguments:
   *    - the name of a file containing the ordering to use to compare
   *      characters
   *    - the name of a file containing words containing only characters in the
   *      other file
   */
  public static void main(String[] args) {

    Alphabet alphabet = null;
    try {
      alphabet = new Alphabet(args[0]);
    } catch (FileNotFoundException e){
      e.printStackTrace();
    }

    AlphabetComparator comparator = new AlphabetComparator(alphabet);
    WordList words = null;
    try {
      words = new WordList(args[1]);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    Sorter insert = new InsertionSorter();
    Sorter merge = new MergeSorter();
    Sorter quick = new QuickSorter();

    Sorter[] sorters = {insert, merge, quick};

    SorterFramework toRun;
    if (words != null) {
      toRun = new SorterFramework(sorters, comparator, words, words.length());
    } else {
      throw new NullPointerException();
    }

    toRun.run();

  }


  /**
   * The comparator to use for sorting.
   */
  private final Comparator<String> comparator;

  /**
   * The words to sort.
   */
  private final WordList words;

  /**
   * The array of sorters to use for sorting.
   */
  private final Sorter[] sorters;

  /**
   * The total amount of words expected to be sorted by each sorter.
   */
  private final int totalToSort;


  /**
   * Constructs and initializes the SorterFramework.
   * 
   * @param sorters
   *   the array of sorters to use for sorting
   * @param comparator
   *   the comparator to use for sorting
   * @param words
   *   the words to sort
   * @param totalToSort
   *   the total amount of words expected to be sorted by each sorter
   * @throws NullPointerException
   *   if any of {@code sorters}, {@code comparator}, {@code words}, or
   *   elements of {@code sorters} are {@code null}
   * @throws IllegalArgumentException
   *   if {@code totalToSort} is negative
   */
  public SorterFramework(Sorter[] sorters, Comparator<String> comparator, WordList words, int totalToSort) throws NullPointerException, IllegalArgumentException {

    if (sorters != null || comparator != null || words != null) {
      this.sorters = sorters;
      this.comparator = comparator;
      this.words = words;
    } else {
      throw new NullPointerException();
    }

    for (Sorter sorter : sorters) {
      if (sorter == null) {
        throw new NullPointerException();
      }
    }

    if (totalToSort > 0) {
      this.totalToSort = totalToSort;
    } else {
      throw new IllegalArgumentException();
    }

  }


  /**
   * Runs all sorters using
   * {@link Sorter#sortWithStatistics(WordList, Comparator, int)
   * sortWithStatistics()}, and then outputs the following information for each
   * sorter:
   *  - the name of the sorter
   *  - the length of the word list sorted each time
   *  - the total number of words sorted
   *  - the total time used to sort words
   *  - the average time to sort the word list
   *  - the number of elements sorted per second
   *  - the total number of comparisons performed
   */
  public void run() {
    // TODO

    for (Sorter sorter : this.sorters) {

      long wordsSorted = 0;
      long runtime = 0;
      long comparisons = 0;
      long iterations = 0;

      sorter.sortWithStatistics(words, comparator, totalToSort);

      wordsSorted = sorter.getTotalWordsSorted();
      runtime = sorter.getTotalSortingTime();
      comparisons = sorter.getTotalComparisons();
      iterations = sorter.iterations;

      System.out.println("Sorter Name: " + sorter.getName());
      System.out.println("Length of the Word List: " + totalToSort);
      System.out.println("Total Words Sorted: " + wordsSorted);
      System.out.println("Time Taken to Sort: " + runtime / 1000 + "s");
      System.out.println("Average Time to Sort: " + runtime / iterations + "ms");
      System.out.println("Elements Sorted per Second: " + wordsSorted / (runtime / 1000));
      System.out.println("Total Comparisons Performed" + comparisons);
      System.out.println();

    }

  }
}
